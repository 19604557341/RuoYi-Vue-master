<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="方案名称" prop="schemename">
        <el-input
          v-model="queryParams.schemename"
          placeholder="请输入方案名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人" prop="founder">
        <el-input
          v-model="queryParams.founder"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="creationtime">
        <el-date-picker clearable
          v-model="queryParams.creationtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="添加时间" prop="addtime">
        <el-date-picker clearable
          v-model="queryParams.addtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择添加时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['admin:feescheme:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:feescheme:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:feescheme:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['admin:feescheme:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="feeschemeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="收费方案管理表id" align="center" prop="fsmid" />
      <el-table-column label="方案名称" align="center" prop="schemename" />
      <el-table-column label="收费类型" align="center" prop="feeType" />
      <el-table-column label="" align="center" prop="stateType" />
      <el-table-column label="创建人" align="center" prop="founder" />
      <el-table-column label="创建时间" align="center" prop="creationtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.creationtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="添加时间" align="center" prop="addtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['admin:feescheme:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['admin:feescheme:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改收费方案管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="方案名称" prop="schemename">
          <el-input v-model="form.schemename" placeholder="请输入方案名称" />
        </el-form-item>
        <el-form-item label="收费类型" prop="feeType" required>
                  <el-select v-model="form.feeType" placeholder="收费类型" clearable :style="{width: '100%'}">
                    <el-option v-for="(item, index) in feeTypeOptions" :key="index" :label="item.dictLabel"
                      :value="item.dictCode"></el-option>
                  </el-select>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios'
import service from "@/views/axios.js"
import { listFeescheme, getFeescheme, delFeescheme, addFeescheme, updateFeescheme } from "@/api/admin/feescheme";

export default {
  name: "Feescheme",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 收费方案管理表格数据
      feeschemeList: [],
      // 收费类型
      feeTypeOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        schemename: null,
        feeType: null,
        stateType: null,
        founder: null,
        creationtime: null,
        addtime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询收费方案管理列表 */
    getList() {
      this.loading = true;
      listFeescheme(this.queryParams).then(response => {
        this.feeschemeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        fsmid: null,
        schemename: null,
        feeType: null,
        stateType: null,
        founder: null,
        creationtime: null,
        addtime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.fsmid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加收费方案管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const fsmid = row.fsmid || this.ids
      getFeescheme(fsmid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改收费方案管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.fsmid != null) {
            updateFeescheme(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFeescheme(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const fsmids = row.fsmid || this.ids;
      this.$modal.confirm('是否确认删除收费方案管理编号为"' + fsmids + '"的数据项？').then(function() {
        return delFeescheme(fsmids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('admin/feescheme/export', {
        ...this.queryParams
      }, `feescheme_${new Date().getTime()}.xlsx`)
    }
  },
   mounted(){
      service.get('/system/dict/data/type/fee_type')
                   .then(response => {
                     this.feeTypeOptions = response.data.data;

                   })
                   .catch(error => {
                     console.log(error);
                   });

   }
};
</script>

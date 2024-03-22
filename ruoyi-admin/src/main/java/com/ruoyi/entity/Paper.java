package rouyi.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Paper {
    @TableId(type = IdType.AUTO)
    private Integer paperId;
    private Integer subjectId;
    private Integer categoryId;
    private Integer gradeId;
    private Integer year;
    private Integer month;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    private Integer createUser;
    @TableField(fill = FieldFill.INSERT)
    private Integer updateUser;
}

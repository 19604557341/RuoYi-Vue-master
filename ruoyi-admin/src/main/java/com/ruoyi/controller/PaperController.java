package com.ruoyi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.R;
import com.ruoyi.dto.PaperDto;
import com.ruoyi.entity.Paper;
import com.ruoyi.entity.Subject;
import com.ruoyi.service.PaperService;
import com.ruoyi.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @Autowired
    private SubjectService subjectService;

    /**
     *试卷分页查询
     * @param request
     * @param page
     * @param pageSize
     * @param categoryId
     * @param subjectId
     * @param gradeId
     * @param year
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(HttpServletRequest request, Integer page, Integer pageSize, Integer categoryId, Integer subjectId, Integer gradeId, Integer year) {
        Page<Paper> pageInfo = new Page<>(page, pageSize);
        Page<PaperDto> paperDtoPage = new Page<>();

        LambdaQueryWrapper<Paper> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(categoryId != null, Paper::getCategoryId, categoryId)
                .like(subjectId != null, Paper::getPaperId, categoryId)
                .like(gradeId != null, Paper::getGradeId, gradeId)
                .like(year != null, Paper::getYear, year)
                .orderByDesc(Paper::getUpdateTime);

        paperService.page(pageInfo, queryWrapper);

        BeanUtils.copyProperties(pageInfo, paperDtoPage, "records");

        List<Paper> records = pageInfo.getRecords();
        List<PaperDto> list = records.stream().map((item) -> {
            PaperDto paperDto = new PaperDto();

            BeanUtils.copyProperties(item, paperDto);

            Integer subjectId1 = item.getSubjectId();
            Subject subject = subjectService.getById(subjectId1);
            paperDto.setSubjectName(subject.getSubjectName());

            return paperDto;
        }).collect(Collectors.toList());

        paperDtoPage.setRecords(list);

        return R.success(paperDtoPage);
    }


    /**
     * 新增试卷
     * @param request
     * @param paper
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, Paper paper) {
        log.info("试卷信息：{}", paper);

        paperService.save(paper);

        return R.success("新增成功");
    }

    /**
     * 根据id修改试卷信息
     * @param request
     * @param paper
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest request, Paper paper) {
        log.info("试卷信息：{}", paper);

        paperService.updateById(paper);

        return R.success("修改成功");
    }

    /**
     * 删除题型
     * @param request
     * @param PaperId
     * @return
     */
    @DeleteMapping
    public R<String> delete(HttpServletRequest request, Integer PaperId) {

        paperService.removeById(PaperId);

        return R.success("删除成功");
    }
}

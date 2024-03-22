package rouyi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rouyi.common.R;
import rouyi.dto.ProblemDto;
import rouyi.entity.Paper;
import rouyi.entity.Problem;
import rouyi.entity.Subject;
import rouyi.service.PaperService;
import rouyi.service.ProblemService;
import rouyi.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private SubjectService subjectService;

    /**
     *题库分页查询
     * @param request
     * @param page
     * @param pageSize
     * @param paperId
     * @param questionsId
     * @param problem
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(HttpServletRequest request, Integer page, Integer pageSize, Integer paperId, Integer questionsId, String problem) {
        log.info("数据id；{}，类型id：{}，问题：{}", paperId, questionsId, problem);
        Page<Problem> pageInfo = new Page<>(page, pageSize);
        Page<ProblemDto> problemDtoPage = new Page<>();

        LambdaQueryWrapper<Problem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(problem != null, Problem::getProblem, problem)
                .like(paperId != null, Problem::getPaperId, paperId)
                .like(questionsId != null, Problem::getQuestionsId, problem)
                .orderByDesc(Problem::getUpdateTime);

        problemService.page(pageInfo, queryWrapper);

        BeanUtils.copyProperties(pageInfo, problemDtoPage, "records");

        List<Problem> records = pageInfo.getRecords();
        List<ProblemDto> list =records.stream().map((item) -> {
            ProblemDto problemDto = new ProblemDto();

            BeanUtils.copyProperties(item, problemDto);

            Integer paperId1 = item.getPaperId();
            Paper paper = paperService.getById(paperId1);
            String paperName = String.valueOf(paper.getCategoryId()) + paper.getGradeId();
            problemDto.setPaper(paperName);
            problemDto.setSubjectsId(paper.getSubjectId());
            Integer subjectId = problemDto.getSubjectsId();
            Subject subject = subjectService.getById(subjectId);
            problemDto.setSubjects(subject.getSubjectName());

            return problemDto;
        }).collect(Collectors.toList());

        problemDtoPage.setRecords(list);

        return R.success(problemDtoPage);
    }

    /**
     * 新增问题
     * @param request
     * @param problem
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, Problem problem) {
        log.info("问题信息：{}", problem);

        problemService.save(problem);

        return R.success("存入问题成功");
    }

    /**
     *根据id修改问题
     * @param request
     * @param problem
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest request, Problem problem) {

        problemService.updateById(problem);

        return R.success("修改成功");
    }


    /**
     * 删除题型
     * @param problemId
     * @return
     */
    @DeleteMapping
    public R<String> delete(Integer problemId) {

        problemService.removeById(problemId);

        return R.success("删除成功");
    }
}

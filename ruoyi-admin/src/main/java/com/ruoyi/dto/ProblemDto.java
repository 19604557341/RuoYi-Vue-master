package com.ruoyi.dto;

import com.ruoyi.entity.Problem;
import lombok.Data;


@Data
public class ProblemDto extends Problem {
    private String paper;
    private Integer subjectsId;
    private String subjects;

}

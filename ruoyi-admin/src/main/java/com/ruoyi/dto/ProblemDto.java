package rouyi.dto;

import lombok.Data;
import rouyi.entity.Problem;

@Data
public class ProblemDto extends Problem {
    private String paper;
    private Integer subjectsId;
    private String subjects;

}

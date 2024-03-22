package rouyi.dto;

import lombok.Data;
import rouyi.entity.Paper;

@Data
public class PaperDto extends Paper {
    private String subjectName;
}

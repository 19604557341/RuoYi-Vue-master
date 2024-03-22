package com.ruoyi.dto;

import com.ruoyi.entity.Paper;
import lombok.Data;


@Data
public class PaperDto extends Paper {
    private String subjectName;
}

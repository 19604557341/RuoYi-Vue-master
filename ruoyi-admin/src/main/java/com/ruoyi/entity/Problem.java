package com.ruoyi.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Problem {
    @TableId(type = IdType.AUTO)
    private Integer problemId;
    private Integer paperId;
    private Integer questionsId;
    private String problem;
    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private String answerFour;
    private String answerFive;
    private String answerSix;
    private String answerSeven;
    private String answerEight;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    private Integer createUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateUser;
}

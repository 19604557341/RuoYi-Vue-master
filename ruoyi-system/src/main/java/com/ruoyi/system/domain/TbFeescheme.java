package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收费方案管理对象 tb_feescheme
 * 
 * @author ruoyi
 * @date 2024-01-30
 */
public class TbFeescheme extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 收费方案管理表id */
    private Long fsmid;

    /** 方案名称 */
    @Excel(name = "方案名称")
    private String schemename;

    /** 收费类型 */
    @Excel(name = "收费类型")
    private Long feeType;

    /**  */
    @Excel(name = "")
    private Integer stateType;

    /** 创建人 */
    @Excel(name = "创建人")
    private String founder;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creationtime;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addtime;

    public void setFsmid(Long fsmid) 
    {
        this.fsmid = fsmid;
    }

    public Long getFsmid() 
    {
        return fsmid;
    }
    public void setSchemename(String schemename) 
    {
        this.schemename = schemename;
    }

    public String getSchemename() 
    {
        return schemename;
    }
    public void setFeeType(Long feeType) 
    {
        this.feeType = feeType;
    }

    public Long getFeeType() 
    {
        return feeType;
    }
    public void setStateType(Integer stateType) 
    {
        this.stateType = stateType;
    }

    public Integer getStateType() 
    {
        return stateType;
    }
    public void setFounder(String founder) 
    {
        this.founder = founder;
    }

    public String getFounder() 
    {
        return founder;
    }
    public void setCreationtime(Date creationtime) 
    {
        this.creationtime = creationtime;
    }

    public Date getCreationtime() 
    {
        return creationtime;
    }
    public void setAddtime(Date addtime) 
    {
        this.addtime = addtime;
    }

    public Date getAddtime() 
    {
        return addtime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fsmid", getFsmid())
            .append("schemename", getSchemename())
            .append("feeType", getFeeType())
            .append("stateType", getStateType())
            .append("founder", getFounder())
            .append("creationtime", getCreationtime())
            .append("addtime", getAddtime())
            .toString();
    }
}

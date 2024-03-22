package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TbFeescheme;

/**
 * 收费方案管理Service接口
 * 
 * @author ruoyi
 * @date 2024-01-30
 */
public interface ITbFeeschemeService 
{
    /**
     * 查询收费方案管理
     * 
     * @param fsmid 收费方案管理主键
     * @return 收费方案管理
     */
    public TbFeescheme selectTbFeeschemeByFsmid(Long fsmid);

    /**
     * 查询收费方案管理列表
     * 
     * @param tbFeescheme 收费方案管理
     * @return 收费方案管理集合
     */
    public List<TbFeescheme> selectTbFeeschemeList(TbFeescheme tbFeescheme);

    /**
     * 新增收费方案管理
     * 
     * @param tbFeescheme 收费方案管理
     * @return 结果
     */
    public int insertTbFeescheme(TbFeescheme tbFeescheme);

    /**
     * 修改收费方案管理
     * 
     * @param tbFeescheme 收费方案管理
     * @return 结果
     */
    public int updateTbFeescheme(TbFeescheme tbFeescheme);

    /**
     * 批量删除收费方案管理
     * 
     * @param fsmids 需要删除的收费方案管理主键集合
     * @return 结果
     */
    public int deleteTbFeeschemeByFsmids(Long[] fsmids);

    /**
     * 删除收费方案管理信息
     * 
     * @param fsmid 收费方案管理主键
     * @return 结果
     */
    public int deleteTbFeeschemeByFsmid(Long fsmid);
}

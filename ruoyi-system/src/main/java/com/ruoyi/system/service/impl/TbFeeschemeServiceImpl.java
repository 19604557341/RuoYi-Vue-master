package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.TbFeescheme;
import com.ruoyi.system.mapper.TbFeeschemeMapper;
import com.ruoyi.system.service.ITbFeeschemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收费方案管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-30
 */
@Service
public class TbFeeschemeServiceImpl implements ITbFeeschemeService 
{
    @Autowired
    private TbFeeschemeMapper tbFeeschemeMapper;

    /**
     * 查询收费方案管理
     * 
     * @param fsmid 收费方案管理主键
     * @return 收费方案管理
     */
    @Override
    public TbFeescheme selectTbFeeschemeByFsmid(Long fsmid)
    {
        return tbFeeschemeMapper.selectTbFeeschemeByFsmid(fsmid);
    }

    /**
     * 查询收费方案管理列表
     * 
     * @param tbFeescheme 收费方案管理
     * @return 收费方案管理
     */
    @Override
    public List<TbFeescheme> selectTbFeeschemeList(TbFeescheme tbFeescheme)
    {
        return tbFeeschemeMapper.selectTbFeeschemeList(tbFeescheme);
    }

    /**
     * 新增收费方案管理
     * 
     * @param tbFeescheme 收费方案管理
     * @return 结果
     */
    @Override
    public int insertTbFeescheme(TbFeescheme tbFeescheme)
    {
        return tbFeeschemeMapper.insertTbFeescheme(tbFeescheme);
    }

    /**
     * 修改收费方案管理
     * 
     * @param tbFeescheme 收费方案管理
     * @return 结果
     */
    @Override
    public int updateTbFeescheme(TbFeescheme tbFeescheme)
    {
        return tbFeeschemeMapper.updateTbFeescheme(tbFeescheme);
    }

    /**
     * 批量删除收费方案管理
     * 
     * @param fsmids 需要删除的收费方案管理主键
     * @return 结果
     */
    @Override
    public int deleteTbFeeschemeByFsmids(Long[] fsmids)
    {
        return tbFeeschemeMapper.deleteTbFeeschemeByFsmids(fsmids);
    }

    /**
     * 删除收费方案管理信息
     * 
     * @param fsmid 收费方案管理主键
     * @return 结果
     */
    @Override
    public int deleteTbFeeschemeByFsmid(Long fsmid)
    {
        return tbFeeschemeMapper.deleteTbFeeschemeByFsmid(fsmid);
    }
}

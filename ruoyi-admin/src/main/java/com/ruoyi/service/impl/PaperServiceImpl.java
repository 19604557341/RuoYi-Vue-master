package rouyi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import rouyi.entity.Paper;
import rouyi.mapper.PaperMapper;
import rouyi.service.PaperService;

@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {
}

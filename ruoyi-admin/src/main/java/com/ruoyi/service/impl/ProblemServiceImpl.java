package rouyi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rouyi.entity.Problem;
import rouyi.mapper.ProblemMapper;
import rouyi.service.PaperService;
import rouyi.service.ProblemService;

@Service
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements ProblemService {
}

package rouyi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import rouyi.entity.Subject;
import rouyi.mapper.SubjectMapper;
import rouyi.service.SubjectService;

@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
}

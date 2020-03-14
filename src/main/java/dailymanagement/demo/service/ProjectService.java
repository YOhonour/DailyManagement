package dailymanagement.demo.service;

import dailymanagement.demo.bean.Project;
import dailymanagement.demo.dao.BookMapper;
import dailymanagement.demo.dao.ProjectMapper;
import dailymanagement.demo.service.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements ProjectServiceImpl {

    @Autowired
    ProjectMapper projectMapper;

    @Override
    public List<Project> getAll() {
        return projectMapper.selectByGid();
    }
}

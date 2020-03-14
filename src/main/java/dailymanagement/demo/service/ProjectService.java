package dailymanagement.demo.service;

import dailymanagement.demo.bean.Project;
import dailymanagement.demo.mapper.ProjectMapper1;
import dailymanagement.demo.service.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements ProjectServiceImpl {

    @Autowired
    ProjectMapper1 projectMapper1;

    @Override
    public List<Project> getAll() {
        return projectMapper1.selectByGid();
    }
}

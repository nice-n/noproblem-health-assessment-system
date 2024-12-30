package com.noproblem.has.service;

import com.noproblem.has.entity.Project;
import com.noproblem.has.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：ysl
 * @Package：com.noproblem.has.service
 * @Project：noproblem
 * @name：ProjectService
 * @Date：2024/12/30 9:48
 */
@Service
public class ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    public Project findByProjectName(String projectName) {
        return projectMapper.findAllProjects(projectName);
    }
}

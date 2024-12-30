package com.noproblem.has.mapper;


import com.noproblem.has.entity.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectMapper {
    List<Project> findAllProjects();
    Project findAllProjects(@Param("project_name") String projectName);
}

package com.noproblem.has.controller;

import com.noproblem.has.entity.Project;
import com.noproblem.has.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/scores")
    public Project getProjectByProjectName(@RequestParam String project_name) {
        return projectService.findByProjectName(project_name);
    }
}

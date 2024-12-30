package com.noproblem.has.controller;

import com.noproblem.has.entity.Project;
import com.noproblem.has.entity.Rank;
import com.noproblem.has.service.ProjectService;
import com.noproblem.has.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/rank")
public class RankController {
    @Autowired
    private RankService rankService;

    @GetMapping("/scores")
    public List<Rank> getAllProjects() {
        return rankService.findRank();
    }

}

package com.noproblem.has.controller;

import com.noproblem.has.service.Indicators1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：ysl
 * @Package：com.noproblem.has.controller
 * @Project：has
 * @name：Indicators1Controller
 * @Date：2024/12/29 12:17
 */

@RestController
@RequestMapping("/indicators1")
public class Indicators1Controller {

    @Autowired
    private Indicators1Service indicators1Service;

    /***根据项目名和一级指标名查询一级指标得分**/
    @GetMapping("/scores")
    public ResponseEntity<String[]> getScores(@RequestParam String indicator1_name, @RequestParam String project_name) {
        String[] scores = indicators1Service.getScores(indicator1_name, project_name);
        if (scores == null || scores.length == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(scores);
    }
}

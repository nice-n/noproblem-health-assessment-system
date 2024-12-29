package com.noproblem.has.service;

import com.noproblem.has.mapper.Indicators1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：ysl
 * @Package：com.noproblem.has.service
 * @Project：has
 * @name：Indicators1Service
 * @Date：2024/12/29 12:18
 */
@Service
public class Indicators1Service {
    @Autowired
    private Indicators1Mapper indicators1Mapper;
    public String[] getScores(String indicator1_name, String project_name) {
        List<String> scoresList = indicators1Mapper.selectScoresByIndicatorAndProject(indicator1_name, project_name);
        return scoresList.toArray(new String[0]); // 将List转换为数组
    }

}

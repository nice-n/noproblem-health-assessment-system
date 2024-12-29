package com.noproblem.has.service;

import com.noproblem.has.mapper.Indicators1Mapper;
import com.noproblem.has.mapper.Indicators2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：ysl
 * @Package：com.noproblem.has.service
 * @Project：noproblem
 * @name：Indicators2Service
 * @Date：2024/12/29 16:56
 */
@Service
public class Indicators2Service {
    @Autowired
    private Indicators2Mapper indicators2Mapper;
    public String[] getIndicatorsScores(String indicator1_name, String project_name) {
        List<String> scoresList = indicators2Mapper.selectIndicators2(indicator1_name, project_name);
        return scoresList.toArray(new String[0]); // 将List转换为数组
    }
}

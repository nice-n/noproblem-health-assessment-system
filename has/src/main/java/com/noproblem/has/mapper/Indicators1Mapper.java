package com.noproblem.has.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author：ysl
 * @Package：com.noproblem.has.mapper
 * @Project：has
 * @name：Indicators1Mapper
 * @Date：2024/12/29 12:17
 */
public interface Indicators1Mapper {
    List<String> selectScoresByIndicatorAndProject(@Param("indicator1_name") String indicator1Name, @Param("project_name") String projectName);
}

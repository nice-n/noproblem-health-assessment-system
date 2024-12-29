package com.noproblem.has.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface Indicators1Mapper {
    List<String> selectScoresByIndicatorAndProject(@Param("indicator1_name") String indicator1Name, @Param("project_name") String projectName);
}

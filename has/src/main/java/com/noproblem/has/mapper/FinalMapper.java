package com.noproblem.has.mapper;


import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinalMapper {
    List<String> FinalScoresByProject( @Param("project_name") String projectName);
}

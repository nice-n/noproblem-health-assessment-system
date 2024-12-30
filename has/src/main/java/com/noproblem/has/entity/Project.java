package com.noproblem.has.entity;

import lombok.Data;

/**
 * @Author：ysl
 * @Package：com.noproblem.openrank.entity
 * @Project：openRank
 * @name：Project
 * @Date：2024/12/28 22:31
 */
@Data
public class Project {
    private Double score;
    private Double projectResponse;
    private Double newParticipant;
    private Double busFactor;
    private Double developmentTrend;// 改为String类型以匹配数据库中的char类型
}



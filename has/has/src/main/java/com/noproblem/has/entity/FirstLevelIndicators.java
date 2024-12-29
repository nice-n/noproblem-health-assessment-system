package com.noproblem.has.entity;

import lombok.Data;

/**
 * @Author：ysl
 * @Package：com.noproblem.openrank.entity
 * @Project：openRank
 * @name：FirstLevelIndicators
 * @Date：2024/12/29 10:46
 */
@Data
public class FirstLevelIndicators {
    private int id;
    private String score;
    private Project project;
    private Indicators1 indicators;
}

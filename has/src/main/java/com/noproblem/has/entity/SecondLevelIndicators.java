package com.noproblem.has.entity;

import lombok.Data;

/**
 * @Author：ysl
 * @Package：com.noproblem.openrank.entity
 * @Project：openRank
 * @name：SecondLevelIndicators
 * @Date：2024/12/29 14:35
 */
@Data
public class SecondLevelIndicators {
    private int id;
    private String score;
    private Project project;
    private Indicators1 indicators1;
    private Indicators2 indicators2;
}

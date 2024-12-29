package com.noproblem.has.entity;

import lombok.Data;

/**
 * @Author：ysl
 * @Package：com.noproblem.openrank.entity
 * @Project：openRank
 * @name：indicators2
 * @Date：2024/12/29 14:35
 */
@Data
public class Indicators2 {
    private int indicator2_id;
    private String indicator2_name;
    private Indicators1 indicators1;
}

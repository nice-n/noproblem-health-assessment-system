package com.noproblem.has.controller;

import com.noproblem.has.service.Indicators1Service;
import com.noproblem.has.service.Indicators2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/indicators2")
public class Indicators2Controller {
    @Autowired
    private Indicators2Service indicators2Service;

    /***根据项目名和一级指标名查询一级指标得分**/
    @GetMapping("/scores")
    public ResponseEntity<double[][]> getIndicatorsScores(@RequestParam String indicator1_name, @RequestParam String project_name) {
        String[] scoreStrings = indicators2Service.getIndicatorsScores(indicator1_name, project_name);
        if (scoreStrings == null || scoreStrings.length == 0) {
            return ResponseEntity.notFound().build();
        }
        // 创建一个列表，用来保存小数组
        List<String[]> smallArrays = new ArrayList<>();
        // 创建一个列表，用来保存浮点型小数组
        List<double[]> floatSmallArrays = new ArrayList<>();
        // 遍历原始数组，分割字符串并存储到小数组
        for (String str : scoreStrings) {
            // 分割字符串
            String[] smallArray = str.split(",");
            // 将小数组添加到列表中
            smallArrays.add(smallArray);
            // 转换为浮点型数组并添加到浮点型小数组列表
            double[] scores = new double[smallArray.length];
            for (int i = 0; i < smallArray.length; i++) {
                scores[i] = Double.parseDouble(smallArray[i].trim());
            }
            floatSmallArrays.add(scores);
        }
        double[][] scoresArray = floatSmallArrays.toArray(new double[0][]);

        // 假设这是在某个方法中，返回ResponseEntity对象
         return ResponseEntity.ok(scoresArray);
    }
}

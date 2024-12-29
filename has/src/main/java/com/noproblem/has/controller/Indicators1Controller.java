package com.noproblem.has.controller;


import com.noproblem.has.service.Indicators1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/indicators1")
public class Indicators1Controller {

    @Autowired
    private Indicators1Service indicators1Service;

    /***根据项目名和一级指标名查询一级指标得分**/
    @GetMapping("/scores")
    public ResponseEntity<double[]> getScores(@RequestParam String indicator1_name, @RequestParam String project_name) {
        String[] scoreStrings = indicators1Service.getScores(indicator1_name, project_name);
        if (scoreStrings == null || scoreStrings.length == 0) {
            return ResponseEntity.notFound().build();
        }
        String[] stringNumbers = scoreStrings[0].split(",");
        // 创建一个与分割后的字符串数组等长的浮点型数据数组
        double[] doubleArray = new double[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            try {
                doubleArray[i] = Double.parseDouble(stringNumbers[i].trim());
            } catch (NumberFormatException e) {
                // 处理转换失败的情况，例如返回错误信息或者默认值
                return ResponseEntity.badRequest().body(null);
            }
        }
        return ResponseEntity.ok(doubleArray); // 返回整数数组
    }


}

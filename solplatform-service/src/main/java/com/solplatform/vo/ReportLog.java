package com.solplatform.vo;

import lombok.Data;

/**
 * 测试报告
 *
 * @author sol
 * @create 2020-06-19  6:28 下午
 */
@Data
public class ReportLog {
    private Long lastTimeFileSize;
    private String text;
    private String color;
}

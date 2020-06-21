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
    // 文件最后读取的指针位置
    private Long lastTimeFileSize;
    // 读取的文本
    private String text;
}

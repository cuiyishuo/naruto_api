package com.solplatform.vo;

import lombok.Data;

import java.util.List;

/**
 * 分页返回的信息
 *
 * @author sol
 * @create 2020-02-16  15:41
 */
@Data
public class TablePage {
    // 数据总条数
    private Long total;
    // 当前页展示的数据
    private List currentPageData;
}

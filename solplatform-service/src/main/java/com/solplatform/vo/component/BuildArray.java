package com.solplatform.vo.component;

import lombok.Data;

/**
 * case和interface数组
 *
 * @author sol
 * @create 2020-04-28  10:31
 */
@Data
public class BuildArray {
    // 接口id数组
    private String[] interfaceIds;
    // caseid数组
    private String[] caseIds;
}

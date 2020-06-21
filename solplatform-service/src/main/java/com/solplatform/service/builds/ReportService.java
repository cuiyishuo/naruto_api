package com.solplatform.service.builds;

import com.solplatform.util.ReportLogUtil;
import com.solplatform.vo.ReportLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 日志报告业务
 *
 * @author sol
 * @create 2020-06-21  11:53 上午
 */
@Service
@Slf4j
public class ReportService {

    /**
     * 读取测试日志
     *
     * @param buildId      该日志对应构建id
     * @param lastFileSize 上一次读取到的日志位置
     * @return
     */
    public ReportLog readReport(String buildId, Long lastFileSize) {
        ReportLogUtil reportLogUtil = new ReportLogUtil ();
        if (null != lastFileSize) {
            return reportLogUtil.readLog (buildId, lastFileSize);
        } else {
            return reportLogUtil.readLog (buildId, 0L);
        }
    }
}

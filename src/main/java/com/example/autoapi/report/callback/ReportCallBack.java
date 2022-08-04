package com.example.autoapi.report.callback;

import com.example.autoapi.model.SummaryResult;

public interface ReportCallBack {
    void postReport(SummaryResult summaryResult,String token,String templateKey);
}

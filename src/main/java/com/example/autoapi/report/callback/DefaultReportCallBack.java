package com.example.autoapi.report.callback;

import com.example.autoapi.http.HttpFacade;
import com.example.autoapi.model.HttpRequest;
import com.example.autoapi.model.SummaryResult;
import com.example.autoapi.template.TemplateFacade;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultReportCallBack implements ReportCallBack{
    @Override
    public void postReport(SummaryResult summaryResult,String token,String templateKey) {
        // 将所有的报错信息组成一个字符串
        String msgs = summaryResult.getFailureResults().stream()
                .map(failureResult -> failureResult.getThrowable().getMessage())
                .collect(Collectors.joining("\n"));

        Map<String,Object> map = new HashMap<>();
        map.put("success_count",summaryResult.getSuccessCount());
        map.put("failure_count",summaryResult.getFailureCount());
        map.put("total_count",summaryResult.getTotalCount());
        map.put("start_time",summaryResult.getStartTime());
        map.put("end_time",summaryResult.getEndTime());
        map.put("failure_msg",msgs);
        String template = TemplateFacade.replaceTemplate(templateKey,map);
        Map<String,String> text = new HashMap<>();
        text.put("content",template);
        Map<String,Object> data = new HashMap<>();
        data.put("text",text);
        data.put("msgtype","text");
        String url = DING_ALARM_URL + token;
//        HttpFacade.doPostJson(url,data);

        HttpRequest httpRequest = HttpRequest.builder()
                .postJson()
                .url(url)
                .data(data)
                .build();
        HttpFacade.doRequest(httpRequest);



    }
    private final static String DING_ALARM_URL = "https://oapi.dingtalk.com/robot/send?access_token=";

}

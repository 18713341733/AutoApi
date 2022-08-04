package com.example.autoapi.cases.run;

import com.example.autoapi.alarm.callback.DefaultAlarmCallBack;
import com.example.autoapi.annotation.CaseSelector;
import com.example.autoapi.annotation.DingTalkAlarm;
import com.example.autoapi.annotation.ReportConfig;
import com.example.autoapi.report.callback.DefaultReportCallBack;

public class SelectCaseRun {
    @CaseSelector(scanPackage = "com.example.autoapi.cases.login",key="level",val="redLine")
    @DingTalkAlarm(token="99b4d8ca6a56739f0a5e08bf5b816735567ecab59a323d00c35a8473b4e6a07f",alarmCallBack = DefaultAlarmCallBack.class)
    @ReportConfig(token="99b4d8ca6a56739f0a5e08bf5b816735567ecab59a323d00c35a8473b4e6a07f",callback = DefaultReportCallBack.class)
    public void redLine(){

    }

    @CaseSelector(scanPackage = "com.example.autoapi.cases.login",team="qa",group="normal")
    public void teamTest(){

    }
}

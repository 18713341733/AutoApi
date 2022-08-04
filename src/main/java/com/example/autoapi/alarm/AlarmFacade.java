package com.example.autoapi.alarm;

import com.example.autoapi.alarm.service.AlarmService;
import com.example.autoapi.model.FailureResult;

// 这里是用来做报警扩展的
// 如支持钉钉报警、企业微信报警等
// 本次只支持 钉钉报警 public static void doAlarm
public class AlarmFacade {
    public static void doAlarm(FailureResult failureResult,String token){
        new AlarmService().doAlarm(failureResult,token);

    }
}

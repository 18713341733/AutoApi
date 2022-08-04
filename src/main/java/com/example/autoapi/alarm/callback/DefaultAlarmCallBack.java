package com.example.autoapi.alarm.callback;

import com.example.autoapi.alarm.AlarmFacade;
import com.example.autoapi.model.FailureResult;

// 错误信息回调
// 默认的实现类
public class DefaultAlarmCallBack implements AlarmCallBack{
    @Override
    public void postAlarm(FailureResult failureResult,String token) {
        AlarmFacade.doAlarm(failureResult,token);

    }
}

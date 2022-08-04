package com.example.autoapi.alarm.callback;

import com.example.autoapi.model.FailureResult;

// 定义回调的接口
// 错误信息
public interface AlarmCallBack {
    void postAlarm(FailureResult failureResult,String token);
}

package com.example.autoapi.alarm.service;

import com.example.autoapi.annotation.CaseDesc;
import com.example.autoapi.annotation.CaseTitle;
import com.example.autoapi.annotation.CheckPoint;
import com.example.autoapi.http.HttpFacade;
import com.example.autoapi.model.FailureResult;
import com.example.autoapi.template.TemplateFacade;
import com.example.autoapi.util.ReflectUtils;
import com.google.common.base.Joiner;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 钉钉报警
public class AlarmService {
    public void doAlarm(FailureResult failureResult,String token){
        String className = failureResult.getClassName();
        String methodName = failureResult.getMethodName();
        Method method = ReflectUtils.getMethod(className,methodName);
        String title = null;
        String desc = null;
        String owner = null;
        List<String> cps = null;
        String caseId = className+"#"+methodName;
        String failureMsg = failureResult.getThrowable().getMessage();
        if(method.isAnnotationPresent(CaseTitle.class)){
            // 判断case的标题是否存在
            title = method.getAnnotation(CaseTitle.class).value();
        }
        desc = method.getAnnotation(CaseDesc.class).desc();
        owner = method.getAnnotation(CaseDesc.class).owner();
        CheckPoint[] checkPoints = method.getAnnotationsByType(CheckPoint.class);
        cps= Arrays.stream(checkPoints).map(checkPoint -> checkPoint.value()).collect(Collectors.toList());
        Map<String,Object> map = new HashMap<>();
        map.put("case_title",title);
        map.put("case_id",caseId);
        map.put("case_desc",desc);
        map.put("case_owner",owner);
        map.put("case_cps", Joiner.on(",").join(cps));
        map.put("failure_msg",failureMsg);
        String template = TemplateFacade.replaceTemplate("default_alarm_template", map);
//        System.out.println("template = " + template);
        /*
        {

    "text": {
        "content":"我就是我, @XXX 是不一样的烟火"
    },
    "msgtype":"text"
}
         */

        Map<String,String> text = new HashMap<>();
        text.put("content",template);
        Map<String,Object> data = new HashMap<>();
        data.put("text",text);
        data.put("msgtype","text");
        String url = DING_ALARM_URL + token;
        HttpFacade.doPostJson(url,data);


    }
    private final static String DING_ALARM_URL = "https://oapi.dingtalk.com/robot/send?access_token=";

}

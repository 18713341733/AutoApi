package com.example.autoapi.dac.service;

import com.example.autoapi.dac.DataSourceFactory;
import com.example.autoapi.dac.model.FieldEntity;
import com.example.autoapi.dac.model.RowEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class JdbcService {
    public int modify(String sql, Map<String,Object> params){
        NamedParameterJdbcTemplate jdbcTemplate =
                new NamedParameterJdbcTemplate(DataSourceFactory.of().getPoolDataSource());
        int update = jdbcTemplate.update(sql, params);
        log.info("update rows:"+update);
        return update;
    }
    public List<RowEntity> query(String sql, Map<String,Object> params){
        NamedParameterJdbcTemplate jdbcTemplate =
                new NamedParameterJdbcTemplate(DataSourceFactory.of().getPoolDataSource());
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql, params);
        // mapList = [{id=1, user_id=1000001, user_name=zhangsan1}, {id=2, user_id=1000002, user_name=zhangsan2}]
        // 将得到的map，转成我们自己的对象
        List<RowEntity> rowEntities = mapList.stream()
                // Map<String, Object> -> RowEntity (这是一个集合）
                .map(m -> {
                    List<FieldEntity> fieldEntities = m.entrySet().stream()
                            .map(entry -> new FieldEntity(entry.getKey(), entry.getValue()))
                            .collect(Collectors.toList());
                    return new RowEntity(fieldEntities);
                })
                .collect(Collectors.toList());
        return rowEntities;


    }
}

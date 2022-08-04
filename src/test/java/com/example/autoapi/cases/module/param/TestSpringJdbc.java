package com.example.autoapi.cases.module.param;

import com.example.autoapi.dac.DataSourceFactory;
import com.example.autoapi.dac.model.RowEntity;
import com.example.autoapi.dac.model.StatementBuild;
import com.example.autoapi.dac.model.StatementOperator;
import com.example.autoapi.dac.table.TbUserTable;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestSpringJdbc {
    @Test
    public void test1(){
        NamedParameterJdbcTemplate jdbcTemplate =
                new NamedParameterJdbcTemplate(DataSourceFactory.of().getPoolDataSource());
        String sql = "select * from bt.tb_user where id = :id";
        Map<String,Object> params = new HashMap<>();
        params.put("id",1);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, params);
        System.out.println("maps = " + maps);

    }

    @Test
    public void test2(){
        new TbUserTable().delete(StatementBuild.builder().addStatement("id","2"));

    }

    @Test
    public void test3(){
        List<RowEntity> select = new TbUserTable().select(StatementBuild.builder().addStatement("id", "3"));
        System.out.println(select);

    }
    @Test
    public void test4(){
        List<RowEntity> select = new TbUserTable().select(
                StatementBuild.builder().addStatement("id", "3", StatementOperator.LESS));
        System.out.println(select);

    }
}

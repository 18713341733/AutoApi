package com.example.autoapi.dac.table;

import com.example.autoapi.dac.model.RowEntity;
import com.example.autoapi.dac.model.StatementBuild;
import com.example.autoapi.dac.service.JdbcService;
import com.example.autoapi.dac.sql.SqlGenerate;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public abstract class AbstractTable {
    private String dbName;
    private String tableName;

    public int delete(StatementBuild build){
        Pair<String, Map<String, Object>> pair = SqlGenerate.genDeleteSql(dbName, tableName, build);
        return new JdbcService().modify(pair.getLeft(), pair.getRight());
    }

    public List<RowEntity> select(StatementBuild build){
        Pair<String, Map<String, Object>> pair =SqlGenerate.genSelectSql(dbName, tableName, build);
        return new JdbcService().query(pair.getLeft(), pair.getRight());
    }



}

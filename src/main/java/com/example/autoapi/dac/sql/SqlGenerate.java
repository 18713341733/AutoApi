package com.example.autoapi.dac.sql;

import com.example.autoapi.dac.model.StatementBuild;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.stream.Collectors;

public class SqlGenerate {
    public static final String SELECT_SQL_TEMP = "select * from %s.%s where 1=1 %s";
    public static final String DELETE_SQL_TEMP = "delete from %s.%s where 1=1 %s";
    public static Pair<String, Map<String, Object>>  genDeleteSql(String daName, String tableName , StatementBuild statementBuild){
        String str = statementBuild.getStatementFields().stream()
                // id=:id
                // statementField.getOperator().getOperator() 就是 = < >
                .map(statementField -> statementField.getFieldName()+statementField.getOperator().getOperator()+":"+statementField.getFieldName())
                .collect(Collectors.joining(" AND "));
        // str = id=:id AND name=:name

        if(StringUtils.isNotEmpty(str)){
            str = "AND "+str;
        }
        String sql = String.format(DELETE_SQL_TEMP, daName, tableName, str);
        // sql = delete from bt.bt_user where 1=1 AND id=:id AND name=:name

        Map<String, Object> params = statementBuild.getStatementFields().stream()
                .collect(Collectors.toMap(f -> f.getFieldName(), f -> f.getFieldValue()));
        // params = {name=zs, id=1}

        Pair<String, Map<String, Object>> pair = Pair.of(sql, params);
        // pair = (delete from bt.bt_user where 1=1 AND id=:id AND name=:name,{name=zs, id=1})
        return pair;

    }

    public static Pair<String, Map<String, Object>>  genSelectSql(String daName, String tableName , StatementBuild statementBuild){
        String str = statementBuild.getStatementFields().stream()
                // id=:id
                .map(statementField -> statementField.getFieldName()+statementField.getOperator().getOperator()+":"+statementField.getFieldName())
                .collect(Collectors.joining(" AND "));
        // str = id=:id AND name=:name

        if(StringUtils.isNotEmpty(str)){
            str = "AND "+str;
        }
        String sql = String.format(SELECT_SQL_TEMP, daName, tableName, str);
        // sql = delete from bt.bt_user where 1=1 AND id=:id AND name=:name

        Map<String, Object> params = statementBuild.getStatementFields().stream()
                .collect(Collectors.toMap(f -> f.getFieldName(), f -> f.getFieldValue()));
        // params = {name=zs, id=1}

        Pair<String, Map<String, Object>> pair = Pair.of(sql, params);
        // pair = (delete from bt.bt_user where 1=1 AND id=:id AND name=:name,{name=zs, id=1})
        return pair;

    }

    public static void main(String[] args) {
        StatementBuild statementBuild = new StatementBuild();
        statementBuild.addStatement("id","1");
//        statementBuild.addStatement("name","zs");

        Pair<String, Map<String, Object>> pair = genDeleteSql("bt", "bt_user", statementBuild);
        System.out.println(pair);
        // select from bt.tb_user where 1=1 AND id=:id
    }
}

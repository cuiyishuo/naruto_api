package com.solplatform.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * map和string转换
 *
 * @author sol
 * @create 2020-05-08  11:31 下午
 */
@MappedTypes(Map.class)
@MappedJdbcTypes(value = JdbcType.VARCHAR)
public class StringAndMapHandler extends BaseTypeHandler<Map> {

    // --- private methods ---

    /**
     * map数组转String
     */
    public static String mapToString(Map map) {
        StringBuilder mapStr = new StringBuilder ("{");
        Set<Map.Entry<String, String>> entries = map.entrySet ();
        Iterator<Map.Entry<String, String>> entryIterator = entries.iterator ();
        while (entryIterator.hasNext ()) {
            Map.Entry<String, String> entry = entryIterator.next ();
            mapStr.append (entry.getKey ()).append (":'").append (entry.getValue ()).append ("',");
        }
        mapStr.append ("}");
        return mapStr.toString ().replace (",}", "}");
    }

    /**
     * map数组转String
     */
    public static Map<String, String> StringToMap(String mapStr) {
        Map<String, String> map = new HashMap<> ();
        return map;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Map map, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public Map getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public Map getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public Map getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}

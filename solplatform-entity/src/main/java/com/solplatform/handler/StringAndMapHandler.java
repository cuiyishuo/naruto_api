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
@MappedJdbcTypes(value = JdbcType.BLOB)
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
        HashMap map = new HashMap ();
        return map;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Map map, JdbcType jdbcType) throws SQLException {
        String str = mapToString (map);
        preparedStatement.setString (i, str);
    }

    @Override
    public Map getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String str = resultSet.getString (columnName);
        return StringToMap (str);
    }

    @Override
    public Map getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String str = resultSet.getString (columnIndex);
        return StringToMap (str);
    }

    @Override
    public Map getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String str = callableStatement.getString (columnIndex);
        return StringToMap (str);
    }
}

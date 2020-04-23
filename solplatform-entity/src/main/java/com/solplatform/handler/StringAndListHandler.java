package com.solplatform.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 * Java list<>与MySQL String转换器
 *
 * @author sol
 * @create 2020-04-18  09:53
 */
@MappedTypes(List.class)
@MappedJdbcTypes(value = JdbcType.VARCHAR)
public class StringAndListHandler extends BaseTypeHandler<List> {


    // --- private methods ---

    /**
     * map数组转String
     */
    public static String arrayToString(List mapList) {
        String maplistStr = "[#]";
        Iterator<Map<String, Object>> it = mapList.iterator ();
        while (it.hasNext ()) {
            System.err.println ("list循环：" + maplistStr.split ("#")[0] + "{#},#" + maplistStr.split ("#")[1]);
            // 每循环一次增加一个断言对象 得到[{}]
            maplistStr = maplistStr.split ("#")[0] + "{#},#" + maplistStr.split ("#")[1];
            System.err.println (maplistStr);
            // 得到 {expression=das, expectValue=das, compareType=das} 形式
            Map<String, Object> map = it.next ();
            for (Map.Entry<String, Object> param : map.entrySet ()) {
                maplistStr = maplistStr.split ("#", 2)[0] + param.getKey () + ":'" + param.getValue () + "',#" + maplistStr.split ("#", 2)[1];
                System.out.println ("map循环：" + maplistStr);
            }
            maplistStr = maplistStr.replace (",#}", "}");
            System.out.println ("map循环结束：" + maplistStr);
        }
        maplistStr = maplistStr.replace (",#]", "]");
        return maplistStr;
    }

    /**
     * String转map数组
     */
    public static List stringToArray(String maplistStr) {
        List mapList = new ArrayList ();
        JSONArray jsonObject = JSONObject.parseArray (maplistStr);
        JSONObject jsonObject1;
        System.out.println (jsonObject);
        for (int i = 0; i < jsonObject.size (); i++) {
            jsonObject1 = (JSONObject) jsonObject.get (i);
            Iterator iter = jsonObject1.entrySet ().iterator ();
            Map map = new HashMap ();
            while (iter.hasNext ()) {
                Map.Entry entry = (Map.Entry) iter.next ();
                map.put (entry.getKey (), entry.getValue ());
            }
            mapList.add (map);
        }
        return mapList;
    }

    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List list, JdbcType jdbcType) throws SQLException {
        String str = arrayToString (list);
        preparedStatement.setString (i, str);
    }

    public List getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String str = resultSet.getString (columnName);
        return stringToArray (str);
    }

    public List getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String str = resultSet.getString (columnIndex);
        return stringToArray (str);
    }

    public List getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String str = callableStatement.getString (columnIndex);
        return stringToArray (str);
    }
}

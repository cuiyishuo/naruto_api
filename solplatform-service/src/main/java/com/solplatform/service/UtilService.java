package com.solplatform.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.solplatform.entity.HttpEntity;
import com.solplatform.exception.BusinessException;
import com.solplatform.vo.ResponseData;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.jni.OS;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * 测试工具业务
 *
 * @author sol
 * @create 2020-03-12  15:13
 */
@Component
public class UtilService {

    /**
     * 调用get请求接口
     *
     * @param httpEntity
     * @return
     */
    public ResponseData invokeGet(HttpEntity httpEntity) {

        CloseableHttpClient httpClient = HttpClients.createDefault ();
        URI uri = null;
        List<NameValuePair> paramsList = new ArrayList<NameValuePair> ();
        Map<String, String> paramsMap;
        Map<String, String> headerMap;
        String headers = httpEntity.getHeaders ();
        String params = httpEntity.getParams ();


        // 配置请求信息-params
        // 将map格式的params转换成uribuilder支持的键值对格式
        if (!StringUtils.isEmpty (params)) {
            paramsMap = JSON.parseObject (params, new TypeReference<Map<String, String>> () {
            });
            for (Map.Entry<String, String> param : paramsMap.entrySet ()) {
                paramsList.add (new BasicNameValuePair (param.getKey (), param.getValue ()));
            }
        }
        try {
            uri = new URIBuilder ().setScheme ("https")
                    .setHost (httpEntity.getHost ())
                    .setPath (httpEntity.getApiUrl ())
                    .addParameters (paramsList)
                    .setParameter ("debug", "1")
                    .build ();
        } catch (URISyntaxException e) {
            throw new BusinessException ("创建httpclient异常:" + e.getMessage ());
        }
        HttpGet httpGet = new HttpGet (uri);

        // 配置请求信息-headers
        // 将map格式的headers转换成uribuilder支持的键值对格式
        if (!StringUtils.isEmpty (headers)) {
            headerMap = JSON.parseObject (headers,new TypeReference<Map<String, String>> (){});
            for (Map.Entry<String, String> header : headerMap.entrySet ()) {
                httpGet.addHeader (header.getKey (), header.getValue ());
            }
        }

        // 请求参数配置
        Integer timeOut = Integer.valueOf (httpEntity.getTimeOut ());
        RequestConfig config = RequestConfig.custom ()
                // 链接上服务器的时间
                .setConnectTimeout (timeOut)
                // 响应时间
                .setSocketTimeout (timeOut)
                .build ();
        httpGet.setConfig (config);
        // 发送请求
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute (httpGet);
        } catch (IOException e) {
            throw new BusinessException ("发送http请求异常:" + e.getMessage ());
        }

        // 使用响应类实体接收响应信息
        // 状态码
        ResponseData responseData = new ResponseData ();
        responseData.setStatusCode (response.getStatusLine ().getStatusCode ());
        // 响应体
        List<Map<String, Object>> entityList = null;
        try {
            String entityStr = EntityUtils.toString (response.getEntity ());
            if (!StringUtils.isEmpty (entityStr)) {
                if (!entityStr.endsWith ("]"))
                    entityStr = "[" + entityStr + "]";
            }
            entityList = JSON.parseObject (entityStr, new TypeReference<List<Map<String, Object>>> () {
            });
        } catch (IOException e) {
            throw new BusinessException ("获取响应体异常:" + e.getMessage ());
        }
        responseData.setBody (entityList);
        // 响应头
        Header[] headersArray = response.getAllHeaders ();
        Map<String, String> headersMap = new HashMap<String, String> ();
        for (Header header : headersArray) {
            headersMap.put (header.getName (), header.getValue ());
        }
        responseData.setHeaders (headersMap);


        // 返回响应数据
        return responseData;

    }

    public ResponseData invokePost(HttpEntity httpEntity) {

        CloseableHttpClient httpClient = HttpClients.createDefault ();
        Map<String, String> paramsMap;
        URI uri;
        String headers = httpEntity.getHeaders ();
        String body = httpEntity.getBody ();

        // 配置请求信息
        try {
            uri = new URIBuilder ().setScheme ("https")
                    .setHost (httpEntity.getHost ())
                    .setPath (httpEntity.getApiUrl ())
                    .setParameter ("debug", "1")
                    .build ();
        } catch (URISyntaxException e) {
            throw new BusinessException ("创建httpclient异常:" + e.getMessage ());
        }
        HttpPost httpPost = new HttpPost (uri);

        // 直接传入json格式的-body
        JSONObject jsonObject = JSONObject.parseObject (body);
        StringEntity bodyString = new StringEntity (jsonObject.toString (), "UTF-8");
        httpPost.setEntity (bodyString);
        // 必须设置这个
        httpPost.addHeader ("Content-Type", "application/json;charset=UTF-8");

        // 配置请求信息-headers
        // 将map格式的headers转换成uribuilder支持的键值对格式
        if (!StringUtils.isEmpty (headers)) {
            paramsMap = JSON.parseObject (headers,new TypeReference<Map<String, String>> (){});
            for (Map.Entry<String, String> header : paramsMap.entrySet ()) {
                httpPost.addHeader (header.getKey (), header.getValue ());
            }
        }

        // 请求参数配置
        Integer timeOut = Integer.valueOf (httpEntity.getTimeOut ());
        RequestConfig config = RequestConfig.custom ()
                // 链接上服务器的时间
                .setConnectTimeout (timeOut)
                // 响应时间
                .setSocketTimeout (timeOut)
                .build ();
        httpPost.setConfig (config);

        // 发送请求
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute (httpPost);
        } catch (IOException e) {
            throw new BusinessException ("发送http请求异常:" + e.getMessage ());
        }

        // 使用响应类实体接收响应信息
        // 状态码
        ResponseData responseData = new ResponseData ();
        responseData.setStatusCode (response.getStatusLine ().getStatusCode ());
        // 响应体
        List<Map<String, Object>> entityList = null;
        try {
            String entityStr = EntityUtils.toString (response.getEntity ());
            if (!StringUtils.isEmpty (entityStr)) {
                if (!entityStr.endsWith ("]"))
                    entityStr = "[" + entityStr + "]";
            }
            entityList = JSON.parseObject (entityStr, new TypeReference<List<Map<String, Object>>> () {
            });
        } catch (IOException e) {
            throw new BusinessException ("获取响应体异常:" + e.getMessage ());
        }
        responseData.setBody (entityList);
        // 响应头
        Header[] headersArray = response.getAllHeaders ();
        Map<String, String> headersMap = new HashMap<String, String> ();
        for (Header header : headersArray) {
            headersMap.put (header.getName (), header.getValue ());
        }
        responseData.setHeaders (headersMap);


        // 返回响应数据
        return responseData;

    }
}

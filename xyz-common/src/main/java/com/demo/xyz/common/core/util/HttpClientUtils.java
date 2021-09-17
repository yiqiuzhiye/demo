package com.demo.xyz.common.core.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * httpclient工具类
 *
 * @author charles
 */
public class HttpClientUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    private static final String ENCODE_CHARSET_UTF8 = "UTF-8";
    /**
     * 连接超时
     */
    private static final int CONNECTION_TIMEOUT = 10 * 1000;

    private static final int SOCKET_TIMEOUT = 10 * 1000;

    public static SimpleHttpResponse doHttpRequest(HttpRequestBase httpRequest, Header[] headers) throws Exception {

        CloseableHttpClient httpclient = HttpClients.custom().build();
        long t1 = System.currentTimeMillis();
        try {
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT)
                    .setConnectTimeout(CONNECTION_TIMEOUT).build();
            httpRequest.setConfig(requestConfig);
            if (headers != null) {
                httpRequest.setHeaders(headers);
            }
            HttpResponse response = httpclient.execute(httpRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = null;
            if (response.getEntity() != null) {
                responseBody = EntityUtils.toString(response.getEntity(), ENCODE_CHARSET_UTF8);
            }
            return new SimpleHttpResponse(statusCode, responseBody);

        } catch (IOException e) {

            e.printStackTrace();
            throw new IOException("httpclient io异常！" + e.getMessage(), e);
        } finally {

            if (httpclient != null) {
                httpclient.close();
            }
            long t2 = System.currentTimeMillis();
            logger.info("######doRequest({}) cost:{}ms", httpRequest.getURI(), t2 - t1);

        }

    }

    public static SimpleHttpResponse doFormPost(String url, String params, Header[] headers) throws Exception {
        logger.info("do Post：url={},params={}", url, params);

        HttpPost httpPost = new HttpPost(url);
        if (StringUtils.isNotBlank(params)) {

            StringEntity entity = new StringEntity(params, ContentType.APPLICATION_FORM_URLENCODED);
            httpPost.setEntity(entity);
        }

        return doHttpRequest(httpPost, headers);

    }

    public static SimpleHttpResponse doFormPost(String url, String params) throws Exception {
        logger.info("do Post：url={},params={}", url, params);

        return doFormPost(url, params, null);

    }

    public static SimpleHttpResponse doPost(String url, String bodyJson) throws Exception {

        logger.info(">>>>>doPost url:{},body:{}", url, bodyJson);
        HttpPost httpPost = new HttpPost(url);
        if (StringUtils.isNotBlank(bodyJson)) {
            StringEntity reqEntity = new StringEntity(bodyJson, ContentType.APPLICATION_JSON);
            httpPost.setEntity(reqEntity);
        }
        return doHttpRequest(httpPost, null);

    }

    public static SimpleHttpResponse doPost(String url, String body, ContentType contentType) throws Exception {
        logger.info(">>>>>doPost url:{},body:{},contentType:", url, body, contentType);
        HttpPost httpPost = new HttpPost(url);
        if (StringUtils.isNotBlank(body)) {
            StringEntity reqEntity = new StringEntity(body, contentType);
            httpPost.setEntity(reqEntity);
        }
        return doHttpRequest(httpPost, null);
    }

    public static SimpleHttpResponse doPost(String url, String body, ContentType contentType, Header[] headers)
            throws Exception {
        logger.info(">>>>>doPost url:{},body:{},contentType:{},headers:{}", url, body, contentType, headers);
        HttpPost httpPost = new HttpPost(url);
        if (StringUtils.isNotBlank(body)) {
            StringEntity reqEntity = new StringEntity(body, contentType);
            httpPost.setEntity(reqEntity);
        }
        return doHttpRequest(httpPost, headers);
    }

    public static SimpleHttpResponse doGet(String url, Map<String, String> paramsMap, Header[] headers)
            throws Exception {

        HttpGet httpGet = new HttpGet();
        String queryStr = "";
        if (paramsMap != null) {

            Iterator<Map.Entry<String, String>> it = paramsMap.entrySet().iterator();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));

            }

            queryStr = EntityUtils.toString(new UrlEncodedFormEntity(params));

        }
        String wrapperUrl = url;
        if (!StringUtils.isBlank(queryStr)) {
            if (!url.contains("?")) {

                wrapperUrl += "?" + queryStr;
            } else {

                wrapperUrl += "&" + queryStr;
            }
        }
        logger.info("do Get：url={}.........", wrapperUrl);
        httpGet.setURI(new URI(wrapperUrl));
        return doHttpRequest(httpGet, headers);

    }

    public static class SimpleHttpResponse {

        public SimpleHttpResponse(int _statusCode, String _body) {
            this.statusCode = _statusCode;
            this.body = _body;
        }

        private int statusCode;

        private String body;

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getBody() {
            if (body != null) {
                return body.replaceAll("\\r\\n", "");

            }
            return body;
        }

        public void setBody(String body) {
            if (body != null) {
                this.body = body.replaceAll("\\r\\n", "");
            } else {
                this.body = body;
            }
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("SimpleHttpResponse [statusCode=");
            builder.append(statusCode);
            builder.append(", body=");
            builder.append(body);
            builder.append("]");
            return builder.toString();
        }

    }

    /**
     * 远程下载ROD文件
     *
     * @param url  文件地址
     * @param dest 目标文件或目录，当为目录时，取URL中的文件名，取不到使用编码后的URL做为文件名
     * @return 文件大小
     */
    public static long downloadRodFile(String url, String dest) {
        if (StrUtil.isBlank(dest)) {
            throw new NullPointerException("[dest] is null!");
        }
        if (StrUtil.isBlank(url)) {
            throw new NullPointerException("[url] is null!");
        }
        File destFile = FileUtil.file(dest);
        if (null == destFile) {
            throw new NullPointerException("[destFile] is null!");
        }
        // 超时，单位毫秒，-1表示默认超时
        int timeout = -1;
        cn.hutool.http.HttpResponse response = HttpRequest.get(url).timeout(timeout).executeAsync();
        if (response.isOk()) {
            return response.writeBody(destFile);
        } else {
            if (response.getStatus() == 302) {
                // 获取重定向地址，并重新请求
                String redirectUrl = response.header("Location");
                response = HttpRequest.get(redirectUrl).timeout(timeout).executeAsync();
                if (response.isOk()) {
                    return response.writeBody(destFile);
                } else {
                    throw new HttpException("Server response error with status code: [{}]", response.getStatus());
                }
            } else {
                throw new HttpException("Server response error with status code: [{}]", response.getStatus());
            }
        }
    }
}
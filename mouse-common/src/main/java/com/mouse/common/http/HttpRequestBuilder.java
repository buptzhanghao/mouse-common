package com.mouse.common.http;

import com.alibaba.fastjson.JSON;
import com.mouse.common.exception.HttpException;
import com.mouse.common.util.BasicAuth;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Map;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/7/1
 */
public class HttpRequestBuilder {

    private static final Logger log = LoggerFactory.getLogger(HttpRequestBuilder.class);

    private static volatile CloseableHttpClient httpClient;

    private static final int SOCKET_TIMEOUT = 5000;

    private static final int CONNECTION_TIMEOUT = 2000;

    static {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(new PoolingHttpClientConnectionManager());
        httpClient = httpClientBuilder.build();
    }

    public static <T> T execute(HttpUriRequest req, Class<T> returnType) {
        try {
            HttpResponse resp = httpClient.execute(req);
            HttpEntity ent = resp.getEntity();
            String content = ent != null ? EntityUtils.toString(ent, "UTF-8") : "";

            if ( resp.getStatusLine().getStatusCode() >= 200 && resp.getStatusLine().getStatusCode() < 300 ) {
                return JSON.parseObject(content, returnType);
            } else {
                throw new HttpException(resp.getStatusLine().getStatusCode(), resp.getStatusLine().getReasonPhrase(), content);
            }
        } catch ( IOException e ) {
            throw new HttpException(505, "IOException", e);
        }
    }

    public static <T> T doPut(String url, String content, Class<T> returnType, int sockedTimeoutMS, int connectTimeOutMS, Map<String, String> headers) {
        try {
            HttpPut put = new HttpPut(url);
            put.setConfig(getRequestConfig(sockedTimeoutMS, connectTimeOutMS));

            StringEntity body = new StringEntity(content, "UTF-8");
            put.setEntity(body);

            if ( headers != null || headers.size() != 0 ) {
                for ( String key : headers.keySet() ) {
                    put.addHeader(key, headers.get(key));
                }
            }

            return execute(put, returnType);
        } catch ( UnsupportedCharsetException e ) {
            throw new HttpException(506, "UnsupportedCharsetException:UTF-8", e);
        }
    }

    public static <T> T doPut(String url, String content, Class<T> returnType, Map<String, String> headers) {
        return doPut(url, content, returnType, -1, -1, headers);
    }

    public static <T> T doPutWithBasicAuth(String url, String content, Class<T> returnType, int sockedTimeoutMS, int connectTimeOutMS, Map<String, String> headers, String clientId, String secret) {
        try {
            HttpPut put = new HttpPut(url);
            put.setConfig(getRequestConfig(sockedTimeoutMS, connectTimeOutMS));

            StringEntity body = new StringEntity(content, "UTF-8");
            put.setEntity(body);

            if ( headers != null || headers.size() != 0 ) {
                for ( String key : headers.keySet() ) {
                    put.addHeader(key, headers.get(key));
                }
            }

            BasicAuth.addBasicAuth(put, clientId, secret);
            return execute(put, returnType);
        } catch ( UnsupportedCharsetException e ) {
            throw new HttpException(506, "UnsupportedCharsetException:UTF-8", e);
        }
    }

    public static <T> T doPutWithBasicAuth(String url, String content, Class<T> returnType, Map<String, String> headers, String clientId, String secret) {
        return doPutWithBasicAuth(url, content, returnType, -1, -1, headers, clientId, secret);
    }

    public static <T> T doPost(String url, String content, Class<T> returnType, int sockedTimeoutMS, int connectTimeOutMS, Map<String, String> headers) {
        try {
            HttpPost post = new HttpPost(url);
            post.setConfig(getRequestConfig(sockedTimeoutMS, connectTimeOutMS));

            StringEntity body = new StringEntity(content, "UTF-8");
            post.setEntity(body);

            if ( headers != null || headers.size() != 0 ) {
                for ( String key : headers.keySet() ) {
                    post.addHeader(key, headers.get(key));
                }
            }

            return execute(post, returnType);
        } catch ( UnsupportedCharsetException e ) {
            throw new HttpException(506, "UnsupportedCharsetException:UTF-8", e);
        }
    }

    public static <T> T doPost(String url, String content, Class<T> returnType, Map<String, String> headers) {
        return doPost(url, content, returnType, -1, -1, headers);
    }

    public static <T> T doPostWithBasicAuth(String url, String content, Class<T> returnType, int sockedTimeoutMS, int connectTimeOutMS, Map<String, String> headers, String clientId, String secret) {
        try {
            HttpPost post = new HttpPost(url);
            post.setConfig(getRequestConfig(sockedTimeoutMS, connectTimeOutMS));

            StringEntity body = new StringEntity(content, "UTF-8");
            post.setEntity(body);

            if ( headers != null || headers.size() != 0 ) {
                for ( String key : headers.keySet() ) {
                    post.addHeader(key, headers.get(key));
                }
            }

            BasicAuth.addBasicAuth(post, clientId, secret);
            return execute(post, returnType);
        } catch ( UnsupportedCharsetException e ) {
            throw new HttpException(506, "UnsupportedCharsetException:UTF-8", e);
        }
    }

    public static <T> T doPostWithBasicAuth(String url, String content, Class<T> returnType, Map<String, String> headers, String clientId, String secret) {
        return doPostWithBasicAuth(url, content, returnType, -1, -1, headers, clientId, secret);
    }

    public static <T> T doGet(String url, Class<T> returnType, int sockedTimeoutMS, int connectTimeOutMS, Map<String, String> headers) {
        HttpGet get = new HttpGet(url);
        get.setConfig(getRequestConfig(sockedTimeoutMS, connectTimeOutMS));

        if ( headers != null || headers.size() != 0 ) {
            for ( String key : headers.keySet() ) {
                get.addHeader(key, headers.get(key));
            }
        }

        return execute(get, returnType);
    }

    public static <T> T doGet(String url, Class<T> returnType, Map<String, String> headers) {
        return doGet(url, returnType, -1, -1, headers);
    }

    public static <T> T doGetWithBasicAuth(String url, Class<T> returnType, Map<String, String> headers, String clientId, String secret) {
        return doGetWithBasicAuth(url, returnType, -1, -1, headers, clientId, secret);
    }

    public static <T> T doGetWithBasicAuth(String url, Class<T> returnType, int sockedTimeoutMS, int connectTimeOutMS, Map<String, String> headers, String clientId, String secret) {
        HttpGet get = new HttpGet(url);
        get.setConfig(getRequestConfig(sockedTimeoutMS, connectTimeOutMS));

        if ( headers != null || headers.size() != 0 ) {
            for ( String key : headers.keySet() ) {
                get.addHeader(key, headers.get(key));
            }
        }

        BasicAuth.addBasicAuth(get, clientId, secret);
        return execute(get, returnType);
    }

    public static boolean rebuildHttpClient() {
        try {
            if ( httpClient != null ) {
                httpClient.close();
            }

            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            httpClientBuilder.setConnectionManager(new PoolingHttpClientConnectionManager());
            httpClient = httpClientBuilder.build();
            return true;
        } catch ( Exception e ) {
            return false;
        }
    }


    private static RequestConfig getRequestConfig(int sockedTimeoutMS, int connectTimeOutMS) {
        if ( sockedTimeoutMS <= 0 ) {
            sockedTimeoutMS = SOCKET_TIMEOUT;
        }
        if ( connectTimeOutMS <= 0 ) {
            connectTimeOutMS = CONNECTION_TIMEOUT;
        }

        return RequestConfig.copy(RequestConfig.DEFAULT)
                .setSocketTimeout(sockedTimeoutMS)
                .setConnectionRequestTimeout(connectTimeOutMS).build();
    }
}

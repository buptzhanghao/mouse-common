package com.mouse.common.http;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/6/23
 *
 * Build response object for HTTP request
 */

public class HttpResponseBuilder {

    private static final Logger log = LoggerFactory.getLogger(HttpResponseBuilder.class);

    private static final String RES_CODE = "rescode";

    private static final String RES_DATA = "data";

    private static final String RES_MESSAGE = "message";

    public static final int SUCCESS = 0;

    public static final int ERROR = 1;


    public static JSONObject success( Object data ) {
        JSONObject obj = new JSONObject();
        obj.put(RES_CODE, SUCCESS);
        if ( data != null ) {
            obj.put(RES_DATA, data);
        } else {
            obj.put(RES_MESSAGE, "empty result from server.");
        }
        return obj;
    }

    public static JSONObject success( Object data, String message ) {
        JSONObject obj = new JSONObject();
        obj.put(RES_CODE, SUCCESS);
        if ( data != null ) {
            obj.put(RES_DATA, data);
        } else {
            obj.put(RES_MESSAGE, "empty result from server.");
        }
        if ( message != null ) {
            obj.put(RES_MESSAGE, message);
        }
        return obj;
    }

    public static JSONObject error( String message ) {
        JSONObject obj = new JSONObject();
        obj.put(RES_CODE, ERROR);
        if ( message != null ) {
            obj.put(RES_MESSAGE, message);
        }
        return obj;
    }

    public static JSONObject error(Object data, String message) {
        JSONObject obj = new JSONObject();
        obj.put(RES_CODE, ERROR);
        if ( message != null ) {
            obj.put(RES_MESSAGE, message);
        }
        if ( data != null ) {
            obj.put(RES_DATA, data);
        }
        return obj;
    }
}

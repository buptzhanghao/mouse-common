package com.mouse.common.boot;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/7/1
 */
public class MappingFastJsonXssHttpMessageConverter extends MappingFastJsonHttpMessageConverter {
    public MappingFastJsonXssHttpMessageConverter() {
    }

    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream in = inputMessage.getBody();
        byte[] buf = new byte[1024];

        while(true) {
            int body = in.read(buf);
            if(body == -1) {
                String body1 = baos.toString(UTF8.name());
                Object result = JSON.parseObject(this.escapeXSS(body1), clazz);
                return result;
            }

            if(body > 0) {
                baos.write(buf, 0, body);
            }
        }
    }

    private String escapeXSS(String value) {
        if(value == null) {
            return null;
        } else {
            String tmp = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
            tmp = tmp.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
            tmp = tmp.replaceAll("\'", "&#39;");
            tmp = tmp.replaceAll("[\"\'][\\s]*javascript:(.*)[\"\']", "\"\"");
            return tmp;
        }
    }
}

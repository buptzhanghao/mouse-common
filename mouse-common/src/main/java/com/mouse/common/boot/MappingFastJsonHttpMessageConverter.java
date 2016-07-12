package com.mouse.common.boot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/7/1
 */
public class MappingFastJsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {
    public static final Charset UTF8 = Charset.forName("UTF-8");
    private Charset charset;
    private SerializerFeature[] serializerFeature;

    public SerializerFeature[] getSerializerFeature() {
        return this.serializerFeature;
    }

    public void setSerializerFeature(SerializerFeature[] serializerFeature) {
        this.serializerFeature = serializerFeature;
    }

    public MappingFastJsonHttpMessageConverter() {
        super(new MediaType("application", "json", UTF8));
        this.charset = UTF8;
        this.serializerFeature = new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.QuoteFieldNames};
    }

    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return true;
    }

    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return true;
    }

    protected boolean supports(Class<?> clazz) {
        throw new UnsupportedOperationException();
    }

    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream in = inputMessage.getBody();
        byte[] buf = new byte[1024];

        while(true) {
            int bytes = in.read(buf);
            if(bytes == -1) {
                byte[] bytes1 = baos.toByteArray();
                if(this.charset == UTF8) {
                    return JSON.parseObject(bytes1, clazz, new Feature[0]);
                }

                return JSON.parseObject(bytes1, 0, bytes1.length, this.charset.newDecoder(), clazz, new Feature[0]);
            }

            if(bytes > 0) {
                baos.write(buf, 0, bytes);
            }
        }
    }

    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream out = outputMessage.getBody();
        byte[] bytes;
        if(this.charset == UTF8) {
            if(this.serializerFeature != null) {
                bytes = JSON.toJSONBytes(obj, this.serializerFeature);
            } else {
                bytes = JSON.toJSONBytes(obj, new SerializerFeature[0]);
            }
        } else {
            String text;
            if(this.serializerFeature != null) {
                text = JSON.toJSONString(obj, this.serializerFeature);
            } else {
                text = JSON.toJSONString(obj);
            }

            bytes = text.getBytes(this.charset);
        }

        out.write(bytes);
    }
}


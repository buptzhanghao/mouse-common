package com.mouse.common.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/6/30
 *
 * 从config.properties中加载配置信息
 *
 */
public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);

    private static final String SPLITOR = "[\\s,;，；]+";

    public static String get(String key) {
        return System.getProperty(key);
    }

    public static String get (String key,String defaultValue) {
        return System.getProperty(key,defaultValue);
    }

    public static void set(String key,String value) {
        System.setProperty(key, value);
    }

    public static String[] getArray(String key) {
        String value = get(key);
        return value == null ? null : value.split(SPLITOR);
    }

    public static String getAppkey() {
        return get("jetty.appkey");
    }

    public static String getApptoken() {
        return get("jetty.apptoken");
    }

    public static int getInt(String key, int defaultValue) {
        String value = get(key);
        return value == null ? defaultValue : Integer.parseInt(value);
    }
    public static void reload() throws IOException {
        InputStream input = Config.class.getResourceAsStream("/config.properties");
        if ( input != null ) {
            System.getProperties().load(input);
            log.info("Config Loaded...");
        } else {
            log.warn("No /config.properties found to load...");
        }
    }
}

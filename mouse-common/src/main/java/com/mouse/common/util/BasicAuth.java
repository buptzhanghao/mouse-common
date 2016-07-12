package com.mouse.common.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhanghao
 * @version 1.0
 * @created 15/9/23
 *
 * refer to : http://wiki.sankuai.com/pages/viewpage.action?pageId=29755412
 */
public class BasicAuth {

    private static Logger log = LoggerFactory.getLogger(BasicAuth.class);

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    private static final Properties clientSecrets;

    private static Pattern authPattern = Pattern.compile("MWS ([\\w/._-]+):([\\w=/+]+)");

    private static final int SIGN_PERIOD = 2 * 60 * 1000;

    static {
        clientSecrets = loadSecrets();
        if (clientSecrets == null) {
            log.error("Failed to load secrets, exit");
            System.exit(-1);
        }
    }

    public static String getClientSecret(String clientId) {
        return clientSecrets.getProperty(clientId);
    }

    public static Date toDate(String date) {
        return DateUtil.getBasicAuthDate(date);
    }

    public static String getSignature(String data, String key) {
        String result;

        try {
            // get an hmac_sha1 key from the raw key bytes
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);

            // get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);

            // compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(data.getBytes());

            result = Base64.encodeBase64String(rawHmac);
        } catch ( Exception e ) {
            throw new RuntimeException("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }

    public static boolean checkSign(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String auth = request.getHeader("Authorization");
        String date = request.getHeader("Date");
        if ( auth == null || date == null ) {
            return false;
        }

        Date date1 = toDate(date);
        long passed = System.currentTimeMillis() - date1.getTime();
        if ( Math.abs(passed) > SIGN_PERIOD ) {
            return false;
        }

        Matcher m = authPattern.matcher(auth);
        if ( !m.matches() ) {
            return false;
        }
        String client = m.group(1);
        String sign = m.group(2);

        String secret = getClientSecret(client);
        if ( secret == null ) {
            return false;
        }

        String stringToSign = request.getMethod() + " " + uri + "\n" + date;
        String check = getSignature(stringToSign, secret);

        if ( !sign.equals(check) ) {
            return false;
        }
        return true;
    }

    public static void addBasicAuth(HttpUriRequest request, String clientId) {
        String uri = request.getURI().getPath();
        String method = request.getMethod();
        String date = DateUtil.getBasicAuthString(new Date());
        String stringToSign = method + " " + uri + "\n" + date;
        String secret = getClientSecret(clientId);

        String sign = getSignature(stringToSign, secret);
        String auth = "MWS" + " " + clientId + ":" + sign;

        request.addHeader("Authorization", auth);
        request.addHeader("Date", date);
    }

    public static void addBasicAuth(HttpUriRequest request, String clientId, String secret) {
        String uri = request.getURI().getPath();
        String method = request.getMethod();
        String date = DateUtil.getBasicAuthString(new Date());
        String stringToSign = method + " " + uri + "\n" + date;

        String sign = getSignature(stringToSign, secret);
        String auth = "MWS" + " " + clientId + ":" + sign;

        request.addHeader("Authorization", auth);
        request.addHeader("Date", date);
    }

    /**
     * load secrets from /secrets.properties
     */
    private static Properties loadSecrets() {
        InputStream input = BasicAuth.class.getResourceAsStream("/secrets.properties");
        if (input == null) {
            log.error("Secrets config file does not exist: " + "/secrets.properties");
            return null;
        }

        Properties secrets = new Properties();
        try {
            secrets.load(new InputStreamReader(input, "UTF-8"));
            input.close();
        } catch (IOException e) {
            log.error("Failed to load secrets.properties");
            return null;
        }
        return secrets;
    }
}

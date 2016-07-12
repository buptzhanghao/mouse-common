package com.mouse.common.http;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/6/23
 *
 * 提取 HTTP Param
 */
public class ReqParamExtractor {

    private final JSONObject request;

    public ReqParamExtractor(JSONObject request) {
        this.request = request;
    }

    public long getUserUID() {
        checkParamExist(ReqParamMapping.UID);
        long uid = request.getLongValue(ReqParamMapping.UID);
        Preconditions.checkArgument(uid >= 0, "%s=%s should >= 0", ReqParamMapping.UID, uid);
        return uid;
    }

    public short getAppId() {
        checkParamExist(ReqParamMapping.APP);
        short appId = request.getShortValue(ReqParamMapping.APP);
        Preconditions.checkArgument(appId >= 0, "%s=%s should >= 0", ReqParamMapping.APP, appId);
        return appId;
    }

    public long getTimestamp() {
        checkParamExist(ReqParamMapping.TIMESTAMP);
        long timestamp = request.getLongValue(ReqParamMapping.TIMESTAMP);
        Preconditions.checkArgument(timestamp >= 0, "%s=%s should >= 0", ReqParamMapping.TIMESTAMP, timestamp);
        return timestamp;
    }

    public int getOffset() {
        checkParamExist(ReqParamMapping.OFFSET);
        int offset = request.getIntValue(ReqParamMapping.OFFSET);
        Preconditions.checkArgument(offset >= 0, "%s=%s should >= 0", ReqParamMapping.OFFSET, offset);
        return offset;
    }

    public int getLimit() {
        checkParamExist(ReqParamMapping.LIMIT);
        int limit = request.getIntValue(ReqParamMapping.LIMIT);
        Preconditions.checkArgument(limit > 0, "%s=%s should > 0", ReqParamMapping.LIMIT, limit);
        return limit;
    }

    public int getCID() {
        checkParamExist(ReqParamMapping.CID);
        int cid = request.getIntValue(ReqParamMapping.CID);
        Preconditions.checkArgument(cid >= 0, "%s=%s should >= 0", ReqParamMapping.CID, cid);
        return cid;
    }

    public String getKeyWord() {
        checkParamExist(ReqParamMapping.KEYWORD);
        String keyword = request.getString(ReqParamMapping.KEYWORD);
        Preconditions.checkArgument(keyword != null, "%s=%s should not be null ", ReqParamMapping.KEYWORD, keyword);
        return keyword;
    }

    public String getPassport() {
        checkParamExist(ReqParamMapping.PASSPORT);
        String passport = request.getString(ReqParamMapping.PASSPORT);
        Preconditions.checkArgument(passport != null, "%s=%s should not be null ", ReqParamMapping.PASSPORT, passport);
        return passport;
    }

    public Set<String> getPassports() {
        checkParamExist(ReqParamMapping.PASSPORT_LIST);
        JSONArray passports = request.getJSONArray(ReqParamMapping.PASSPORT_LIST);
        Preconditions.checkArgument(passports != null && passports.size() != 0, "%s=%s should not be null ",
                ReqParamMapping.PASSPORT_LIST, passports);

        Set<String> set = Sets.newHashSet();
        for (Object obj : passports) {
            set.add((String) obj);
        }
        return set;
    }

    public Set<Long> getUids() {
        checkParamExist(ReqParamMapping.UID_LIST);
        JSONArray uids = request.getJSONArray(ReqParamMapping.UID_LIST);
        Preconditions.checkArgument(uids != null && uids.size() != 0, "%s=%s should not be null ",
                ReqParamMapping.UID_LIST, uids);

        Set<Long> set = Sets.newHashSet();
        for ( int i = 0 ; i != uids.size() ; i ++ ) {
            set.add(uids.getLong(i));
        }
        return set;
    }

    public long getStartTime() {
        checkParamExist(ReqParamMapping.STAR_TTIME);
        long start = request.getLongValue(ReqParamMapping.STAR_TTIME);
        Preconditions.checkArgument(start >= 0, "%s=%s should >= 0", ReqParamMapping.STAR_TTIME, start);
        return start;
    }

    public long getEndTime() {
        checkParamExist(ReqParamMapping.END_TIME);
        long end = request.getLongValue(ReqParamMapping.END_TIME);
        Preconditions.checkArgument(end >= 0, "%s=%s should >= 0", ReqParamMapping.END_TIME, end);
        return end;
    }

    public String getMobile() {
        checkParamExist(ReqParamMapping.MOBILE);
        String mobile = request.getString(ReqParamMapping.MOBILE);
        Preconditions.checkArgument(mobile != null, "%s=%s should not be null ", ReqParamMapping.MOBILE, mobile);
        return mobile;
    }

    public String getCompanyName() {
        checkParamExist(ReqParamMapping.COMPANY_NAME);
        String companyName = request.getString(ReqParamMapping.COMPANY_NAME);
        Preconditions.checkArgument(companyName  != null, "%s=%s should not be null ", ReqParamMapping.COMPANY_NAME, companyName );
        return companyName ;
    }

    private void checkParamExist(String key) {
        if ( !request.containsKey(key) ) {
            throw new IllegalArgumentException("Missed param: '" + key + "'");
        }
    }
}

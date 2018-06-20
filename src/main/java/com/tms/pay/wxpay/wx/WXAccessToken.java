package com.tms.pay.wxpay.wx;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;


public class WXAccessToken implements Serializable {
    private static final long serialVersionUID = 1L;
    private String accessToken;
    private Integer expiresIn;

    public WXAccessToken() {
    }

    public WXAccessToken(String jsonStr) {
        try {
            JSONObject obj = new JSONObject(jsonStr);
            this.accessToken = obj.getString("access_token");
            this.expiresIn = obj.getInt("expires_in");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
}

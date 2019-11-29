package com.schindler.ioee.tm_service.response;

import com.alibaba.fastjson.JSONObject;
/**
 * @author Jensen
 */
public class RestErrorResponse {
    private Integer errorCode;
    private String message;
    private Object details;
    private JSONObject result;


    private RestErrorResponse() {
    }

    public static RestErrorResponse build() {
        return new RestErrorResponse();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public RestErrorResponse setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RestErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getDetails() {
        return details;
    }

    public RestErrorResponse setDetails(Object details) {
        this.details = details;
        return this;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return getResult();
    }

    public String getResult() {
        result = new JSONObject();
        JSONObject errorJson = new JSONObject();
        errorJson.put("code", errorCode);
        errorJson.put("message", message);
        errorJson.put("details", details);
        result.put("error", errorJson);
        return result.toString();
    }


}

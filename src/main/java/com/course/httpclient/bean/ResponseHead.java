package com.course.httpclient.bean;

public class ResponseHead {

    Header heads;

    public Header getHeads() {
        return heads;
    }

    public void setHeads(Header heads) {
        this.heads = heads;
    }

    public static class Header{
        int code;
        String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}

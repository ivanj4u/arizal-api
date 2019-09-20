package com.arizal.api.util;

public class Constants {

    public enum RESPONSE {
        APPROVED("false", "Approved"),
        INVALID_ACCOUNT_NOT_FOUND("true", "Account Not Found"),
        WRONG_FORMAT("true", "Wrong Format"),
        ;

        private String code, desc;

        RESPONSE(String code, String desc) {
            setCode(code);
            setDesc(desc);
        }

        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getDesc() {
            return desc;
        }
        public void setDesc(String name) {
            this.desc = name;
        }
    }
}

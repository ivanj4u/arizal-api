package com.arizal.api.util;

import org.slf4j.Logger;
import org.springframework.validation.BindingResult;

import java.util.HashMap;

public class ValidationHelper {

    public static boolean fieldValidation(BindingResult result, HashMap<String, Object> response, Logger logger){
        if (result.hasErrors()) {
            response.put("isError", Constants.RESPONSE.WRONG_FORMAT.getCode());
            response.put("message", "Format Data field  " + result.getFieldError().getField()  + " tidak benar ");
            logger.error("Format Data field  " + result.getFieldError().getField()  + " tidak benar ");
            return false;
        }
        return true;
    }
}

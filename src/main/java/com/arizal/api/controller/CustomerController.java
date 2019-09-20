package com.arizal.api.controller;

import com.arizal.api.dto.customer.RestCustomerAddRequest;
import com.arizal.api.dto.customer.RestCustomerDeleteRequest;
import com.arizal.api.dto.customer.RestCustomerInquiryRequest;
import com.arizal.api.dto.customer.RestCustomerUpdateRequest;
import com.arizal.api.entity.Customer;
import com.arizal.api.model.CustomerServices;
import com.arizal.api.util.ValidationHelper;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired private CustomerServices customerServices;

    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @ApiOperation(value = "Add Customer", response = Customer.class)
    @PreAuthorize("#oauth2.hasScope('INSERT')")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object add(@RequestBody @Valid RestCustomerAddRequest req, BindingResult result) throws Exception {
        logger.info("Incoming Get customer/add/" + req.getName() + "-" + req.getBirthDate());
        Object res;
        HashMap<String, Object> response = new HashMap<>();

        if (!ValidationHelper.fieldValidation(result, response, logger)) {
            return response;
        }
        try {
            res = customerServices.addCustomer(req);
        } catch (Exception e) {
            throw e;
        }
        logger.info("OutGoing Get customer/add/" + req.getName() + "-" + req.getBirthDate());
        return res;
    }

    @ApiOperation(value = "Update Customer", response = Customer.class)
    @PreAuthorize("#oauth2.hasScope('UPDATE')")
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object update(@RequestBody @Valid RestCustomerUpdateRequest req, BindingResult result) throws Exception {
        logger.info("Incoming Get customer/update/" + req.getName() + "-" + req.getBirthDate());
        Object res;
        HashMap<String, Object> response = new HashMap<>();

        if (!ValidationHelper.fieldValidation(result, response, logger)) {
            return response;
        }
        try {
            res = customerServices.updateCustomer(req);
        } catch (Exception e) {
            throw e;
        }
        logger.info("OutGoing Get customer/update/" + req.getName() + "-" + req.getBirthDate());
        return res;
    }

    @ApiOperation(value = "Delete Customer", response = Boolean.class)
    @PreAuthorize("#oauth2.hasScope('DELETE')")
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object delete(@RequestBody @Valid RestCustomerDeleteRequest req, BindingResult result) throws Exception {
        logger.info("Incoming Get customer/delete/" + req.getId());
        Object res;
        HashMap<String, Object> response = new HashMap<>();

        if (!ValidationHelper.fieldValidation(result, response, logger)) {
            return response;
        }
        try {
            res = customerServices.deleteCustomer(req);
        } catch (Exception e) {
            throw e;
        }
        logger.info("OutGoing Get customer/inquiry/" + req.getId());
        return res;
    }

    @ApiOperation(value = "Inquiry Customer", response = Customer.class)
    @PreAuthorize("#oauth2.hasScope('READ')")
    @RequestMapping(value = "/inquiry", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object inquiry(@RequestBody @Valid RestCustomerInquiryRequest req, BindingResult result) throws Exception {
        logger.info("Incoming Get customer/inquiry/" + req.getName() + "-" + req.getBirthDate());
        Object res;
        HashMap<String, Object> response = new HashMap<>();

        if (!ValidationHelper.fieldValidation(result, response, logger)) {
            return response;
        }
        try {
            res = customerServices.inquiryCustomer(req);
        } catch (Exception e) {
            throw e;
        }
        logger.info("OutGoing Get customer/inquiry/" + req.getName() + "-" + req.getBirthDate());
        return res;
    }

}

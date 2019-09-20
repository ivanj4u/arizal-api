package com.arizal.api.model;

import com.arizal.api.dto.customer.RestCustomerAddRequest;
import com.arizal.api.dto.customer.RestCustomerDeleteRequest;
import com.arizal.api.dto.customer.RestCustomerInquiryRequest;
import com.arizal.api.dto.customer.RestCustomerUpdateRequest;
import com.arizal.api.entity.Customer;
import com.arizal.api.exception.AccountNotFoundException;
import com.arizal.api.util.Constants;
import com.arizal.api.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServices {

    @Autowired private CustomerDao customerDao;

    private final static Logger logger = LoggerFactory.getLogger(CustomerServices.class);

    public Object addCustomer(RestCustomerAddRequest req) throws Exception {
        logger.info("Start Services addCustomer : " + req.getName() + "-" + req.getBirthDate());
        Object response;
        try {
            Customer customer = new Customer();
            customer.setName(req.getName().toUpperCase());
            customer.setBirthDate(req.getBirthDate());
            customerDao.save(customer);
            response = ResponseUtil.setResponse(Constants.RESPONSE.APPROVED, customer, true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw e;
        }
        logger.info("End Services addCustomer : " + req.getName() + "-" + req.getBirthDate());
        return response;
    }

    public Object updateCustomer(RestCustomerUpdateRequest req) throws Exception {
        logger.info("Start Services updateCustomer : " + req.getId() + "-" + req.getName() + "-" + req.getBirthDate());
        Object response;
        try {
            Customer customer = customerDao.findById(Long.parseLong(req.getId())).orElseThrow(() -> new AccountNotFoundException("Customer : " + req.getId() + " not found"));
            customer.setName(req.getName().toUpperCase());
            customer.setBirthDate(req.getBirthDate());
            customerDao.save(customer);
            response = ResponseUtil.setResponse(Constants.RESPONSE.APPROVED, customer, true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw e;
        }
        logger.info("End Services updateCustomer : " + req.getId() + "-" + req.getName() + "-" + req.getBirthDate());
        return response;
    }

    public Object deleteCustomer(RestCustomerDeleteRequest req) throws Exception {
        logger.info("Start Services deleteCustomer : " + req.getId());
        Object response;
        try {
            Customer customer = customerDao.findById(Long.parseLong(req.getId())).orElseThrow(() -> new AccountNotFoundException("Customer : " + req.getId() + " not found"));
            customerDao.delete(customer);
            response = ResponseUtil.setResponse(Constants.RESPONSE.APPROVED, true, false);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw e;
        }
        logger.info("End Services deleteCustomer : " + req.getId());
        return response;
    }

    public Object inquiryCustomer(RestCustomerInquiryRequest req) throws Exception {
        logger.info("Start Services inquiryCustomer : " + req.getName() + "-" + req.getBirthDate());
        Object response;
        try {
            List<Customer> listCust;
            if (req.getBirthDate() != null && !"".equals(req.getBirthDate())) {
                String name = req.getName().toUpperCase() + "%";
                listCust = customerDao.findByUsernameAndBirthDate(name, req.getBirthDate());
            } else {
                String name = req.getName().toUpperCase() + "%";
                listCust = customerDao.findByUsername(name);
            }
            if (listCust.size() == 0) {
                response = ResponseUtil.setResponse(Constants.RESPONSE.INVALID_ACCOUNT_NOT_FOUND, null, false);
                return response;
            }
            response = ResponseUtil.setResponse(Constants.RESPONSE.APPROVED, listCust, true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw e;
        }
        logger.info("End Services inquiryCustomer : " + req.getName() + "-" + req.getBirthDate());
        return response;
    }

}

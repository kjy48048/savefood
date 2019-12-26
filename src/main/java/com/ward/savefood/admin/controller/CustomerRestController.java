package com.ward.savefood.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ward.savefood.common.controller.GeneralController;
import com.ward.savefood.admin.model.UpdateCustomerRequest;
import com.ward.savefood.admin.service.CustomerService;;

@RestController
@RequestMapping("/api/admin/customer")
public class CustomerRestController extends GeneralController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/update")
	public ResponseEntity<?> updateCustomer(@Valid @RequestBody List<UpdateCustomerRequest> customerRequest, BindingResult bindingResult) {
		logger.info("CustomerRestController updateCustomer : "+ customerRequest.toString());
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return customerService.updateCustomer(customerRequest);
	}
	
}

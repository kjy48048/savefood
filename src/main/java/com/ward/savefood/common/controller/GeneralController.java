package com.ward.savefood.common.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GeneralController {
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	protected HttpSession session;
}

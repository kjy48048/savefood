package com.ward.savefood.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ward.savefood.admin.dao.CustomerDao;
import com.ward.savefood.admin.model.UpdateCustomerRequest;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private PlatformTransactionManager transactionManager;
	
	private DefaultTransactionDefinition def = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);

	public ResponseEntity<?> updateCustomer(List<UpdateCustomerRequest> updateCustomerRequest) {
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {						
			int updateResult = 0;
			
			for(int i = 0; i < updateCustomerRequest.size(); i++) {	
				int memberSeq = updateCustomerRequest.get(i).getMemberSeq();
				int memberRoleId = updateCustomerRequest.get(i).getMemberRoleId();
				int memberStatusId = updateCustomerRequest.get(i).getMemberStatusId();
				
				if(memberSeq == 0) {
					return new ResponseEntity<>("input Customer info", HttpStatus.NO_CONTENT);
				}
				Map<String, Object> updateCustomer = new HashMap<String, Object>();
				updateCustomer.put("memberSeq", memberSeq);
				updateCustomer.put("memberRoleId", memberRoleId);
				updateCustomer.put("memberStatusId", memberStatusId);
				updateResult += customerDao.updateCustomer(updateCustomer);		
			}
			
			if(updateResult > 0) {
				transactionManager.commit(status);
				return new ResponseEntity<>("OK", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		transactionManager.rollback(status);
		return new ResponseEntity<>("fail to update customer", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ArrayList<Map<String, Object>> selectMemberList(int page, int order, boolean isEditable) {	
		
		try {				
			Map<String, Object> selectQuary = new HashMap<String, Object>();
			selectQuary.put("page", page);
			selectQuary.put("isEditable", isEditable);
			selectQuary.put("order", order);
			ArrayList<Map<String, Object>> selectResult = customerDao.selectMemberList(selectQuary);
			
			return selectResult;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Map<String, Object>> selectRoleList() {	
		try {	

			ArrayList<Map<String, Object>> selectResult = customerDao.selectRoleList();
			
			return selectResult;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ArrayList<Map<String, Object>> selectStatusList() {	
		try {	

			ArrayList<Map<String, Object>> selectResult = customerDao.selectStatusList();
			
			return selectResult;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public int selectPager() {	
		try {	

			Map<String, Object> selectResult = customerDao.selectPager();
			int maxPage = (int)selectResult.get("cnt");
			
			return maxPage/100 + 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}

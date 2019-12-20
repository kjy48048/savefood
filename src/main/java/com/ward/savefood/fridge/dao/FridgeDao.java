package com.ward.savefood.fridge.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface FridgeDao {
	List<Map<String, Object>> getStorage();
}

package com.ward.savefood.fridge.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface FridgeDao {
	List<Map<String, Object>> getStorage();

	String getMemberSeq(String memberId);
	ArrayList<Map<String, Object>> getFridgeList(String memberSeq);
	ArrayList<Map<String, Object>> getSaveplace(Map<String, Object> fridge);
	ArrayList<Map<String, Object>> getSavefoods(Map<String, Object> saveplace);
	
	ArrayList<Map<String, Object>> getSaveplaceList(String fridgeSeq);
	int insertSaveplace(Map<String, Object> insertSaveplace);
	int updateSaveplace(Map<String, Object> updateSaveplace);
	int deleteSaveplace(Map<String, Object> deleteSaveplace);

}

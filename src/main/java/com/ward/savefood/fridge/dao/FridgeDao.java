package com.ward.savefood.fridge.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FridgeDao {
	List<Map<String, Object>> getStorage();

	String getMemberSeq(String memberId);
	ArrayList<Map<String, Object>> getFridgeList(String memberSeq);
	ArrayList<Map<String, Object>> getSaveplace(@Param("fridgeSeqList")int[] fridgeSeqList);
	ArrayList<Map<String, Object>> getSavefoodList(@Param("saveplaceSeqList")int[] saveplaceSeqList);
	
	ArrayList<Map<String, Object>> getSaveplaceList(String fridgeSeq);
	int insertSaveplace(Map<String, Object> insertSaveplace);
	int deleteSaveplace(Map<String, Object> deleteSaveplace);

}

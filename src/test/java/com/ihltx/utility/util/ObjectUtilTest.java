package com.ihltx.utility.util;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import com.ihltx.utility.UtilityApplication;
import com.ihltx.utility.util.entity.Result;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SuppressWarnings("all")
@SpringBootTest(classes = {UtilityApplication.class})

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class ObjectUtilTest {
	
	
	@Test
	public void test_10_object2Map() {
		Result obj =new Result();
		obj.setStatus("0");
		obj.setMsg("success");
		obj.setData(12.5f);
		Map<String, Object> map = null;
		try {
			map = ObjectUtil.object2Map(obj);
			System.out.println(map);
			assertEquals(map==null, false);
			assertEquals(map.get("status").toString().equals("0"), true);
			assertEquals(map.get("msg").toString().equals("success"), true);
			assertEquals(map.get("data").toString().equals("12.5"), true);
			
			Result obj1= null;
			map = ObjectUtil.object2Map(obj1);
			System.out.println(map);
			assertEquals(map==null, true);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
	}
	
	@Test
	public void test_11_Map2object() {
		Result obj = null;
		Map<String, Object> map = null;
		map =new HashMap<>();
		map.put("status", "0");
		map.put("msg", "success");
		map.put("data", 12.5f);
		map.put("success", 1);
		
		obj = ObjectUtil.Map2Object(map, Result.class);
		System.out.println(obj);
		assertEquals(obj==null, false);
		assertEquals(obj.getStatus().equals("0"), true);
		assertEquals(obj.getMsg().equals("success"), true);
		assertEquals(obj.getData().toString().equals("12.5"), true);
		assertEquals(obj.getSuccess(), true);
		
		map= null;
		obj = ObjectUtil.Map2Object(map, Result.class);
		System.out.println(obj);
		assertEquals(obj==null, true);
	}

	@Test
	public void test_12_object2JSON() {
		Result obj =new Result();
		obj.setStatus("0");
		obj.setMsg("success");
		obj.setData(12.5f);
		String json = ObjectUtil.Object2JSON(obj);
		System.out.println(json);
		assertEquals(json.equals("{\"data\":12.5,\"msg\":\"success\",\"status\":\"0\",\"success\":false}"), true);
		obj = null;
		json = ObjectUtil.Object2JSON(obj);
		System.out.println(json);
		assertEquals(json==null, true);

	}
	
	@Test
	public void test_13_JSON2Object() {
		String json ="{\"data\":12.5,\"msg\":\"success\",\"status\":\"0\"}";
		Result obj = ObjectUtil.JSON2Object(json , Result.class);
		System.out.println(obj);
		assertEquals(obj==null, false);
		assertEquals(obj.getStatus().equals("0"), true);
		assertEquals(obj.getMsg().equals("success"), true);
		assertEquals(obj.getData().toString().equals("12.5"), true);

		json = "";
		obj = ObjectUtil.JSON2Object(json , Result.class);
		System.out.println(obj);
		assertEquals(obj==null, true);
	}
	
}

package com.ihltx.utility.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.util.Strings;

import com.alibaba.fastjson.JSON;

/**
 * ObjectUtil
 * Object utility class
 * @author liulin 84611913@qq.com
 *
 */
@SuppressWarnings("all")
public class ObjectUtil {

	/**
	 * Replace the object object with a map collection (convert false / true to integer 0 / 1 at the same time)
	 *
	 * @param object				Object to convert
	 * @return Map<String, Object>
	 *     Return map collection
	 *     null	--	Indicates that the conversion failed
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Map<String, Object> object2Map(Object object) throws IllegalArgumentException, IllegalAccessException {
		return object2Map(object, true);
	}

	/**
	 * Replace the object object with a map collection
	 *
	 * @param object				Object to convert
	 * @param isBoolean2Int    		Convert Boolean false / true to integer 0 / 1
	 * @return Map<String, Object>
	 *     Return map collection
	 *     null	--	Indicates that the conversion failed
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Map<String, Object> object2Map(Object object, Boolean isBoolean2Int)
			throws IllegalArgumentException, IllegalAccessException {
		if (object == null)
			return null;
		Map<String, Object> map = new HashMap<String, Object>();
		Class<?> clazz = object.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);
			String fieldName = field.getName();
			Object value = field.get(object);
			if (isBoolean2Int && value!=null && value.getClass().getTypeName().equals("java.lang.Boolean")) {
				if (Boolean.valueOf(value.toString())) {
					map.put(fieldName, 1);
				} else {
					map.put(fieldName, 0);
				}
			} else if (value!=null && value.getClass().getTypeName().equals("java.util.Date")) {
				SimpleDateFormat sdf = new SimpleDateFormat(JSONObject.DEFFAULT_DATE_FORMAT);
				map.put(fieldName, sdf.format((Date)value));
			} else {
				map.put(fieldName, value);
			}
		}
		return map;
	}

	/**
	 * Convert the map collection to the specified T-type object (convert integer 0 / 1 to false / true at the same time)
	 *
	 * @param map                	Map collection to convert
	 * @return T
	 *     Returns an object of type T
	 *     null	--	Indicates that the conversion failed
	 * @throws IllegalAccessException
	 */
	public static <T> T Map2Object(Map<String, Object> map, Class<?> clazz) {
		if (map == null)
			return null;
		return (T) JSONObject.parseObject(JSON.toJSONString(map), clazz);
	}

	/**
	 * Convert object object to JSON string
	 *
	 * @param object                Object to convert
	 * @return String
	 * 		Returns JSON string
	 * 		null	--	Indicates that the conversion failed
	 * @throws IllegalAccessException
	 */
	public static String Object2JSON(Object object) {
		if (object == null) {
			return null;
		}
		return JSONObject.toJSONString(object);
	}

	/**
	 * Converts a JSON string to a specified T-type object
	 *
	 * @param <T>
	 * @param json				JSON string
	 * @param clazz				Type of T
	 * @return T
	 *     Returns an object of type T
	 *     null	--	Indicates that the conversion failed
	 */
	public static <T> T JSON2Object(String json, Class<?> clazz) {
		if (Strings.isEmpty(json)) {
			return null;
		}
		return (T) JSONObject.parseObject(json, clazz);
	}

	/**
	 * Object is serialized as a string
	 * 
	 * @param object            Objects to process
	 * @return String
	 */
	public static String serializeObject(Object object) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
		out.writeObject(object);
		String objectStr = byteArrayOutputStream.toString(StringUtil.ISO_8859_1);
		out.close();
		byteArrayOutputStream.close();
		return objectStr;
	}

	/**
	 * Serialize string to object
	 * 
	 * @param str            	String to process
	 * @return Object
	 *     Returns an object
	 *     null	--	Indicates that the conversion failed
	 * @throws Exception
	 */
	public static Object stringSerializeObject(String str) throws Exception {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes(StringUtil.ISO_8859_1));
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
		Object object = objectInputStream.readObject();
		objectInputStream.close();
		byteArrayInputStream.close();
		return object;
	}


	/**
	 * 将 JSONObject 对象转 Map<String , Object> 对象
	 * @param jsonObject			jsonObject
	 * @return Map<String , Object>
	 */
	public static Map<String , Object> JsonObject2Map(JSONObject jsonObject){
		Map<String, Object> data =new HashMap<>();
		Iterator it =jsonObject.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			data.put(entry.getKey(), entry.getValue());
		}
		return data;
	}

}

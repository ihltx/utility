package com.ihltx.utility.util.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Result
 * Processing result entity class
 * @author liulin 84611913@qq.com
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {

	/**
	 * Execution result status
	 * 		0 		-- 	success
	 * 		non-0 	-- 	failure
	 */
	private String status = "1";

	/**
	 * message
	 */
	private String msg = "success";

	/**
	 * data
	 */
	private Object data = null;

	/**
	 * Execution result status
	 * 	 	true	-- 	success
	 * 	 	false	-- 	failure
	 */
	private Boolean success = false;

	public Result(String status, String msg){
		this.status = status;
		this.msg = msg;
	}
	
}

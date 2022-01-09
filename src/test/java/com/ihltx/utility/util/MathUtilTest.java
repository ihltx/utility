package com.ihltx.utility.util;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import com.ihltx.utility.UtilityApplication;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;


@SuppressWarnings("all")
@SpringBootTest(classes = {UtilityApplication.class})

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class MathUtilTest {
	
	
	@Test
	public void test_10_multiply() {
		BigDecimal rs = MathUtil.multiply(123456.8d, 1223323223);
		System.out.println(rs);
 		assertEquals(rs.toString().equals("151027570477266.4"), true);
	}
	
	@Test
	public void test_11_divide() {
		BigDecimal rs = MathUtil.divide(1234532332236.8d, 1223223);
		System.out.println(rs);
		assertEquals(rs.toString().equals("1009245.52"), true);

		rs = MathUtil.divide(1234532332236.8d, 1223223 , 6);
		System.out.println(rs);
		assertEquals(rs.toString().equals("1009245.519613"), true);
		
		rs = MathUtil.divide(1234532332236.8d, 1223223 , 0);
		System.out.println(rs);
		assertEquals(rs.toString().equals("1009246"), true);

	}
	
}

package com.ihltx.utility.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * MathUtil
 * Math utility class
 * @author liulin 84611913@qq.com
 *
 */
public class MathUtil {

	/**
	 * Reserved precision multiplication
	 * @param d1   	multiplier 1
	 * @param d2	multiplier 2
	 * @return BigDecimal
	 */
	public static BigDecimal multiply(double d1, double d2) {
		BigDecimal dc1 = new BigDecimal(d1 + "");
		BigDecimal dc2 = new BigDecimal(d2 + "");
		return dc1.multiply(dc2);
		
	}
	
	
	/**
	 * Keep two decimal places and divide (round)
	 * @param d1		Dividend
	 * @param d2		Divisor
	 * @return BigDecimal
	 */
	public static BigDecimal divide(double d1, double d2) {
		return divide(d1 ,d2 ,2 , RoundingMode.CEILING);
	}
	
	/**
	 * Retain the precision of the specified decimal places and divide (round)
	 * @param d1				Dividend
	 * @param d2				Divisor
	 * @param digit             Exact number of decimal places
	 * @return BigDecimal
	 */
	public static BigDecimal divide(double d1, double d2, int digit) {
		BigDecimal dc1 = new BigDecimal(d1 + "");
		BigDecimal dc2 = new BigDecimal(d2 + "");
		return dc1.divide(dc2 , digit, RoundingMode.CEILING);
	}
	
	
	/**
	 * Preserves the precision of the specified decimal places
	 * @param d1				Dividend
	 * @param d2				Divisor
	 * @param digit				Exact number of decimal places
	 * @param roundingMode		RoundingMode.HALF_UP   --向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。如果舍弃部分 >= 0.5，则舍入行为与 ROUND_UP 相同;否则舍入行为与 ROUND_DOWN 相同。注意，这是我们大多数人在小学时就学过的舍入模式(四舍五入)。 
	 * 						    RoundingMode.HALF_DOWN --向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为上舍入的舍入模式。如果舍弃部分 > 0.5，则舍入行为与 ROUND_UP 相同;否则舍入行为与 ROUND_DOWN 相同(五舍六入)。
	 * 							RoundingMode.UP		   --舍入远离零的舍入模式，在丢弃非零部分之前始终增加数字(始终对非零舍弃部分前面的数字加1)，注意，此舍入模式始终不会减少计算值的大小。
	 * 							RoundingMode.DOWN      --接近零的舍入模式，在丢弃某部分之前始终不增加数字(从不对舍弃部分前面的数字加1，即截短)，注意，此舍入模式始终不会增加计算值的大小。
	 * 							RoundingMode.CEILING   --接近正无穷大的舍入模式，如果 BigDecimal 为正，则舍入行为与 ROUND_UP 相同;如果为负，则舍入行为与 ROUND_DOWN 相同。注意，此舍入模式始终不会减少计算值。
	 * 							RoundingMode.FLOOR     --接近负无穷大的舍入模式，如果 BigDecimal 为正，则舍入行为与 ROUND_DOWN 相同;如果为负，则舍入行为与 ROUND_UP 相同。注意，此舍入模式始终不会增加计算值。
	 * 							RoundingMode.FLOOR     --接近负无穷大的舍入模式，如果 BigDecimal 为正，则舍入行为与 ROUND_DOWN 相同;如果为负，则舍入行为与 ROUND_UP 相同。注意，此舍入模式始终不会增加计算值。
	 * @return BigDecimal
	 */
	public static BigDecimal divide(double d1, double d2, int digit , RoundingMode roundingMode) {
		BigDecimal dc1 = new BigDecimal(d1 + "");
		BigDecimal dc2 = new BigDecimal(d2 + "");
		return dc1.divide(dc2 , digit, roundingMode);
	}

}

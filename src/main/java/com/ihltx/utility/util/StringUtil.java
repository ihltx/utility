package com.ihltx.utility.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.logging.log4j.util.Strings;
import org.springframework.util.StringUtils;

/**
 * StringUtil
 * String utility class
 * @author liulin 84611913@qq.com
 *
 */

public class StringUtil {

	public static final String US_ASCII = "US-ASCII";
	public static final String ISO_8859_1 = "ISO-8859-1";
	public static final String UTF_8 = "UTF-8";
	public static final String UTF_16BE = "UTF-16BE";
	public static final String UTF_16LE = "UTF-16LE";
	public static final String UTF_16 = "UTF-16";
	public static final String GBK = "GBK";
	public static final String GB2312 = "GB2312";

	
	/**
	 * Ignore case to determine whether the string starts with prefix
	 * 
	 * @param str                	String to check
	 * @param prefix				Prefix
	 * @return Boolean
	 * 		true	--  yes
	 * 		false	--	no
	 */
	public static Boolean startsWithi(String str, String prefix) {
		return StringUtils.startsWithIgnoreCase(str, prefix);
	}

	/**
	 * Ignore case and judge whether the string ends with suffix
	 * 
	 * @param str                	String to check
	 * @param suffix				Suffix
	 * @return Boolean
	 * 		true	--  yes
	 * 		false	--	no
	 */
	public static Boolean endsWithi(String str, String suffix) {
		return StringUtils.endsWithIgnoreCase(str, suffix);
	}
	
	
	/**
	 * Strictly case sensitive to determine whether the string starts with prefix
	 * 
	 * @param str                	String to check
	 * @param prefix				Prefix
	 * @return Boolean
	 * 		true	--  yes
	 * 		false	--	no
	 */
	public static Boolean startsWith(String str, String prefix) {
		if(str==null) {
			return false;
		}else if(str.equals("")) {
			if(prefix==null) {
				return false;
			}else if(prefix.equals("")) {
				return true;
			}
			return false;
		}
		return str.startsWith(prefix);
	}

	/**
	 * Strictly case sensitive to determine whether the string ends with suffix
	 * 
	 * @param str                	String to check
	 * @param suffix				Suffix
	 * @return Boolean
	 * 		true	--  yes
	 * 		false	--	no
	 */
	public static Boolean endsWith(String str, String suffix) {
		if(str==null) {
			return false;
		}else if(str.equals("")) {
			if(suffix==null) {
				return false;
			}else if(suffix.equals("")) {
				return true;
			}
			return false;
		}
		return str.endsWith(suffix);
	}



	/**
	 * The generated random string only includes upper and lower case letters and numbers,
	 * and the length of the generated string is determined by length
	 *
	 * @param length                The length of the generated random string
	 * @return String
	 * 		Returns a random string
	 */
	public static String getRandomString(Integer length) {
		return getRandomString(length, true , true, true, false);
	}

	/**
	 * The generated random string only includes upper and lower case letters and numbers,
	 * and the length of the generated string is determined by length
	 * 
	 * @param length                The length of the generated random string
	 * @param isAddLowercaseLetter  Is allow to add all lowercase Letters
	 * @return String
	 * 		Returns a random string
	 */
	public static String getRandomString(Integer length, Boolean isAddLowercaseLetter) {
		return getRandomString(length, isAddLowercaseLetter , false);
	}

	/**
	 * Generate a random string, and the length of the generated string is determined by length
	 *
	 * @param length				The length of the generated random string
	 * @param isAddLowercaseLetter  Is allow to add all lowercase Letters
	 * @param isAddCapitalLetter    Is allow to add all Capital Letters
	 * @return String
	 * 		Returns a random string
	 */
	public static String getRandomString(Integer length, Boolean isAddLowercaseLetter, Boolean isAddCapitalLetter) {
		return getRandomString(length, isAddLowercaseLetter , isAddCapitalLetter , false);
	}
	/**
	 * Generate a random string, and the length of the generated string is determined by length
	 *
	 * @param length				The length of the generated random string
	 * @param isAddLowercaseLetter  Is allow to add all lowercase Letters
	 * @param isAddCapitalLetter    Is allow to add all Capital Letters
	 * @param isAddNumber           Is allow to add all number
	 * @return String
	 * 		Returns a random string
	 */
	public static String getRandomString(Integer length, Boolean isAddLowercaseLetter, Boolean isAddCapitalLetter,Boolean isAddNumber) {
		return getRandomString(length, isAddLowercaseLetter , isAddCapitalLetter , isAddNumber , false);
	}

	/**
	 * Generate a random string, and the length of the generated string is determined by length
	 *
	 * @param length				The length of the generated random string
	 * @param isAddLowercaseLetter  Is allow to add all lowercase Letters
	 * @param isAddCapitalLetter    Is allow to add all capital Letters
	 * @param isAddNumber           Is allow to add all number
	 * @param isAddSymbol           Is allow to add all symbols except "、/、\
	 * @return String
	 * 		Returns a random string
	 */
	public static String getRandomString(Integer length, Boolean isAddLowercaseLetter, Boolean isAddCapitalLetter,Boolean isAddNumber, Boolean isAddSymbol) {
		if(length==null || length<=0) return "";
		StringBuffer str = new StringBuffer();
		byte num = 0;
		if (isAddLowercaseLetter) {
			str.append("zxcvbnmlkjhgfdsaqwertyuiop"); // 26个
			num += 26;
		}
		if (isAddCapitalLetter) {
			str.append("QWERTYUIOPASDFGHJKLZXCVBNM"); // 26个
			num += 26;
		}
		if (isAddNumber) {
			str.append("1629384750"); // 10个
			num += 10;
		}
		if (isAddSymbol) {
			str.append("!@#$%^&*()-=_+{}[]|;:',.<>?`~"); // 29个
			num += 29;
		}
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(num);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	
	
	/**
	 * Remove leading and trailing white space characters
	 * 
	 * @param str				The string
	 * @return String
	 * 		Returns the string after successful processing
	 */
	public static String trim(String str) {
		if(Strings.isEmpty(str)) return str;
		return str.replaceAll("^[ \\t　]*", "").replaceAll("[ \\t　]*$", "");
	}
	
	
	/**
	 * Case sensitive, removing the first and last specified characters
	 * If you want to be case insensitive, include case sensitive characters in the specified character list
	 * 
	 * @param str				The string
	 * @param element            Specify character list , Such as "a 68"
	 * @return String
	 * 		Returns the string after successful processing
	 */
	public static String trim(String str, String element) {
		if(Strings.isEmpty(str)) return str;
		return str.replaceAll("^[" + element +"]*", "").replaceAll("[" + element + "]*$", "");
	}
	

	/**
	 * Remove first white space character
	 * 
	 * @param str				The string
	 * @return String
	 * 		Returns the string after successful processing
	 */
	public static String ltrim(String str) {
		if(Strings.isEmpty(str)) return str;
		return str.replaceAll("^[ \\t　]*", "");
	}
	
	/**
	 * Case sensitive to remove the specified characters on the left
	 * If you want to be case insensitive, include case sensitive characters in the specified character list
	 *
	 * @param str				The string
	 * @param element            Specify character list , Such as "a 68"
	 * @return String
	 * 		Returns the string after successful processing
	 */
	public static String ltrim(String str, String element) {
		if(Strings.isEmpty(str)) return str;
		return str.replaceAll("^[" + element +"]*", "");
	}

	

	/**
	 * Remove trailing white space characters
	 * 
	 * @param str				The string
	 * @return String
	 * 		Returns the string after successful processing
	 */
	public static String rtrim(String str) {
		if(Strings.isEmpty(str)) return str;
		return str.replaceAll("[ \\t　]*$", "");
	}
	
	
	/**
	 * Case sensitive removal of trailing specified characters
	 * If you want to be case insensitive, include case sensitive characters in the specified character list
	 *
	 * @param str				The string
	 * @param element            Specify character list , Such as "a 68"
	 * @return String
	 * 		Returns the string after successful processing
	 */
	public static String rtrim(String str, String element) {
		if(Strings.isEmpty(str)) return str;
		return str.replaceAll("[" + element + "]*$", "");
	}
	

	
	/**
     * Converts the first letter of a string to uppercase
     * @param str				The string
	 * @return String
	 * 		Returns the string after successful processing
	 */
	public static String captureName(String str) {
		if(Strings.isEmpty(str)) return str;
        char[] chars=str.toCharArray();
        if(chars[0]>=97 && chars[0]<=122){
			chars[0]-=32;
		}
        return String.valueOf(chars);
    }
	
	
	/**
     * Return UUID
	 * @return String
	 * 		Return UUID
	 */
	public static String uuid() {
        return UUID.randomUUID().toString();
    }
	
	
	/**
	 *  Format the string and replace {0},.., {n} in message with args [0],..., args [n]
	 * @param message				The string
	 * @param args					Specify parameters
	 * @return String
	 * 		Returns the string after successful processing
	 */
	public static String formatString(String message , Object ...args) {
		if(!Strings.isEmpty(message)) {
			String msg = message;
			if(args!=null && args.length>0) {
				for(int i=0;i<args.length;i++) {
					msg = msg.replaceAll("\\{" + i +"\\}", args[i].toString()); 
				}
			}
			return msg;
		}else {
			return null;
		}
	}


	/**
	 * Convert character encoding to us-ascii
	 *
	 * @param str				The string
	 * @return String
	 * 		Returns the string after successful processing
	 * @throws UnsupportedEncodingException
	 */
	public static String toASCII(String str) throws UnsupportedEncodingException {
		return changeCharset(str, US_ASCII);
	}

	/**
	 * Convert character encoding to ISO_8859_1
	 *
	 * @param str				The string
	 * @return String
	 * 		Returns the string after successful processing
	 * @throws UnsupportedEncodingException
	 */
	public static String toISO_8859_1(String str) throws UnsupportedEncodingException {
		return changeCharset(str, ISO_8859_1);
	}

	/**
	 * Convert character encoding to UTF_8
	 *
	 * @param str				The string
	 * @return String
	 * 		Returns the string after successful processing
	 * @throws UnsupportedEncodingException
	 */
	public static String toUTF_8(String str) throws UnsupportedEncodingException {
		return changeCharset(str, UTF_8);
	}

	/**
	 * Convert character encoding to UTF_16BE
	 *
	 * @param str				The string
	 * @return String
	 * 		Returns the string after successful processing
	 * @throws UnsupportedEncodingException
	 */
	public static String toUTF_16BE(String str) throws UnsupportedEncodingException {
		return changeCharset(str, UTF_16BE);
	}

	/**
	 * Convert character encoding to UTF_16LE
	 *
	 * @param str				The string
	 * @return String
	 * 		Returns the string after successful processing
	 * @throws UnsupportedEncodingException
	 */
	public static String toUTF_16LE(String str) throws UnsupportedEncodingException {
		return changeCharset(str, UTF_16LE);
	}

	/**
	 * Convert character encoding to UTF_16
	 *
	 * @param str				The string
	 * @return String
	 * 		Returns the string after successful processing
	 * @throws UnsupportedEncodingException
	 */
	public static String toUTF_16(String str) throws UnsupportedEncodingException {
		return changeCharset(str, UTF_16);
	}

	/**
	 * Convert character encoding to GBK
	 *
	 * @param str				The string
	 * @return String
	 * 		Returns the string after successful processing
	 * @throws UnsupportedEncodingException
	 */
	public static String toGBK(String str) throws UnsupportedEncodingException {
		return changeCharset(str, GBK);
	}

	/**
	 * Convert character encoding to GB2312
	 *
	 * @param str				The string
	 * @return String
	 * 		Returns the string after successful processing
	 * @throws UnsupportedEncodingException
	 */
	public static String toGB2312(String str) throws UnsupportedEncodingException {
		return changeCharset(str, GB2312);
	}

	/**
	 * Implementation of string encoding conversion
	 *
	 * @param str				The string
	 * @param newCharset     	Target character set
	 * @return String
	 * 		Returns the string after successful processing
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String str, String newCharset) throws UnsupportedEncodingException {
		if(str != null) {
			byte[] bs = str.getBytes();
			return new String(bs,newCharset);
		}
		return null;
	}

	/**
	 * Implementation of string encoding conversion
	 * @param str				The string
	 * @param oldCharset		Old character set
	 * @param newCharset		New character set
	 * @return String
	 * 		Returns the string after successful processing
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String str, String oldCharset, String newCharset) throws UnsupportedEncodingException {
		if(str != null) {
			byte[] bs = str.getBytes(oldCharset);
			return new String(bs, newCharset);
		}
		return null;
	}

	

	/**
	 * Convert string to BufferedReader
	 * @param source			The string
	 * @return	BufferedReader
	 */
    public static BufferedReader StringToBufferedReader(String source){
        if(Strings.isEmpty(source)) return null;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(source.getBytes());
        InputStream inputStream = byteArrayInputStream;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return reader;
    }
    
    /**
     * Convert the long integer of scientific counting method to the string of non scientific counting method
     * @param number        	Long integer to format
	 * @return String
	 * 		Returns the string after successful processing
     */
    public static String foramt(Long number) {
    	DecimalFormat df = new DecimalFormat("##");
    	return df.format(number);
    }

	/**
	 * Convert the double precision decimal of scientific counting method to the string of non scientific counting method
	 * @param number        	Double precision decimal to format
	 * @return String
	 * 		Returns the string after successful processing
	 */
    public static String foramt(Double number) {
    	return foramt(number,0);
    }
    
    /**
     * Convert the double precision decimal of scientific counting method to the string of non scientific counting method
     * @param number			Double precision decimal to format
     * @param point             Specify the number of decimal places to be reserved. It must be > = 0. If it is 0, only integers will be reserved
	 * @return String
	 * 		Returns the string after successful processing
	 */
    public static String foramt(Double number , Integer point) {
    	StringBuffer sb =new StringBuffer("##");
    	if(point>0) {
    		sb.append(".");
    	}
    	for(int i=0;i<point;i++) {
    		sb.append("#");
    	}
    	DecimalFormat df = new DecimalFormat(sb.toString());
    	return df.format(number);
    }
    
    /**
     * Determines whether the string is an integer
     * @param str			The string
	 * @return Boolean
	 * 		true	--  yes
	 * 		false	--	no
	 */
    public static Boolean isInteger(String str) {
    	Pattern pattern =Pattern.compile("^[0-9]+$");
    	return pattern.matcher(str).find();
    }
    
    /**
     * Determines whether the string is a list of integers, that is  separated by , character
     * @param str			The string
	 * @return Boolean
	 * 		true	--  yes
	 * 		false	--	no
	 */
    public static Boolean isIntegerList(String str) {
    	Pattern pattern =Pattern.compile("^[0-9]+(,[0-9]+)*$");
    	return pattern.matcher(str).find();
    }
    
    /**
     * Determines whether the string is decimal
     * @param str			The string
	 * @return Boolean
	 * 		true	--  yes
	 * 		false	--	no
	 */
    public static Boolean isNumeric(String str) {
    	Pattern pattern =Pattern.compile("^[0-9]+(\\.[0-9]+)?$");
    	return pattern.matcher(str).find();
    }
    
    /**
     * Determines whether the string is a decimal list, that is separated by , character
     * @param str			The string
	 * @return Boolean
	 * 		true	--  yes
	 * 		false	--	no
	 */
    public static Boolean isNumericList(String str) {
    	Pattern pattern =Pattern.compile("^[0-9]+(\\.[0-9]+)?(,[0-9]+(\\.[0-9]+)?)*$");
    	return pattern.matcher(str).find();
    }
    
    /**
     * Determines whether the string is a string list, that is separated by , character
     * @param str			The string
	 * @return Boolean
	 * 		true	--  yes
	 * 		false	--	no
	 */
    public static Boolean isWordList(String str) {
    	Pattern pattern =Pattern.compile("^[\\w\\.\\-]+(,[\\w\\.\\-]+)*$");
    	return pattern.matcher(str).find();
    }

	/**
	 * Determines whether the string is null Or empty
	 * @param str			The string
	 * @return Boolean
	 * 		true	--  yes
	 * 		false	--	no
	 */
    public static Boolean isNullOrEmpty(String str){
    	return (str==null || str.length()<=0);
	}


	/**
	 * first letter convert capitalize
	 * @param name
	 * @return String
	 */
	public static String captureFirstName(String name) {
		if(isNullOrEmpty(name)) return name;
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		return name;
	}

	/**
	 * 针对字符串数组进行排序
	 * @param array
	 */
	public static void sort(String array[]) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[j].compareTo(array[i]) < 0) {
					String temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
	}

	/**
	 * 使用指定分隔符连接数组并返回连接字符串，如果元素都为null则返回null
	 * @param array				对象数组
	 * @param separator			分隔符
	 * @return String
	 */
	public static String join(Object[] array, String separator) {
		if (separator == null) {
			separator = "";
		}
		if(array==null || array.length<=0){
			return null;
		}
		StringBuffer buf = new StringBuffer();

		int i = 0;
		for(Object o : array) {
			if(o!=null){
				if (i > 0) {
					buf.append(separator);
				}
				buf.append(o);
				i++;
			}
		}
		if(i==0){
			return null;
		}
		return buf.toString();
	}
}

package com.ihltx.utility.util;

import java.io.*;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.*;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.logging.log4j.util.Strings;
import org.springframework.util.DigestUtils;

/**
 * SecurityUtil
 * Security utility class
 * @author liulin 84611913@qq.com
 *
 */
@SuppressWarnings("all")
public class SecurityUtil {
	public static final String SHA1 = "SHA1";
	public static final String SHA_256 = "SHA-256";
	public static final String SHA_512 = "SHA-512";
	public static final String DES = "DES";

	/**
	 * MD5 encryption
	 *
	 * @param data                Plaintext data
	 * @param salt                add salt data
	 * @return String
	 * 		Ciphertext data
	 */
	public static String md5(String data , int salt) {
		return md5(data + salt);
	}


	/**
	 * MD5 encryption
	 * 
	 * @param data                Plaintext data
	 * @return String
	 * 		Ciphertext data
	 */
	public static String md5(String data) {
		return DigestUtils.md5DigestAsHex(data.getBytes());
	}

	private static byte[] encryptSHA(byte[] data, String shaN) throws Exception {
		MessageDigest sha = MessageDigest.getInstance(shaN);
		sha.update(data);
		return sha.digest();

	}

	/**
	 * SHA1 encryption
	 * 
	 * @param data                Plaintext data
	 * @return String
	 * 	 	Ciphertext data
	 * @throws Exception
	 */
	public static String sha(String data) throws Exception {
		byte[] outputData = new byte[0];
		outputData = encryptSHA(data.getBytes(), SHA1);
		return new BigInteger(1, outputData).toString(16);
	}

	/**
	 * SHA256 encryption
	 * 
	 * @param data                Plaintext data
	 * @return String
	 * 	 	Ciphertext data
	 * @throws Exception
	 */
	public static String sha256(String str) throws Exception {
		byte[] outputData = new byte[0];
		outputData = encryptSHA(str.getBytes(), SHA_256);
		return new BigInteger(1, outputData).toString(16);
	}

	/**
	 * SHA512 encryption
	 *
	 * @param data                Plaintext data
	 * @return String
	 * 	 	Ciphertext data
	 * @throws Exception
	 */
	public static String sha512(String str) throws Exception {
		byte[] outputData = new byte[0];
		outputData = encryptSHA(str.getBytes(), SHA_512);
		return new BigInteger(1, outputData).toString(16);
	}

	/**
	 * base64 encode
	 * 
	 * @param data          	Data to encode
	 * @param charset			The character set corresponding to the string to be encoded. If it is empty or null, it means Base64 encoding using UTF-8 character set
	 * @return String
	 * 		Returns the encoded string
	 * 		null -- encoding failure
	 * @throws UnsupportedEncodingException
	 */
	public static String base64Encode(String data, String charset) throws UnsupportedEncodingException {
		if (Strings.isEmpty(charset)) {
			charset = StringUtil.UTF_8;
		}
		Base64.Encoder base64 = Base64.getEncoder();
		return base64.encodeToString(data.getBytes(charset));
	}

	/**
	 * base64 encode
	 * 
	 * @param data      		Data to encode (using UTF-8 character set)
	 * @return String
	 * 		Returns the encoded string
	 * 		null -- encoding failure
	 * @throws UnsupportedEncodingException
	 */
	public static String base64Encode(String data) throws UnsupportedEncodingException {
		return base64Encode(data, StringUtil.UTF_8);
	}

	/**
	 * base64 decode
	 * 
	 * @param data				Data to decode
	 * @param charset			The character set corresponding to the string to be decoded. If it is empty or null, it means Base64 encoding using UTF-8 character set
	 * @return String
	 * 		Returns the decoded string
	 * 		null -- Decoding failed
	 * @throws UnsupportedEncodingException
	 */
	public static String base64Decode(String data, String charset) throws UnsupportedEncodingException {
		if (Strings.isEmpty(charset)) {
			charset = StringUtil.UTF_8;
		}
		Base64.Decoder base64 = Base64.getDecoder();
		return new String(base64.decode(data), charset);
	}

	/**
	 * base64 decode
	 * 
	 * @param data				Data to decode(using UTF-8 character set)
	 * @return String
	 * 		Returns the decoded string
	 * 		null -- Decoding failed
	 * @throws UnsupportedEncodingException
	 */
	public static String base64Decode(String data) throws UnsupportedEncodingException {
		return base64Decode(data, StringUtil.UTF_8);
	}

	/**
	 * URL encoding
	 * 
	 * @param url				URL to encode
	 * @param charset			The character set corresponding to the string to be encoded. If it is empty or null, it means Base64 encoding using UTF-8 character set
	 * @return String
	 * 		Returns the encoded string
	 * 		null -- encoding failure
	 * @throws UnsupportedEncodingException
	 */
	public static String urlEncode(String url, String charset) throws UnsupportedEncodingException {
		if (Strings.isEmpty(charset)) {
			charset = StringUtil.UTF_8;
		}
		return URLEncoder.encode(url, charset);
	}

	/**
	 * URL encoding
	 *
	 * @param url				URL to encode(using UTF-8 character set)
	 * @return String
	 * 		Returns the encoded string
	 * 		null -- encoding failure
	 * @throws UnsupportedEncodingException
	 */
	public static String urlEncode(String url) throws UnsupportedEncodingException {
		return urlEncode(url, StringUtil.UTF_8);
	}

	/**
	 * URL decode
	 *
	 * @param url				URL to decode
	 * @param charset			The character set corresponding to the string to be decoded. If it is empty or null, it means Base64 encoding using UTF-8 character set
	 * @return String
	 * 		Returns the decoded string
	 * 		null -- Decoding failed
	 */
	public static String urlDecode(String url, String charset) throws UnsupportedEncodingException {
		if (Strings.isEmpty(charset)) {
			charset = StringUtil.UTF_8;
		}
		return URLDecoder.decode(url, charset);
	}

	/**
	 * URL decode
	 *
	 * @param url				URL to decode(using UTF-8 character set)
	 * @param charset			The character set corresponding to the string to be decoded. If it is empty or null, it means Base64 encoding using UTF-8 character set
	 * @return String
	 * 		Returns the decoded string
	 * 		null -- Decoding failed
	 */
	public static String urlDecode(String url) throws UnsupportedEncodingException {
		return urlDecode(url, StringUtil.UTF_8);
	}

	/**
	 * Generate key
	 *
	 * @param password				Password
	 * @param charset				The character set
	 * @return	Key
	 *
	 * @throws Exception
	 */
	private static Key generateKey(String password, String charset) throws Exception {
		if (Strings.isEmpty(charset)) {
			charset = StringUtil.UTF_8;
		}
		DESKeySpec dks = new DESKeySpec(password.getBytes(charset));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		return keyFactory.generateSecret(dks);
	}

	/**
	 * Des encrypted string(using UTF-8 character set)
	 * 
	 * @param data             		Data to be encrypted
	 * @param password				Encrypted password, at least 8 characters
	 * @param key					Encryption key, at least 8 characters
	 * @return String
	 * 		Returns an encrypted string
	 * 		null -- encryption failed
	 * @throws Exception
	 */
	public static String encrypt(String data, String password, String key) throws Exception {
		return encrypt(data, password, key, StringUtil.UTF_8);
	}

	/**
	 * Des encrypted string
	 *
	 * @param data             		Data to be encrypted
	 * @param password				Encrypted password, at least 8 characters
	 * @param key					Encryption key, at least 8 characters
	 * @param charset				Character set
	 * @return String
	 * 		Returns an encrypted string
	 * 		null -- encryption failed
	 * @throws Exception
	 */
	public static String encrypt(String data, String password, String key, String charset) throws Exception {
		if (Strings.isEmpty(charset)) {
			charset = StringUtil.UTF_8;
		}
		if (key == null || key.length() < 8) {
			throw new Exception("Data encryption failed,Key at least 8 bytes.");
		}
		if (key.length() > 8) {
			key = key.substring(0, 8);
		}
		if (password == null || password.length() < 8) {
			throw new Exception("Data encryption failed,Password at least 8 bytes.");
		}
		if (data == null)
			return null;
		Key secretKey = generateKey(password, charset);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec(key.getBytes(charset));
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
		byte[] bytes = cipher.doFinal(data.getBytes(charset));
		return new String(Base64.getEncoder().encode(bytes));
	}

	/**
	 * Des decryption date(using UTF-8 character set)
	 *
	 * @param data     				Encrypted data
	 * @param password				Encrypted password, at least 8 characters
	 * @param key					Encryption key, at least 8 characters
	 * @return String
	 * 		Returns the decryption data
	 * 		null -- decryption failed
	 * @throws Exception
	 */
	public static String decrypt(String data, String password, String key) throws Exception {
		return decrypt(data, password, key, StringUtil.UTF_8);
	}

	/**
	 * Des decryption date
	 *
	 * @param data     				Encrypted data
	 * @param password				Encrypted password, at least 8 characters
	 * @param key					Encryption key, at least 8 characters
	 * @param charset				Character set
	 * @return String
	 * 		Returns the decryption data
	 * 		null -- decryption failed
	 * @throws Exception
	 */
	public static String decrypt(String data, String password, String key, String charset) throws Exception {
		if (Strings.isEmpty(charset)) {
			charset = StringUtil.UTF_8;
		}
		if (key == null || key.length() < 8) {
			throw new Exception("Data decryption failed,Key at least 8 bytes.");
		}
		if (key.length() > 8) {
			key = key.substring(0, 8);
		}
		if (password == null || password.length() < 8) {
			throw new Exception("Data decryption failed,Password at least 8 bytes.");
		}

		if (data == null)
			return null;
		Key secretKey = generateKey(password, charset);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec(key.getBytes(charset));
		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		return new String(cipher.doFinal(Base64.getDecoder().decode(data.getBytes(charset))), charset);
	}

	/**
	 * Des encrypts files(using UTF-8 character set)
	 *
	 * @param srcFile    			Full path of the original file to be encrypted
	 * @param destFile 				The full path of the encrypted target file. If it exists, it will be overwritten
	 * @param password				Encrypted password, at least 8 characters
	 * @param key					Encryption key, at least 8 characters
	 * @return String
	 * 		Returns the full path of the target file successfully encrypted
	 *		null -- encryption failed
	 * @throws Exception
	 */
	public static String encryptFile(String srcFile, String destFile, String password, String key) throws Exception {
		return encryptFile(srcFile, destFile, password, key, StringUtil.UTF_8);
	}


	/**
	 * Des encrypts files
	 *
	 * @param srcFile    			Full path of the original file to be encrypted
	 * @param destFile 				The full path of the encrypted target file. If it exists, it will be overwritten
	 * @param password				Encrypted password, at least 8 characters
	 * @param key					Encryption key, at least 8 characters
	 * @param charset				Character set
	 * @return String
	 * 		Returns the full path of the target file successfully encrypted
	 *		null -- encryption failed
	 * @throws Exception
	 */
	public static String encryptFile(String srcFile, String destFile, String password, String key, String charset) throws Exception {
		if (Strings.isEmpty(charset)) {
			charset = StringUtil.UTF_8;
		}
		if (key == null || key.length() < 8) {
			throw new Exception("File encryption failed,Key at least 8 bytes.");
		}
		if (key.length() > 8) {
			key = key.substring(0, 8);
		}
		if (password == null || password.length() < 8) {
			throw new Exception("File encryption failed,Password at least 8 bytes.");
		}
		IvParameterSpec iv = new IvParameterSpec(key.getBytes(charset));
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, generateKey(password, charset), iv);
		InputStream is = new FileInputStream(srcFile);
		OutputStream out = new FileOutputStream(destFile);
		CipherInputStream cis = new CipherInputStream(is, cipher);
		byte[] buffer = new byte[1024];
		int r;
		while ((r = cis.read(buffer)) > 0) {
			out.write(buffer, 0, r);
		}
		cis.close();
		is.close();
		out.close();
		return destFile;
	}

	/**
	 * DES解密文件
	 *
	 * @param srcFile  String 已加密的源文件完整路径
	 * @param destFile String 解密之后的目标文件完整路径，如果存在将覆盖
	 * @param password String 加密密码，至少8字符(UTF-8)
	 * @param key      String 加密Key，至少8字符(UTF-8)
	 * @return String  解密成功返回目标文件完整路径   null--解密失败
	 * @throws Exception
	 */
	public static String decryptFile(String srcFile, String destFile, String password, String key) throws Exception {
		return decryptFile(srcFile, destFile, password, key, "UTF-8");
	}

	/**
	 * Des decryption file
	 *
	 * @param srcFile  				Full path to encrypted source file
	 * @param destFile 				The full path of the decrypted target file. If it exists, it will be overwritten
	 * @param password				Encrypted password, at least 8 characters
	 * @param key					Encryption key, at least 8 characters
	 * @param charset				Character set
	 * @return String
	 * 		Returns the full path of the target file successfully decrypted
	 * 		null -- decryption failed
	 * @throws Exception
	 */
	public static String decryptFile(String srcFile, String destFile, String password, String key, String charset) throws Exception {
		if (Strings.isEmpty(charset)) {
			charset = StringUtil.UTF_8;
		}
		if (key == null || key.length() < 8) {
			throw new Exception("File decryption failed,Key at least 8 bytes.");
		}
		if (key.length() > 8) {
			key = key.substring(0, 8);
		}
		if (password == null || password.length() < 8) {
			throw new Exception("File decryption failed,Password at least 8 bytes.");
		}
		File file = new File(destFile);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		IvParameterSpec iv = new IvParameterSpec(key.getBytes(charset));
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, generateKey(password, charset), iv);
		InputStream is = new FileInputStream(srcFile);
		OutputStream out = new FileOutputStream(destFile);
		CipherOutputStream cos = new CipherOutputStream(out, cipher);
		byte[] buffer = new byte[1024];
		int r;
		while ((r = is.read(buffer)) >= 0) {
			cos.write(buffer, 0, r);
		}
		cos.close();
		is.close();
		out.close();
		return destFile;
	}

	/**
	 * 获取uuid
	 * @return uuid
	 */
	public static String uuid(){
		return UUID.randomUUID().toString();
	}

}

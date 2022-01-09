package com.ihltx.utility.util;

import static org.junit.Assert.assertEquals;

import com.ihltx.utility.UtilityApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


@SuppressWarnings("all")
@SpringBootTest(classes = {UtilityApplication.class})

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class SecurityUtilTest {


	private String testBaseDirPath = "/tmp/test/";
	private String currTestBaseDirPath = "/tmp/test/";

	@BeforeEach
	void setUp() {
		if(FileUtil.isWindows()){
			testBaseDirPath = "C:" + testBaseDirPath;
			currTestBaseDirPath = "C:" + currTestBaseDirPath;
		}
		currTestBaseDirPath += this.getClass().getSimpleName() + "/";
		if(!FileUtil.exists(currTestBaseDirPath)){
			FileUtil.makeDirs(currTestBaseDirPath);
		}
	}

	@Test
	public void test_10_Md5() {
		String md5 = SecurityUtil.md5("admin");
		System.out.println(md5 + "   length: " + md5.length());
		assertEquals(md5.equals("21232f297a57a5a743894a0e4a801fc3"), true);

		md5 = SecurityUtil.md5("12345678");
		System.out.println(md5);
		assertEquals(md5.equals("25d55ad283aa400af464c76d713c07ad"), true);

		md5 = SecurityUtil.md5("");
		System.out.println(md5);
		assertEquals(md5.equals("d41d8cd98f00b204e9800998ecf8427e"), true);

	}
	
	@Test
	public void test_10_Sha() {
		String sha = null;
		try {
			sha = SecurityUtil.sha("admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(sha!=null){
			System.out.println(sha + "   length: " + sha.length());
			assertEquals(sha.equals("d033e22ae348aeb5660fc2140aec35850c4da997"), true);
		}else{
			assertEquals(false, true);
		}

		sha = null;
		try {
			sha = SecurityUtil.sha("12345678");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(sha!=null){
			System.out.println(sha);
			assertEquals(sha.equals("7c222fb2927d828af22f592134e8932480637c0d"), true);
		}else {
			assertEquals(false, true);
		}

		sha = null;
		try {
			sha = SecurityUtil.sha("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(sha!=null){
			System.out.println(sha);
			assertEquals(sha.equals("da39a3ee5e6b4b0d3255bfef95601890afd80709"), true);
		}else {
			assertEquals(false, true);
		}
	}
	
	@Test
	public void test_10_Sha256() {
		String sha256 = null;
		try {
			sha256 = SecurityUtil.sha256("admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(sha256!=null){
			System.out.println(sha256 + "   length: " + sha256.length());
			assertEquals(sha256.equals("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918"), true);
		}else {
			assertEquals(false, true);
		}

		sha256 = null;
		try {
			sha256 = SecurityUtil.sha256("12345678");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(sha256!=null){
			System.out.println(sha256);
			assertEquals(sha256.equals("ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f"), true);
		}else {
			assertEquals(false, true);
		}

		sha256 = null;
		try {
			sha256 = SecurityUtil.sha256("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(sha256!=null){
			System.out.println(sha256);
			assertEquals(sha256.equals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"), true);
		}else {
			assertEquals(false, true);
		}

	}
	
	
	@Test
	public void test_10_Base64Encode() {
		String rs = null;
		try {
			rs = SecurityUtil.base64Encode("admin中国");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(rs!=null){
			System.out.println(rs + "   length: " + rs.length());
			assertEquals(rs.equals("YWRtaW7kuK3lm70="), true);
		}else {
			assertEquals(false, true);
		}

		rs = null;
		try {
			rs = SecurityUtil.base64Encode("12345678人民");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(rs!=null){
			System.out.println(rs + "   length: " + rs.length());
			assertEquals(rs.equals("MTIzNDU2NzjkurrmsJE="), true);
		}else {
			assertEquals(false, true);
		}

	}
	
	@Test
	public void test_10_Base64Decode() {
		String rs = null;
		try {
			rs = SecurityUtil.base64Decode("YWRtaW7kuK3lm70=");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(rs!=null){
			System.out.println(rs + "   length: " + rs.length());
			assertEquals(rs.equals("admin中国"), true);
		}else {
			assertEquals(false, true);
		}

		rs = null;
		try {
			rs = SecurityUtil.base64Decode("MTIzNDU2NzjkurrmsJE=");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(rs!=null){
			System.out.println(rs + "   length: " + rs.length());
			assertEquals(rs.equals("12345678人民"), true);
		}else {
			assertEquals(false, true);
		}
	}


	@Test
	public void test_10_UrlEncode() {
		String rs = null;
		try {
			rs = SecurityUtil.urlEncode("admin中国");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(rs!=null){
			System.out.println(rs + "   length: " + rs.length());
			assertEquals(rs.equals("admin%E4%B8%AD%E5%9B%BD"), true);
		}else {
			assertEquals(false, true);
		}

		rs = null;
		try {
			rs = SecurityUtil.urlEncode("12345678人民");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(rs!=null){
			System.out.println(rs + "   length: " + rs.length());
			assertEquals(rs.equals("12345678%E4%BA%BA%E6%B0%91"), true);
		}else {
			assertEquals(false, true);
		}
	}

	@Test
	public void test_10_UrlDecode() {
		String rs = null;
		try {
			rs = SecurityUtil.urlDecode("admin%E4%B8%AD%E5%9B%BD");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(rs!=null){
			System.out.println(rs + "   length: " + rs.length());
			assertEquals(rs.equals("admin中国"), true);
		}else {
			assertEquals(false, true);
		}

		rs = null;
		try {
			rs = SecurityUtil.urlDecode("12345678%E4%BA%BA%E6%B0%91");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(rs!=null){
			System.out.println(rs + "   length: " + rs.length());
			assertEquals(rs.equals("12345678人民"), true);
		}else {
			assertEquals(false, true);
		}
	}
	

	@Test
	public void test_10_Encrypt() {
		String rs = null;
		try {
			rs = SecurityUtil.encrypt("admin中国" ,"25d55ad283aa400af464c76d713c07ad","21232f297a57a5a743894a0e4a801fc3");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rs!=null){
			System.out.println(rs + "   length: " + rs.length());
			assertEquals(rs.equals("i+vyMbEFQhS4ZLDmDsGcHg=="), true);
		}else {
			assertEquals(false, true);
		}

		rs = null;
		try {
			rs = SecurityUtil.encrypt("12345678人民" ,"25d55ad283aa400af464c76d713c07ad","21232f297a57a5a743894a0e4a801fc3");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rs!=null){
			System.out.println(rs + "   length: " + rs.length());
			assertEquals(rs.equals("xRYUuUdO317tgY//HzF90Q=="), true);
		}else {
			assertEquals(false, true);
		}

	}

	@Test
	public void test_10_Decrypt() {
		String rs = null;
		try {
			rs = SecurityUtil.decrypt("i+vyMbEFQhS4ZLDmDsGcHg==" ,"25d55ad283aa400af464c76d713c07ad","21232f297a57a5a743894a0e4a801fc3");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rs!=null){
			System.out.println(rs + "   length: " + rs.length());
			assertEquals(rs.equals("admin中国"), true);
		}else {
			assertEquals(false, true);
		}

		rs = null;
		try {
			rs = SecurityUtil.decrypt("xRYUuUdO317tgY//HzF90Q==" ,"25d55ad283aa400af464c76d713c07ad","21232f297a57a5a743894a0e4a801fc3");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rs!=null){
			System.out.println(rs + "   length: " + rs.length());
			assertEquals(rs.equals("12345678人民"), true);
		}else {
			assertEquals(false, true);
		}

	}
	
	@Test
	public void test_20_EncryptFile() {
		String srcFile = currTestBaseDirPath + "test_21_DecryptFile.txt";
		String destFile = currTestBaseDirPath + "test_21_DecryptFile_target.txt";
		boolean rs = false;
		if(FileUtil.isFile(srcFile)) {
			rs = FileUtil.deleteFile(srcFile);
			assertEquals(rs, true);
		}
		String content = "中国12ab34";
		rs = false;
		try {
			rs = FileUtil.writeFile(srcFile, content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(rs, true);
		String rs1 = null;
		try {
			rs1 = SecurityUtil.encryptFile(srcFile, destFile,"25d55ad283aa400af464c76d713c07ad","21232f297a57a5a743894a0e4a801fc3");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(destFile.equals(rs1), true);
	}
	
	@Test
	public void test_21_DecryptFile() {
		String srcFile = currTestBaseDirPath + "test_21_DecryptFile_target.txt";
		String destFile = currTestBaseDirPath + "test_21_DecryptFile_decrypt.txt";
		String content = "中国12ab34";
		String rs1 = null;
		try {
			rs1 = SecurityUtil.decryptFile(srcFile, destFile,"25d55ad283aa400af464c76d713c07ad","21232f297a57a5a743894a0e4a801fc3");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(destFile.equals(rs1), true);
		String content1 = null;
		try {
			content1 = FileUtil.readFile(destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(content);
		System.out.println(content1);
		assertEquals(content.equals(content1), true);
	}

}

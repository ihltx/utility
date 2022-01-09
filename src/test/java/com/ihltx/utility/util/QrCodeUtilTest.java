package com.ihltx.utility.util;

import static org.junit.Assert.assertEquals;

import com.ihltx.utility.UtilityApplication;
import com.google.zxing.WriterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;


@SuppressWarnings("all")
@SpringBootTest(classes = {UtilityApplication.class})

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class QrCodeUtilTest {

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
	public void test_10_qrcode() {
		String filename = testBaseDirPath + "water.jpg";
		String qr_filename= currTestBaseDirPath + "test_10_qrcode.jpg";
		String rs = null;
		try {
			rs = QrCodeUtil.generate("http://www.baidu.com/", 350, 350, filename , qr_filename , false);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
		assertEquals(rs.equals(qr_filename), true);
		
		qr_filename = currTestBaseDirPath + "test_10_qrcode1.jpg";
		rs = null;
		try {
			rs = QrCodeUtil.generate("http://www.baidu.com/", 350, 350, qr_filename);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(rs.equals(qr_filename), true);
		
	}
	
}

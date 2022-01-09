package com.ihltx.utility.util;


import com.ihltx.utility.UtilityApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.krysalis.barcode4j.BarcodeException;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("all")
@SpringBootTest(classes = {UtilityApplication.class})
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class BarcodeUtilTest {

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
    public void test_10_generateFile() {
        String bar_filename= currTestBaseDirPath + "test_10_generateFile.jpg";
        File rs = null;
        try {
            rs = BarcodeUtil.generate("http://www.baidu.com",  bar_filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BarcodeException e) {
            e.printStackTrace();
        }
        assertEquals(rs==null, true);

        rs = null;
        try {
            rs = BarcodeUtil.generate("1234567890123",  bar_filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BarcodeException e) {
            e.printStackTrace();
        }
        assertEquals(rs!=null, true);
        assertEquals(rs.exists() && rs.isFile(), true);
    }

    @Test
    public void test_11_generateBytes() {
        byte[] rs = null;
        try {
            rs = BarcodeUtil.generate("http://www.baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BarcodeException e) {
            e.printStackTrace();
        }
        assertEquals(rs==null, true);

        rs = null;
        try {
            rs = BarcodeUtil.generate("1234567890123");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BarcodeException e) {
            e.printStackTrace();
        }
        assertEquals(rs!=null, true);

    }

    @Test
    public void test_11_generateOutputStream() {
        String bar_filename= currTestBaseDirPath + "test_11_generateOutputStream.jpg";
        FileOutputStream fos = null;
        try {
            assertEquals(FileUtil.deleteFile(bar_filename) , true);
            fos = new FileOutputStream(new File(bar_filename));
            assertEquals(BarcodeUtil.generate("http://www.baidu.com" , fos), false);
            fos.close();
            assertEquals(FileUtil.exists(bar_filename) , true);
            assertEquals(FileUtil.deleteFile(bar_filename) , true);
        }catch (IOException e) {
            e.printStackTrace();
            assertEquals(false, false);
        } catch (BarcodeException e) {
            e.printStackTrace();
            assertEquals(false, false);
        }finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            assertEquals(FileUtil.deleteFile(bar_filename) , true);
            fos = new FileOutputStream(new File(bar_filename));
            assertEquals(BarcodeUtil.generate("1234567890123" , fos), true);
            fos.close();
            assertEquals(FileUtil.exists(bar_filename) , true);
        } catch (IOException e) {
            e.printStackTrace();
            assertEquals(false, true);
        } catch (BarcodeException e) {
            e.printStackTrace();
            assertEquals(false, true);
        }

    }

}

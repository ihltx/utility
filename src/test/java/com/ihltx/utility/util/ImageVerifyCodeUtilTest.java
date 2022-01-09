package com.ihltx.utility.util;


import com.ihltx.utility.UtilityApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("all")
@SpringBootTest(classes = {UtilityApplication.class})

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class ImageVerifyCodeUtilTest {

    private String testBaseDirPath = "/tmp/test/";
    private String currTestBaseDirPath = "/tmp/test/";

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;


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

        request = new MockHttpServletRequest();
        request.setCharacterEncoding(StringUtil.UTF_8);
        response = new MockHttpServletResponse();
        response.setCharacterEncoding(StringUtil.UTF_8);
    }

    @Test
    public void test_10_outputImage2OutputStream()
    {
        ImageVerifyCodeUtil imageVerifyCodeUtil = new ImageVerifyCodeUtil();
        imageVerifyCodeUtil.setVerifyCodeLength(6);
        imageVerifyCodeUtil.setBackgroundImageFileName(testBaseDirPath + "2.jpg");
        String fileName = currTestBaseDirPath + "test_10_outputImage2OutputStream.jpg";
        if(FileUtil.exists(fileName)){
            FileUtil.deleteFile(fileName);
        }
        FileOutputStream fos = null;
        try {
            fos =new FileOutputStream(fileName);
            imageVerifyCodeUtil.outputImage2OutputStream(fos , null , null);
            assertEquals(true , true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            assertEquals(false , true);
        } catch (IOException e) {
            e.printStackTrace();
            assertEquals(false , true);
        } finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test_10_outputImage2Response()
    {
        ImageVerifyCodeUtil imageVerifyCodeUtil = new ImageVerifyCodeUtil();
        imageVerifyCodeUtil.setVerifyCodeLength(6);
        imageVerifyCodeUtil.setBackgroundImageFileName(testBaseDirPath + "2.jpg");
        request.getSession().setAttribute("aaa","bbb");
        try {
            imageVerifyCodeUtil.outputImage2Response(request , response);
            assertEquals(true , true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            assertEquals(false , true);
        } catch (IOException e) {
            e.printStackTrace();
            assertEquals(false , true);
        } finally {
        }
        assertEquals(imageVerifyCodeUtil.getVerifyCode().equals(request.getSession().getAttribute(imageVerifyCodeUtil.getVerifyCodeKey())) , true);
    }
}

package com.ihltx.utility.util;


import com.ihltx.utility.UtilityApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("all")
@SpringBootTest(classes = {UtilityApplication.class})

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class FileUtilTest {

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
    public void test_10_isWindows()
    {
        assertEquals(FileUtil.isWindows(), true);
    }

    @Test
    public void test_20_exists()
    {
        assertEquals(FileUtil.exists(""), false);
        assertEquals(FileUtil.exists(null), false);

        if(FileUtil.isWindows()){
            assertEquals(FileUtil.exists("C:/"), true);
        }else{
            assertEquals(FileUtil.exists("/bin"), true);
        }

        if(FileUtil.isWindows()){
            assertEquals(FileUtil.exists("C:/123456_78901234"), false);
        }else{
            assertEquals(FileUtil.exists("/bin/123456_78901234"), false);
        }

        if(FileUtil.isWindows()){
            assertEquals(FileUtil.exists("C:/Windows/System32/cmd.exe"), true);
            assertEquals(FileUtil.exists("C:/Windows/System32/cmd_1234567890.exe"), false);
        }else{
            assertEquals(FileUtil.exists("/bin/bash"), true);
            assertEquals(FileUtil.exists("/bin/bash_1234567890"), false);
        }
    }


    @Test
    public void test_30_makeDirs()
    {
        String filename  = "";
        filename  = currTestBaseDirPath + "abcd";
        Boolean rs = FileUtil.deleteDir(filename);
        assertEquals(rs, true);

        filename  = currTestBaseDirPath + "abcd/1234/5678";
        rs = FileUtil.makeDirs(filename);
        assertEquals(rs, true);

        filename  = currTestBaseDirPath + "abcd";
        rs = FileUtil.isDir(filename);
        assertEquals(rs, true);

        filename  = currTestBaseDirPath + "abcd/1234";
        rs = FileUtil.isDir(filename);
        assertEquals(rs, true);

        filename  = currTestBaseDirPath + "abcd/1234/5678";
        rs = FileUtil.isDir(filename);
        assertEquals(rs, true);

    }

    @Test
    public void test_40_isFile() {
        String filename  = "";
        Boolean rs = FileUtil.isFile(filename);
        assertEquals(rs, false);

        filename  = currTestBaseDirPath;
        rs = FileUtil.isFile(filename);
        assertEquals(rs, false);

        filename  = currTestBaseDirPath + "2.txt";
        if(FileUtil.exists(filename)){
            rs = FileUtil.deleteFile(filename);
            assertEquals(rs, true);
        }
        rs = FileUtil.isFile(filename);
        assertEquals(rs, false);

        try {
            rs = FileUtil.writeFile(filename, "123456");
        } catch (IOException e) {
            e.printStackTrace();
            rs = false;
        }
        assertEquals(rs, true);

        rs = FileUtil.isFile(filename);
        assertEquals(rs, true);
    }

    @Test
    public void test_50_isDir() {
        String filename  = "";
        boolean rs = FileUtil.isDir(filename);
        assertEquals(rs, false);

        filename  = currTestBaseDirPath;
        rs = FileUtil.isDir(filename);
        assertEquals(rs, true);

        filename  = currTestBaseDirPath + "2.txt";
        if(FileUtil.exists(filename)){
            rs = FileUtil.deleteFile(filename);
            assertEquals(rs, true);
        }

        rs = FileUtil.isDir(filename);
        assertEquals(rs, false);

        try {
            rs = FileUtil.writeFile(filename, "123456");
        } catch (IOException e) {
            e.printStackTrace();
            rs = false;
        }
        assertEquals(rs, true);

        rs = FileUtil.isDir(filename);
        assertEquals(rs, false);

        filename  = currTestBaseDirPath + "temp";
        if(FileUtil.exists(filename)){
            rs = FileUtil.deleteDir(filename);
            assertEquals(rs, true);
        }

        rs = FileUtil.makeDirs(filename);
        assertEquals(rs, true);

        rs = FileUtil.isDir(filename);
        assertEquals(rs, true);
    }


    @Test
    public void test_60_getFilePath() {
        String filename  = "";
        filename  = currTestBaseDirPath + "abcd";
        String parentPath = FileUtil.getFilePath(filename);
        parentPath = parentPath.replaceAll("\\\\", "/") + "/";
        assertEquals(parentPath.equals(currTestBaseDirPath), true);
    }

    @Test
    public void test_70_getFileBaseName() {
        String filename  = "";
        filename  = currTestBaseDirPath + "abcd";
        String basename = FileUtil.getFileBaseName(filename);
        assertEquals(basename.equals("abcd"), true);

        filename  = "";
        basename = FileUtil.getFileBaseName(filename);
        assertEquals(basename.equals(""), true);


        filename  = "d:/";
        basename = FileUtil.getFileBaseName(filename);
        assertEquals(basename.equals(""), true);

        filename  = "d:";
        basename = FileUtil.getFileBaseName(filename);
        assertEquals(basename.equals(""), true);

    }

    @Test
    public void test_80_getFileExtName() {
        String filename  = "";
        filename  = currTestBaseDirPath + "abcd";
        String extname = FileUtil.getFileExtName(filename);
        assertEquals(extname.equals(""), true);

        filename  =  currTestBaseDirPath + "abcd.txt";
        extname = FileUtil.getFileExtName(filename);
        assertEquals(extname.equals("txt"), true);


        filename  =  currTestBaseDirPath + "/abcd.abcd.jpg.txt";
        extname = FileUtil.getFileExtName(filename);
        assertEquals(extname.equals("txt"), true);


        filename  =  currTestBaseDirPath;
        extname = FileUtil.getFileExtName(filename);
        assertEquals(extname.equals(""), true);

        filename  =  "";
        extname = FileUtil.getFileExtName(filename);
        assertEquals(extname.equals(""), true);

        filename  =  "d:/";
        extname = FileUtil.getFileExtName(filename);
        assertEquals(extname.equals(""), true);

        filename  =  "d:";
        extname = FileUtil.getFileExtName(filename);
        assertEquals(extname.equals(""), true);

    }

    @Test
    public void test_90_writeFile() {
        String filename  = "";
        filename  = currTestBaseDirPath + "test_90_writeFile.txt";
        Boolean rs = false;
        if(FileUtil.isFile(filename)){
            rs = FileUtil.deleteFile(filename);
            assertEquals(rs, true);
        }
        if(FileUtil.isDir(filename)){
            rs = FileUtil.deleteDir(filename);
            assertEquals(rs, true);
        }
        String content ="中国1233工";
        try {
            rs = FileUtil.writeFile(filename , content);
        } catch (IOException e) {
            rs = false;
            e.printStackTrace();
        }
        assertEquals(rs, true);

        String content1 = null;
        try {
            content1 = FileUtil.readFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(content1!=null){
            assertEquals(content1.equals(content), true);
        }else{
            assertEquals(false, true);
        }

        content ="中国1233工1111113333";
        try {
            rs = FileUtil.writeFile(filename , content);
        } catch (IOException e) {
            e.printStackTrace();
            rs = false;
        }
        assertEquals(rs, true);

        content1 = null;
        try {
            content1 = FileUtil.readFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(content1!=null){
            assertEquals(content1.equals(content), true);
        }else{
            assertEquals(false, true);
        }

    }


    @Test
    public void test_100_appendFile() {
        String filename  = "";
        filename  = currTestBaseDirPath + "test_16_appendFile.txt";
        Boolean rs = false;
        if(FileUtil.isFile(filename)){
            rs = FileUtil.deleteFile(filename);
            assertEquals(rs, true);
        }
        if(FileUtil.isDir(filename)){
            rs = FileUtil.deleteDir(filename);
            assertEquals(rs, true);
        }
        String content ="中国1233工";
        try {
            rs = FileUtil.appendFile(filename , content);
        } catch (IOException e) {
            e.printStackTrace();
            rs = false;
        }
        assertEquals(rs, true);

        String content1 = null;
        try {
            content1 = FileUtil.readFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(content1!=null){
            assertEquals(content1.equals(content), true);
        }else{
            assertEquals(false, true);
        }

        content ="中国1233工1111113333";
        try {
            rs = FileUtil.appendFile(filename , content);
        } catch (IOException e) {
            e.printStackTrace();
            rs = false;
        }
        assertEquals(rs, true);

        try {
            content1 = FileUtil.readFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
            content1 = null;
        }
        if(content1!=null){
            assertEquals(content1.equals("中国1233工" + content), true);
        }else{
            assertEquals(false, true);
        }

    }

    @Test
    public void test_110_rename() {
        String srcfilename = currTestBaseDirPath + "test_110_rename.txt";
        String targetfilename = currTestBaseDirPath + "test_110_rename.target.txt";
        Boolean rs = false;
        if(FileUtil.isFile(srcfilename)){
            rs = FileUtil.deleteFile(srcfilename);
            assertEquals(rs, true);
        }
        if(FileUtil.isDir(srcfilename)){
            rs = FileUtil.deleteDir(srcfilename);
            assertEquals(rs, true);
        }

        if(FileUtil.isFile(targetfilename)){
            rs = FileUtil.deleteFile(targetfilename);
            assertEquals(rs, true);
        }
        if(FileUtil.isDir(targetfilename)){
            rs = FileUtil.deleteDir(targetfilename);
            assertEquals(rs, true);
        }


        try {
            rs = FileUtil.writeFile(srcfilename , "1234");
        } catch (IOException e) {
            e.printStackTrace();
            rs = false;
        }
        assertEquals(rs, true);

        rs = FileUtil.rename(srcfilename , targetfilename);
        assertEquals(rs, true);

        rs = FileUtil.rename(srcfilename , targetfilename);
        assertEquals(rs, false);
    }

    @Test
    public void test_120_move() {
        String srcfilename = currTestBaseDirPath + "test_120_move.txt";
        String targetfilename = currTestBaseDirPath + "temp/test_120_move.txt";
        String targetdirname = currTestBaseDirPath + "temp";
        Boolean rs =  false;
        if(FileUtil.isFile(srcfilename)){
            rs = FileUtil.deleteFile(srcfilename);
            assertEquals(rs, true);
        }
        if(FileUtil.isDir(srcfilename)){
            rs = FileUtil.deleteDir(srcfilename);
            assertEquals(rs, true);
        }

        if(FileUtil.isFile(targetdirname)){
            rs = FileUtil.deleteFile(targetdirname);
            assertEquals(rs, true);
        }
        if(FileUtil.isDir(targetdirname)){
            rs = FileUtil.deleteDir(targetdirname);
            assertEquals(rs, true);
        }

        rs = FileUtil.makeDirs(targetdirname);
        assertEquals(rs, true);


        if(FileUtil.isFile(targetfilename)){
            rs = FileUtil.deleteFile(targetfilename);
            assertEquals(rs, true);
        }
        if(FileUtil.isDir(targetfilename)){
            rs = FileUtil.deleteDir(targetfilename);
            assertEquals(rs, true);
        }

        try {
            rs = FileUtil.writeFile(srcfilename , "1234");
        } catch (IOException e) {
            e.printStackTrace();
            rs = false;
        }
        assertEquals(rs, true);

        rs = FileUtil.move(srcfilename , targetfilename);
        assertEquals(rs, true);

        rs = FileUtil.exists(srcfilename);
        assertEquals(rs, false);

        rs = FileUtil.exists(targetfilename);
        assertEquals(rs, true);

        srcfilename = currTestBaseDirPath + "move";
        if(FileUtil.isFile(srcfilename)){
            rs = FileUtil.deleteFile(srcfilename);
            assertEquals(rs, true);
        }
        if(FileUtil.isDir(srcfilename)){
            rs = FileUtil.deleteDir(srcfilename);
            assertEquals(rs, true);
        }

        rs = FileUtil.makeDirs(srcfilename);
        assertEquals(rs, true);

        try {
            rs = FileUtil.writeFile(srcfilename + "test_120_move" , "1234");
        } catch (IOException e) {
            e.printStackTrace();
            rs = false;
        }
        assertEquals(rs, true);

        targetfilename = currTestBaseDirPath + "temp/move";
        rs = FileUtil.deleteDir(targetfilename);
        assertEquals(rs, true);

        rs = FileUtil.move(srcfilename , targetfilename);
        assertEquals(rs, true);

        rs = FileUtil.exists(srcfilename);
        assertEquals(rs, false);

        rs = FileUtil.exists(targetfilename);
        assertEquals(rs, true);

        srcfilename = currTestBaseDirPath + "temp/move";
        targetfilename = currTestBaseDirPath + "move12";
        rs = FileUtil.deleteDir(targetfilename);
        assertEquals(rs, true);

        rs = FileUtil.move(srcfilename , targetfilename);
        assertEquals(rs, true);

        rs = FileUtil.exists(srcfilename);
        assertEquals(rs, false);

        rs = FileUtil.exists(targetfilename);
        assertEquals(rs, true);

    }

    @Test
    public void test_130_copy() {
        String srcfilename = currTestBaseDirPath + "test_130_copy.txt";
        String targetfilename = currTestBaseDirPath + "temp/test_130_copy.txt";
        Boolean rs = false;

        if(FileUtil.isDir(currTestBaseDirPath + "temp")){
            rs = FileUtil.deleteDir(currTestBaseDirPath + "temp");
            assertEquals(rs, true);
        }
        rs = FileUtil.makeDirs(currTestBaseDirPath + "temp");
        assertEquals(rs, true);

        if(FileUtil.isFile(srcfilename)){
            FileUtil.deleteFile(srcfilename);
            assertEquals(rs, true);
        }
        if(FileUtil.isDir(srcfilename)){
            FileUtil.deleteDir(srcfilename);
            assertEquals(rs, true);
        }

        if(FileUtil.isFile(targetfilename)){
            FileUtil.deleteFile(targetfilename);
            assertEquals(rs, true);
        }
        if(FileUtil.isDir(targetfilename)){
            FileUtil.deleteDir(targetfilename);
            assertEquals(rs, true);
        }


        try {
            rs = FileUtil.writeFile(srcfilename , "1234");
        } catch (IOException e) {
            e.printStackTrace();
            rs = false;
        }
        assertEquals(rs, true);

        try {
            rs = FileUtil.copy(srcfilename , targetfilename , true);
        } catch (IOException e) {
            e.printStackTrace();
            rs = false;
        }
        assertEquals(rs, true);

        rs = FileUtil.exists(srcfilename);
        assertEquals(rs, true);

        rs = FileUtil.exists(targetfilename);
        assertEquals(rs, true);

        String content = null;
        try {
            content = FileUtil.readFile(targetfilename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(content !=null ){
            assertEquals(content.equals("1234"), true);
        }else{
            assertEquals(false, true);
        }


        try {
            rs = FileUtil.copy(srcfilename , targetfilename , false);
        } catch (IOException e) {
            e.printStackTrace();
            rs = false;
        }
        assertEquals(rs, false);


        targetfilename = currTestBaseDirPath + "temp/copy_1234";
        rs = FileUtil.deleteDir(targetfilename);
        assertEquals(rs, true);
        rs = FileUtil.makeDirs(targetfilename);
        assertEquals(rs, true);

        try {
            rs = FileUtil.copy(srcfilename , targetfilename);
        } catch (IOException e) {
            e.printStackTrace();
            rs = false;
        }
        assertEquals(rs, false);

    }

    @Test
    public void test_140_replace() {
        String srcfilename = currTestBaseDirPath + "test_140_replace.txt";
        Boolean rs = FileUtil.deleteFile(srcfilename);
        assertEquals(rs, true);

        try {
            rs = FileUtil.writeFile(srcfilename , "a12b34r");
        } catch (IOException e) {
            e.printStackTrace();
            rs = false;
        }
        assertEquals(rs, true);

        String content= null;
        try {
            content = FileUtil.replace(srcfilename, "[0-9]+" ,"***");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(content!=null){
            assertEquals(content.equals("a***b***r"), true);
        }else{
            assertEquals(false, true);
        }

    }

}

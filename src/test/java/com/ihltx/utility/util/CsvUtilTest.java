package com.ihltx.utility.util;


import com.ihltx.utility.UtilityApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("all")
@SpringBootTest(classes = {UtilityApplication.class})

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class CsvUtilTest {

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
    public void test_10_writeCSV() {
        String filename  = currTestBaseDirPath + "002498.csv";
        Boolean rs = false;
        if(FileUtil.exists(filename)){
           rs = FileUtil.deleteFile(filename);
            assertEquals(rs, true);
        }
        List<List<String>> datas = new ArrayList<>();
        List<String> headers =new ArrayList<>();
        headers.add("id");
        headers.add("studentName");
        headers.add("studentAge");
        headers.add("studentSex");
        datas.add(headers);

        List<String> data =new ArrayList<>();
        data.add("100001");
        data.add("zhang san");
        data.add("25");
        data.add("male");
        datas.add(data);

        data =new ArrayList<>();
        data.add("100002");
        data.add("li shi");
        data.add("23");
        data.add("male");
        datas.add(data);

        data =new ArrayList<>();
        data.add("100005");
        data.add("wang wu");
        data.add("29");
        data.add("female");
        datas.add(data);

        data =new ArrayList<>();
        data.add("100008");
        data.add("李莉");
        data.add("29");
        data.add("女");
        datas.add(data);
        try {
            CsvUtil.writeCSV(filename, datas);
            assertEquals(true, true);
        } catch (IOException e) {
            assertEquals(false, true);
            e.printStackTrace();
        }
    }

    @Test
    public void test_20_readCSV() {
        String filename  = currTestBaseDirPath +"002498.csv";
        if(FileUtil.exists(filename)){
            try {
                List<List<String>> datas = CsvUtil.readCSV(filename);
                System.out.println(datas);
                assertEquals(datas.size()==5, true);
                assertEquals(datas.get(4).get(1).equals("李莉"), true);
            } catch (IOException e) {
                assertEquals(false, true);
                e.printStackTrace();
            }
        }else{
            assertEquals(false, true);
        }
    }

    @Test
    public void test_30_readCSVByReader() {
        String content  = "日期,股票代码,名称,收盘价,最高价,最低价,开盘价,前收盘,涨跌额,涨跌幅,换手率,成交量,成交金额,总市值,流通市值,成交笔数\r\n" +
                "2021-02-26,'300948,C冠中,59.0,71.5,58.0,61.5,71.69,-12.69,-17.7012,63.3813,14027620,883367096.44,5507060000.0,1305793959.0,51677\r\n" +
                "2021-02-25,'300948,N冠中,71.69,71.69,53.0,54.9,13.0,58.69,451.4615,77.9373,17249152,1029331915.79,6691544600.0,1586650320.69,84398\r\n";
        try {
            Reader reader = StringUtil.StringToBufferedReader(content);
            List<List<String>> data = CsvUtil.readCSVByReader(reader);
            System.out.println(data);
            assertEquals(true, true);
        } catch (IOException e) {
            assertEquals(false, true);
            e.printStackTrace();
        }
    }

}

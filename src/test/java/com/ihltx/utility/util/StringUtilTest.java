package com.ihltx.utility.util;


import com.ihltx.utility.UtilityApplication;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("all")
@SpringBootTest(classes = {UtilityApplication.class})

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class StringUtilTest {

    @Test
    public void test_10_startsWith()
    {
        assertEquals(StringUtil.startsWithi(null, null), false);
        assertEquals(StringUtil.startsWithi(null, ""), false);
        assertEquals(StringUtil.startsWithi("", null), false);
        assertEquals(StringUtil.startsWithi("", ""), true);
        assertEquals(StringUtil.startsWithi(null, "my"), false);
        assertEquals(StringUtil.startsWithi("", "my"), false);

        assertEquals(StringUtil.startsWith(null, null), false);
        assertEquals(StringUtil.startsWith(null, ""), false);
        assertEquals(StringUtil.startsWith("", null), false);
        assertEquals(StringUtil.startsWith("", ""), true);
        assertEquals(StringUtil.startsWith(null, "my"), false);
        assertEquals(StringUtil.startsWith("", "my"), false);


        String s = "My name is abc";
        assertEquals(StringUtil.startsWithi(s, "my"), true);
        assertEquals(StringUtil.startsWithi(s, "my n"), true);
        assertEquals(StringUtil.startsWithi(s, "My n"), true);
        assertEquals(StringUtil.startsWithi(s, ""), true);

        assertEquals(StringUtil.startsWith(s, "my"), false);
        assertEquals(StringUtil.startsWith(s, "my n"), false);
        assertEquals(StringUtil.startsWith(s, "My n"), true);
        assertEquals(StringUtil.startsWith(s, ""), true);
    }

    @Test
    public void test_20_endsWith() {

        assertEquals(StringUtil.endsWithi(null, null), false);
        assertEquals(StringUtil.endsWithi(null, ""), false);
        assertEquals(StringUtil.endsWithi("", null), false);
        assertEquals(StringUtil.endsWithi("", ""), true);
        assertEquals(StringUtil.endsWithi(null, "my"), false);
        assertEquals(StringUtil.endsWithi("", "my"), false);

        assertEquals(StringUtil.endsWith(null, null), false);
        assertEquals(StringUtil.endsWith(null, ""), false);
        assertEquals(StringUtil.endsWith("", null), false);
        assertEquals(StringUtil.endsWith("", ""), true);
        assertEquals(StringUtil.endsWith(null, "my"), false);
        assertEquals(StringUtil.endsWith("", "my"), false);

        String s = "My name is abc";
        assertEquals(StringUtil.endsWithi(s, "abc"), true);
        assertEquals(StringUtil.endsWithi(s, "s abc"), true);
        assertEquals(StringUtil.endsWithi(s, "S abC"), true);
        assertEquals(StringUtil.endsWithi(s, ""), true);

        assertEquals(StringUtil.endsWith(s, "abc"), true);
        assertEquals(StringUtil.endsWith(s, "s abc"), true);
        assertEquals(StringUtil.endsWith(s, "S abC"), false);
        assertEquals(StringUtil.endsWith(s, ""), true);

    }

    @Test
    public void test_30_getRandomString() {

        String s = StringUtil.getRandomString(0);
        assertEquals(s.equals(""), true);

        s = StringUtil.getRandomString(-10);
        assertEquals(s.equals(""), true);


        s = StringUtil.getRandomString(0, true);
        assertEquals(s.equals(""), true);

        s = StringUtil.getRandomString(-10, true);
        assertEquals(s.equals(""), true);

        s = StringUtil.getRandomString(8);
        assertEquals(s.length()==8, true);

        s = StringUtil.getRandomString(8 , true);
        assertEquals(s.length()==8, true);

    }

    @Test
    public void test_40_trim() {

        String s = null;
        String s1 = StringUtil.trim(s);

        assertEquals(s1==null, true);

        s = "";
        s1 = StringUtil.trim(s);
        assertEquals(s1.equals(""), true);

        s = "  My name is abc  ";
        s1 = StringUtil.trim(s);
        assertEquals(s1.equals("My name is abc"), true);

        s = "\t\t  My name is abc  \t\t";
        s1 = StringUtil.trim(s);
        assertEquals(s1.equals("My name is abc"), true);

        s = "\t \t  My name is abc  \t \t";
        s1 = StringUtil.trim(s);
        assertEquals(s1.equals("My name is abc"), true);

        s = " 　　　   My name is abc     ";
        s1 = StringUtil.trim(s);
        assertEquals(s1.equals("My name is abc"), true);

        s = "  　 　 　   My name is abc  　 　   ";
        s1 = StringUtil.trim(s);
        assertEquals(s1.equals("My name is abc"), true);

        s = "  　 　\t 　   My name is abc  　 　\t   ";
        s1 = StringUtil.trim(s);
        assertEquals(s1.equals("My name is abc"), true);


        s = null;
        s1 = StringUtil.trim(s ,null);
        assertEquals(s1==null, true);

        s = null;
        s1 = StringUtil.trim(s ,"MmCc");
        assertEquals(s1==null, true);

        s = "";
        s1 = StringUtil.trim(s ,null);
        assertEquals(s1.equals(""), true);

        s = "";
        s1 = StringUtil.trim(s ,"");
        assertEquals(s1.equals(""), true);

        s = "";
        s1 = StringUtil.trim(s ,"MmCc");
        assertEquals(s1.equals(""), true);


        s = "Mcy name is abcM";
        s1 = StringUtil.trim(s ,"MmCc");
        assertEquals(s1.equals("y name is ab"), true);

        s = "Mdcy name is abcM";
        s1 = StringUtil.trim(s ,"MmCc");
        assertEquals(s1.equals("y name is ab"), false);

        assertEquals(s1.equals("dcy name is ab"), true);
    }

    @Test
    public void test_50_ltrim() {

        String s = null;
        String s1 = StringUtil.ltrim(s);

        assertEquals(s1==null, true);

        s = "";
        s1 = StringUtil.ltrim(s);
        assertEquals(s1.equals(""), true);

        s = "  My name is abc";
        s1 = StringUtil.ltrim(s);
        assertEquals(s1.equals("My name is abc"), true);

        s = "\t\t  My name is abc";
        s1 = StringUtil.ltrim(s);
        assertEquals(s1.equals("My name is abc"), true);

        s = "\t \t  My name is abc";
        s1 = StringUtil.ltrim(s);
        assertEquals(s1.equals("My name is abc"), true);

        s = " 　　　   My name is abc";
        s1 = StringUtil.ltrim(s);
        assertEquals(s1.equals("My name is abc"), true);

        s = "  　 　 　   My name is abc";
        s1 = StringUtil.ltrim(s);
        assertEquals(s1.equals("My name is abc"), true);

        s = "  　 　\t 　   My name is abc";
        s1 = StringUtil.ltrim(s);
        assertEquals(s1.equals("My name is abc"), true);


        s = null;
        s1 = StringUtil.ltrim(s ,null);
        assertEquals(s1==null, true);

        s = null;
        s1 = StringUtil.ltrim(s ,"MmCc");
        assertEquals(s1==null, true);

        s = "";
        s1 = StringUtil.ltrim(s ,null);
        assertEquals(s1.equals(""), true);

        s = "";
        s1 = StringUtil.ltrim(s ,"");
        assertEquals(s1.equals(""), true);

        s = "";
        s1 = StringUtil.ltrim(s ,"MmCc");
        assertEquals(s1.equals(""), true);


        s = "Mcy name is abc";
        s1 = StringUtil.ltrim(s ,"MmCc");
        assertEquals(s1.equals("y name is abc"), true);

        s = "Mdcy name is abc";
        s1 = StringUtil.ltrim(s ,"MmCc");
        assertEquals(s1.equals("y name is abc"), false);

        assertEquals(s1.equals("dcy name is abc"), true);
    }

    @Test
    public void test_60_rtrim() {

        String s = null;
        String s1 = StringUtil.rtrim(s);

        assertEquals(s1==null, true);

        s = "";
        s1 = StringUtil.rtrim(s);
        assertEquals(s1.equals(""), true);

        s = "My name is abc  ";
        s1 = StringUtil.rtrim(s);
        assertEquals(s1.equals("My name is abc"), true);

        s = "My name is abc  \t\t";
        s1 = StringUtil.rtrim(s);
        assertEquals(s1.equals("My name is abc"), true);

        s = "My name is abc  \t \t";
        s1 = StringUtil.rtrim(s);
        assertEquals(s1.equals("My name is abc"), true);

        s = "My name is abc     ";
        s1 = StringUtil.rtrim(s);
        assertEquals(s1.equals("My name is abc"), true);

        s = "My name is abc  　 　   ";
        s1 = StringUtil.rtrim(s);
        assertEquals(s1.equals("My name is abc"), true);

        s = "My name is abc  　 　\t   ";
        s1 = StringUtil.rtrim(s);
        assertEquals(s1.equals("My name is abc"), true);


        s = null;
        s1 = StringUtil.rtrim(s ,null);
        assertEquals(s1==null, true);

        s = null;
        s1 = StringUtil.rtrim(s ,"MmCc");
        assertEquals(s1==null, true);

        s = "";
        s1 = StringUtil.rtrim(s ,null);
        assertEquals(s1.equals(""), true);

        s = "";
        s1 = StringUtil.rtrim(s ,"");
        assertEquals(s1.equals(""), true);

        s = "";
        s1 = StringUtil.rtrim(s ,"MmCc");
        assertEquals(s1.equals(""), true);

        s = "My name is abcM";
        s1 = StringUtil.rtrim(s, "MmCc");
        assertEquals(s1.equals("My name is ab"), true);

        s = "Mdcy name is abac";
        s1 = StringUtil.rtrim(s, "MmCc");
        assertEquals(s1.equals("Mdcy name is abc"), false);

        assertEquals(s1.equals("Mdcy name is aba"), true);

    }

    @Test
    public void test_70_captureName() {
        String s = null;
        String s1 = StringUtil.captureName(s);
        assertEquals(s1==null, true);

        s = "";
        s1 = StringUtil.captureName(s);
        assertEquals(s1.equals(""), true);

        s = "my name is abc";
        s1 = StringUtil.captureName(s);
        assertEquals(s1.equals("My name is abc"), true);

    }
}

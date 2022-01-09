package com.ihltx.utility.util;


import com.ihltx.utility.UtilityApplication;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("all")
@SpringBootTest(classes = {UtilityApplication.class})

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class DateUtilTest {

    @Test
    public void test_10_Date() {
        String rs = DateUtil.date();
        System.out.println(rs);
        assertEquals(rs.length(), 19);

        Calendar c1 = Calendar.getInstance();
        c1.set(2020, 5, 8, 16, 10, 23);
        c1.setTimeInMillis(1591603823475L);
        Date d = c1.getTime();
        rs = DateUtil.date(d);
        System.out.println(rs);
        assertEquals(rs.equals("2020-06-08 16:10:23"), true);

        rs = DateUtil.date("yyyy-MM-dd HH:mm:ss");
        System.out.println(rs);
        assertEquals(rs.length(), 19);

        rs = DateUtil.date("yyyy-MM-dd HH:mm");
        System.out.println(rs);
        assertEquals(rs.length(), 16);

        rs = DateUtil.date(d,"yyyy-MM-dd HH:mm:ss");
        System.out.println(rs);
        assertEquals(rs.equals("2020-06-08 16:10:23"), true);

        rs = DateUtil.date(d,"HH:mm:ss");
        System.out.println(rs);
        assertEquals(rs.equals("16:10:23"), true);

        long t = DateUtil.getTime();
        System.out.println(t);
        assertEquals(t>0, true);

        t = DateUtil.getTimeMillis();
        System.out.println(t);
        assertEquals(t>0, true);


        t = DateUtil.getTime(d);
        System.out.println(t);
        assertEquals(t==1591603823, true);

        t = DateUtil.getTimeMillis(d);
        System.out.println(t);
        assertEquals(t==1591603823475L, true);

        try {
            String date = DateUtil.addDate("2021-03-09",1);
            assertEquals(date.equals("2021-03-10"), true);
            System.out.println(date);
        } catch (ParseException e) {
            assertEquals(false, true);
            e.printStackTrace();
        }

        try {
            String date = DateUtil.addDate("2021-03-01",-1);
            assertEquals(date.equals("2021-02-28"), true);
            System.out.println(date);
        } catch (ParseException e) {
            assertEquals(false, true);
            e.printStackTrace();
        }


    }

}

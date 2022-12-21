package com.acem.demo;

import com.acem.demo.scrapper.Scrapper;
import com.acem.demo.util.HolidayUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws ParseException {

//        List<Date> holidayList = HolidayUtil.findAllHolidays("2026");
            Map<Date, String> map = HolidayUtil.findAllHolidaysUsingMap("2026");
//
//        for (Date date:
//             holidayList) {
//            System.out.println(date);
//        }

        for (Map.Entry<Date, String> entry : map.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }


    }
}

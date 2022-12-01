package com.acem.demo;

import com.acem.demo.util.HolidayUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {

        List<Date> holidayList = HolidayUtil.findAllHolidays("2026");

        for (Date date:
             holidayList) {
            System.out.println(date);
        }


    }
}

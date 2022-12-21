package com.acem.demo.util;

import com.acem.demo.constants.HolidayConstants;
import com.acem.demo.scrapper.Scrapper;
import com.acem.demo.scrapper.impl.HolidayScrapperImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class HolidayUtil {

    public static List<Date> findAllHolidays(String year){
        Scrapper<Date, String> scrapper = new HolidayScrapperImpl();

        return mergeAndSort(scrapper.scrap(year),findAllSaturdayOfTheYear(year));
    }

    public static Map<Date, String> findAllHolidaysUsingMap(String year){
        Scrapper<Date, String> scrapper = new HolidayScrapperImpl();

        Map holidayMap = scrapper.scrapMap("2022");
        return holidayMap;
    }

    public static List<Date> mergeAndSort(List<Date> arrayList1, List<Date> arrayList2){
        arrayList1.removeAll(arrayList2);
        arrayList1.addAll(arrayList2);

        Collections.sort(arrayList1);

        return arrayList1;
    }

    public static List<Date> findAllSaturdayOfTheYear(String year){
        List<Date> dates = new ArrayList<>();
        try{
            Long.parseLong(year);
        }catch (NumberFormatException ex){
            System.out.println("NumberFormatException occurred. " + ex.getMessage());
        }
        Integer intYear = Integer.parseInt(year);
        LocalDate now = LocalDate.of(intYear, Month.JANUARY, 1);
        // Find the first Sunday of the year
        LocalDate saturday = now.with(firstInMonth(DayOfWeek.SATURDAY));
        String temp;
        do {
            temp = saturday.format(DateTimeFormatter.ofPattern("dd MMM")) + " " + year;
            dates.add(HolidayUtil.toDate(temp));
            saturday = saturday.plus(Period.ofDays(7));
        } while (saturday.getYear() == intYear);
        return dates;
    }

    public static Date toDate(String date){
        Date date1 = null;
        try{
             date1 = new SimpleDateFormat(HolidayConstants.DATE_FORMAT).parse(date);
           // System.out.println(date1);
        }catch (ParseException ex){
            System.out.println("ParseException occurred." + ex.getMessage());
        }
        return date1;
    }

}

package com.acem.demo.scrapper.impl;

import com.acem.demo.constants.HolidayConstants;
import com.acem.demo.scrapper.Scrapper;
import com.acem.demo.util.HolidayUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class HolidayScrapperImpl implements Scrapper<Date, String> {

    @Override
    public List<Date> scrap(String year) {
        List<Date> dates = new ArrayList<>();
        Document doc = getDoc(buildURL(year));
        Elements tableElements = doc.select(HolidayConstants.TABLE);
        int i=0;
        String temp;
        for(Element row: tableElements){
            //map first two element of the row.
            temp = row.select("td").get(0).text() + " " + year;
            dates.add(HolidayUtil.toDate(temp));
        }
        return dates;
    }

    @Override
    public Map<Date, String> scrapMap(String year) {
        Map<Date, String> holidayMap = new HashMap<>();
        Document doc = getDoc(buildURL(year));
        Elements tableElements = doc.select(HolidayConstants.TABLE);
        int i=0;
        String temp1, temp2;
        for(Element row: tableElements){
            //map first two element of the row.
            temp1 = row.select("td").get(0).text() + " " + year;
            temp2 = row.select("td").get(2).text() + " " + year;
            Date date = HolidayUtil.toDate(temp1);
            holidayMap.put(date,temp2);
        }
        return holidayMap;
    }
    @Override
    public Document getDoc(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return doc;
    }

    public String buildURL(String year){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(HolidayConstants.URL);
        try{
            Long.parseLong(year);
            stringBuilder.append(year);
        }catch (NumberFormatException ex){
            System.out.println("NumberFormatException occurred. " + ex.getMessage());
        }
        stringBuilder.append("-dates/");

        return stringBuilder.toString();
    }
}

package com.acem.demo.scrapper.impl;

import com.acem.demo.constants.Constants;
import com.acem.demo.scrapper.Scrapper;
import com.acem.demo.util.HolidayUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class HolidayScrapperImpl implements Scrapper<Date> {

    @Override
    public List<Date> scrap(String year) {
        List<Date> dates = new ArrayList<>();
        Document doc = getDoc(buildURL(year));
        Elements tableElements = doc.select(Constants.TABLE);
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
        stringBuilder.append(Constants.URL);
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

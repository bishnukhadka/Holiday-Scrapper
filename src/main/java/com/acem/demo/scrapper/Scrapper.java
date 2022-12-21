package com.acem.demo.scrapper;

import org.jsoup.nodes.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface Scrapper<T,U> {

    public <T> List<T> scrap(String year);

    public <T,U> Map<T, U> scrapMap(String year);

    public Document getDoc(String url);
}

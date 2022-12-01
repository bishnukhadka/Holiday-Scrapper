package com.acem.demo.scrapper;

import java.util.List;

public interface Scrapper<T> {

    public <T> List<T> scrap(String url);
}

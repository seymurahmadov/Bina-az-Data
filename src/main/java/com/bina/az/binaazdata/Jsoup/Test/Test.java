package com.bina.az.binaazdata.Jsoup.Test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect(" https://bina.az/alqi-satqi?page=5000").get();
        Element elements = document.getElementsByTag("p").first();

        System.out.println(elements.text());
    }
}

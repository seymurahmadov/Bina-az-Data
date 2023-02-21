package com.bina.az.binaazdata.Jsoup.Test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupTestPage {
    public static void main(String[] args) throws IOException {
        //2354

        boolean endPage = true;
        int page = 1;

        int i = page;
        while (endPage) {


            Document pageTest = Jsoup.connect(" https://bina.az/alqi-satqi?page=" + i).get();
            Element elements = pageTest.getElementsByTag("p").first();

            if (elements.text().equals("Heç bir elan tapılmayıb")) {

                endPage = false;
                System.out.println("Bitti");

            } else {
                Document document = Jsoup.connect(" https://bina.az/alqi-satqi?page=" + i).get();

//                Elements div = document.select("div.items_list div.items-i").not(".vipped");
                  Elements div = document.getElementsByClass("items-i");


                for (Element element1 : div) {
                    Elements price = element1.getElementsByClass("price-val");
                    System.out.println(price.text());
                }

                i++;
            }


//        Document document = Jsoup.connect(" https://bina.az/alqi-satqi?page=2354").get();
//
//        Elements div = document.select("div.items_list div.items-i").not(".vipped");
//                   Elements price = document.getElementsByClass("price-val");
//        System.out.println(price.text());


        }


    }




}

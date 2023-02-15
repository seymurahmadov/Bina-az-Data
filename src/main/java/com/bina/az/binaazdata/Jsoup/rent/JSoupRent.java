package com.bina.az.binaazdata.Jsoup.rent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JSoupRent {
    public static void main(String[] args) throws IOException {
        Document pageCount = Jsoup.connect("https://bina.az/alqi-satqi/menziller").get();

        Elements page = pageCount.getElementsByClass("page");

        int size = page.size();
        Element element = page.get(size - 1);
        String pageNumber = element.text();

        for (int i = 1; i <= Integer.parseInt(pageNumber); i++) {

            Document document = Jsoup.connect("https://bina.az/alqi-satqi/menziller?page=" + i).get();

            Elements divRent = document.getElementsByClass("items-i");


            for (Element element1 : divRent) {
                Elements price = element1.getElementsByClass("price-val");
                Elements location = element1.getElementsByClass("location");
                Elements extract = element1.getElementsByClass("bill_of_sale");
                Elements repair = element1.getElementsByClass("repair");
                repair.tagName("span");

                Element rooms= element1.select("ul.name li").get(0);
                Element squareMeter= element1.select("ul.name li").get(1);

                try {

                    Element countOfFloor = element1.select("ul.name li").get(2);

                    System.out.println(price.text() + " AZN " + "****" + location.text()+ "****" + extract.text() +
                            "****" + repair.text() + "**** " + rooms.text() + "****" + squareMeter.text() + "****" + countOfFloor.text() );
                }catch (IndexOutOfBoundsException e){
                    System.out.println(price.text() + " AZN " + "****" + location.text()+ "****" + extract.text() +
                            "****" + repair.text() + "**** " + rooms.text() + "****" + squareMeter.text() );
                }
            }


        }
    }
}

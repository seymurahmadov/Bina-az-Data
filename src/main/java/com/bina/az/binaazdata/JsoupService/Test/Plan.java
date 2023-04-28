package com.bina.az.binaazdata.JsoupService.Test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;


public class Plan {
    public static void main(String[] args) throws IOException {

        String link = "https://turbo.az/";

        Document doc = Jsoup.connect(link).get();
        Elements productName = doc.getElementsByClass("products-i");

        for (Element product : productName) {

            Elements href = product.getElementsByAttribute("href");
            String link1=href.attr("abs:href");
            Document document = Jsoup.connect(link1).get();

            Elements idNumber = document.getElementsByClass("product-actions__id");

            //Elanın nömrəsi: 7043450
            String idNumberFinal = idNumber.text().substring(16);
//            System.out.println(idNumberFinal);

        }
    }
}

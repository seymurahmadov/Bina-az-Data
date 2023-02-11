package com.bina.az.binaazdata.Jsoup;


import com.bina.az.binaazdata.dto.PurchaseDto;
import lombok.Builder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsoupPurchase {

    public List purchaseJsoupData() throws IOException {

        List<PurchaseDto> dtoList = new ArrayList<>();

        Document pageCount = Jsoup.connect("https://bina.az/alqi-satqi/menziller").get();

        Elements page = pageCount.getElementsByClass("page");

        int size = page.size();
        Element element = page.get(size - 1);
        String pageNumber = element.text();

        for (int i = 1; i <= Integer.parseInt(pageNumber); i++) {

            Document document = Jsoup.connect("https://bina.az/alqi-satqi/menziller?page=" + i).get();

            Elements div = document.getElementsByClass("items-i");


            for (Element element1 : div) {

                PurchaseDto dto = new PurchaseDto();

                dto.setPrice(Integer.parseInt(element1.getElementsByClass("price-val").text()));
                dto.setLocation(element1.getElementsByClass("location").text());
                dto.setExtract(element1.getElementsByClass("bill_of_sale").text());
                dto.setRepair(element1.getElementsByClass("repair").tagName("span").text());
                try {
                    dto.setRooms(Integer.parseInt(element1.select("ul.name li").get(0).text()));
                } catch (IndexOutOfBoundsException exception) {
                    continue;
                }

                try {
                    dto.setSquareMeter(Integer.parseInt(element1.select("ul.name li").get(1).text()));
                } catch (IndexOutOfBoundsException exception) {
                    continue;
                }

                try {
                    dto.setCountOfFloor(Integer.parseInt(element1.select("ul.name li").get(2).text()));
                } catch (IndexOutOfBoundsException exception) {
                    continue;
                }

                dtoList.add(dto);
            }
        }
        return dtoList;
    }
}
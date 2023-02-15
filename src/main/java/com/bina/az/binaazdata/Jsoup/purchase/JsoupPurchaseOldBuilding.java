package com.bina.az.binaazdata.Jsoup.purchase;

import com.bina.az.binaazdata.dto.purchase.PurchaseOldBuildingDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsoupPurchaseOldBuilding {
    public List purchaseJsoupOldBuildingData() throws IOException {

        List<PurchaseOldBuildingDto> dtoList = new ArrayList<>();

        Document pageCount = Jsoup.connect("https://bina.az/alqi-satqi").get();

        Elements page = pageCount.getElementsByClass("page");

        int size = page.size();
        Element element = page.get(size - 1);
        String pageNumber = element.text();

        for (int i = 1; i <= Integer.parseInt(pageNumber); i++) {

            Document document = Jsoup.connect("https://bina.az/alqi-satqi?page=" + i).get();

            Elements div = document.getElementsByClass("items-i");


            for (Element element1 : div) {

                //In Item *** Category Testing

                Elements href = element1.getElementsByAttribute("href");
                String link = href.attr("abs:href");
                Document document1 = Jsoup.connect(link).get();

                Element categoryTest = document1.getElementsByTag("tr").first();
                String categoryStringTest = categoryTest.text().substring(11);

                if (categoryStringTest.equalsIgnoreCase("Köhnə tikili")) {

                    PurchaseOldBuildingDto dto = new PurchaseOldBuildingDto();


                    dto.setPrice(Integer.parseInt(element1.getElementsByClass("price-val").text()));
                    dto.setLocation(element1.getElementsByClass("location").text());
                    dto.setRepair(element1.getElementsByClass("repair").tagName("span").text());

                    try {
                        dto.setExtract(element1.getElementsByClass("bill_of_sale").text());
                    } catch (Exception e) {
                        dto.setExtract("No Extract");
                    }
                    try {
                        dto.setRooms(Integer.parseInt(element1.select("ul.name li").get(0).text()));
                    } catch (IndexOutOfBoundsException exception) {
                        dto.setRooms(Integer.parseInt("No Rooms"));
                    }

                    try {
                        dto.setArea(Integer.parseInt(element1.select("ul.name li").get(1).text()));
                    } catch (IndexOutOfBoundsException exception) {
                        dto.setArea(Integer.parseInt("No Area"));
                    }

                    try {
                        dto.setCountOfFloor(Integer.parseInt(element1.select("ul.name li").get(2).text()));
                    } catch (IndexOutOfBoundsException exception) {
                        dto.setCountOfFloor(Integer.parseInt("No Count Of Floor"));
                    }


                    try { //AnnouncementId
                        Elements announcementId = document1.select("div.item_info");
                        String announcementIdString = announcementId.tagName("p").text().split(" ")[2];
                        dto.setAnnouncementId(Integer.parseInt(announcementIdString));
                    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                        dto.setAnnouncementId(Integer.parseInt("No AnnouncementID"));
                    }

                    try {
                        Elements elements = document1.getElementsByTag("td"); //area
                        String area = elements.text();

                        Pattern pattern = Pattern.compile("(?<=Sahə)(.*\\n?)(?=m²)");
                        Matcher matcher=pattern.matcher(area);
                        if(matcher.find()){
                            dto.setArea(Integer.parseInt(matcher.group(1)));
                        }
                    }catch (Exception e){
                        dto.setArea(Integer.parseInt("No Area"));
                    }

                    try { //Category
                        Element category = document1.getElementsByTag("tr").first();
                        String categoryString = category.text().substring(11);
                        dto.setCategory(categoryString);

                    } catch (NullPointerException nullPointerException) {
                        dto.setCategory("No Category");
                    }

                    try { //Latitude
                        Elements latitude = document1.select("#item_map");
                        String latitudeString = latitude.attr("data-lat");
                        dto.setLatitude(latitudeString);
                    } catch (Exception e) {
                        dto.setLatitude("No Latitude");
                    }

                    try { //Lonitude
                        Elements longitude = document1.select("#item_map");
                        String longitudeString = longitude.attr("data-lng");
                        dto.setLongitude(longitudeString);
                    } catch (Exception e) {
                        dto.setLongitude("No Longitude");
                    }


                    dtoList.add(dto);
                }
                else {
                    continue;
                }
            }

        }
        return dtoList;
    }
}

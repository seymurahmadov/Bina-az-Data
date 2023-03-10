package com.bina.az.binaazdata.JsoupService.purchase;

import com.bina.az.binaazdata.dto.purchase.PurchaseOldBuildingDto;
import com.bina.az.binaazdata.entity.PurchaseOldBuildingEntity;
import com.bina.az.binaazdata.repository.PurchaseOldBuildingRepository;
import com.bina.az.binaazdata.util.PurchaseOldBuildingUtil;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class JsoupPurchaseOldBuilding {

    private final PurchaseOldBuildingRepository repository;


    public PurchaseOldBuildingDto purchaseJsoupOldBuildingData() throws IOException {

        PurchaseOldBuildingDto dto = new PurchaseOldBuildingDto();

        boolean notEndOfPage = true;
        int page = 1;

        int i = page;

        while (notEndOfPage) {


            Document pageTest = Jsoup.connect(" https://bina.az/alqi-satqi?page=" + i).get();
            Element elements = pageTest.getElementsByTag("p").first();

            if (elements.text().equals("Heç bir elan tapılmayıb")) {

                notEndOfPage = false;
                System.out.println("Bitti");

            } else {


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


                        dto.setPrice(element1.getElementsByClass("price-val").text());
                        dto.setLocation(element1.getElementsByClass("location").text());

                        //Date
                        Elements date = element1.getElementsByClass("city_when");

                        Pattern patternDate = Pattern.compile("(?<=, )(.*\\n?)(?= )");
                        Matcher matcherDate = patternDate.matcher(date.text());
                        if (matcherDate.find()) {
                            String group = matcherDate.group(1);
                            if (group.equals("bugün")){
                                LocalDate today = LocalDate.now();
                                dto.setDate(String.valueOf(today));
                            }else if (group.equals("dünən")){
                                LocalDate today = LocalDate.now();
                                dto.setDate(String.valueOf(today.minusDays(1)));
                            }else {
                                dto.setDate(group);
                            }
                        }

                        try {
                            String repairString = element1.getElementsByClass("repair").tagName("span").text();
                            dto.setRepair(repairString);
                            if (repairString.equals("")){
                                dto.setRepair("Təmirsiz");
                            }
                        }catch (Exception e){
                            dto.setRepair("Təmirsiz");
                        }

                        try {
                            dto.setExtract(element1.getElementsByClass("bill_of_sale").text());
                        } catch (Exception e) {
                            dto.setExtract("No Extract");
                        }
                        try {
                            dto.setRooms(element1.select("ul.name li").get(0).text());
                        } catch (IndexOutOfBoundsException exception) {
                            dto.setRooms("No Rooms");
                        }

                        try {
                            dto.setArea(element1.select("ul.name li").get(1).text());
                        } catch (IndexOutOfBoundsException exception) {
                            dto.setArea("No Area");
                        }

                        try {
                            dto.setCountOfFloor(element1.select("ul.name li").get(2).text());
                        } catch (IndexOutOfBoundsException exception) {
                            dto.setCountOfFloor("No Count Of Floor");
                        }


                        try { //AnnouncementId
                            Elements announcementId = document1.select("div.item_info");
                            String announcementIdString = announcementId.tagName("p").text().split(" ")[2];
                            dto.setAnnouncementId(Integer.parseInt(announcementIdString));
                        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                            dto.setAnnouncementId(Integer.parseInt("No AnnouncementID"));
                        }

                        try {
                            Elements elementsOfArea = document1.getElementsByTag("td"); //area
                            String area = elementsOfArea.text();

                            Pattern pattern = Pattern.compile("(?<=Sahə)(.*\\n?)(?=m²)");
                            Matcher matcher = pattern.matcher(area);
                            if (matcher.find()) {
                                dto.setArea(matcher.group(1));
                            }
                        } catch (Exception e) {
                            dto.setArea("No Area");
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


                    } else {
                        continue;
                    }

                    PurchaseOldBuildingUtil util = new PurchaseOldBuildingUtil();
                    PurchaseOldBuildingEntity oldBuildingEntity = util.oldBuildingUtil(dto);

                    repository.save(oldBuildingEntity);
                }

                i++;

                if (i == 2) {
                    break;

                }

            }

        }
        return dto;
    }
}

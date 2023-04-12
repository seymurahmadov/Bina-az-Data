package com.bina.az.binaazdata.JsoupService.purchase;

import com.bina.az.binaazdata.dto.purchase.PurchaseNewBuildingDto;
import com.bina.az.binaazdata.entity.PurchaseNewBuildingEntity;
import com.bina.az.binaazdata.repository.PurchaseNewBuildingRepository;
import com.bina.az.binaazdata.util.PurchaseNewBulildingUtil;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class JsoupPurchaseNewBuilding {

    private final PurchaseNewBuildingRepository newBuildingRepository;

    @Scheduled(fixedRate = 30000)
    public PurchaseNewBuildingDto purchaseJsoupNewBuildingData() throws IOException {

        PurchaseNewBuildingDto dto = new PurchaseNewBuildingDto();


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

                    if (categoryStringTest.equalsIgnoreCase("Yeni tikili")) {


                        //Price
                        String priceString = element1.getElementsByClass("price-val").text();
                        String s1 = priceString.replaceAll(" ", "");
                        dto.setPrice(Long.valueOf(s1));

                        //Location
                        dto.setLocation(element1.getElementsByClass("location").text());

                        //Date
                        Elements date = element1.getElementsByClass("city_when");

                        Pattern patternDate = Pattern.compile("(?<=, )(.*\\n?)(?= )");
                        Matcher matcherDate = patternDate.matcher(date.text());
                        if (matcherDate.find()) {
                            String group = matcherDate.group(1);
                            if (group.equals("bugün")){
                                LocalDate today = LocalDate.now();
                                Date dateToday = java.sql.Date.valueOf(today);
                                dto.setDate(dateToday);
                            }else if (group.equals("dünən")){
                                LocalDate  today = LocalDate.now();
                                Date dateYesterday = java.sql.Date.valueOf(today.minusDays(1));
                                dto.setDate(dateYesterday);
                            }else {
                                Date dateAnother = java.sql.Date.valueOf(group);
                                dto.setDate(dateAnother);
                            }
                        }


                        //Repair
                        try {
                            Elements repair = document1.getElementsByTag("td");
                            String repairString = repair.text();

                            Pattern pattern = Pattern.compile("(?<=Təmir\\s)(\\w+)");
                            Matcher matcher = pattern.matcher(repairString);
                            if (matcher.find()) {
                                String group = matcher.group(1);
                                dto.setRepair(group);
                            }

                            if (repairString.equals("") || repairString.equals(null)){
                                dto.setRepair("Təmirsiz");
                            }
                        } catch (Exception e) {
                            dto.setRepair("No Repair");
                        }


                        try { //Extract
                            Elements extract = document1.getElementsByTag("td");

                            String string = extract.text();

                            Pattern pattern = Pattern.compile("(?<=Çıxarış\\s)(\\w+)");
                            Matcher matcher = pattern.matcher(string);
                            if (matcher.find()) {
                                dto.setExtract(matcher.group(1));
                            }
                        } catch (Exception e) {
                            dto.setExtract("No Extract");
                        }

                        //Rooms
                        try {
                            String rooms = element1.select("ul.name li").get(0).text();
                            String roomsSubstring= rooms.substring(0,1);
                            dto.setRooms(roomsSubstring);
                        } catch (IndexOutOfBoundsException exception) {
                            dto.setRooms(("No Rooms"));
                        }

                        try {
                            String text = element1.select("ul.name li").get(2).text();
                            String[] s = text.split(" ");
                            String floor = s[0];
                            dto.setCountOfFloor(floor);
                        } catch (IndexOutOfBoundsException exception) {
                            dto.setCountOfFloor(("No Count Of Floor"));
                        }


                        try { //AnnouncementId
                            Elements announcementId = document1.select("div.item_info");
                            String announcementIdString = announcementId.tagName("p").text().split(" ")[2];
                            dto.setAnnouncementId(Integer.parseInt(announcementIdString));
                        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                            dto.setAnnouncementId(Integer.parseInt("No AnnouncementID"));
                        }

                        try { //area
                            Elements areaElement = document1.getElementsByTag("td"); //area
                            String area = areaElement.text();

                            Pattern pattern = Pattern.compile("(?<=Sahə)(.*\\n?)(?=m²)");
                            Matcher matcher = pattern.matcher(area);
                            if (matcher.find()) {
                              String areaString =matcher.group(1).trim();
                              Integer readyValue = Integer.valueOf(areaString);
                              dto.setArea(readyValue);
                            }

                        } catch (Exception e) {
                            dto.setArea((0));
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

                        try { //Longtitude
                            Elements longitude = document1.select("#item_map");
                            String longitudeString = longitude.attr("data-lng");
                            dto.setLongitude(longitudeString);
                        } catch (Exception e) {
                            dto.setLongitude("No Longitude");
                        }


                    } else {
                        continue;
                    }

                    PurchaseNewBulildingUtil util = new PurchaseNewBulildingUtil();
                    PurchaseNewBuildingEntity purchaseNewBuildingEntity = util.newBuilding(dto);

                    PurchaseNewBuildingEntity byAnnouncementId =
                            newBuildingRepository.findByAnnouncementId(purchaseNewBuildingEntity.getAnnouncementId());

                    if (byAnnouncementId==null){
                        newBuildingRepository.save(purchaseNewBuildingEntity);
                    }


                }
                i++;

                if (i==2){
                    break;
                }
            }
        }

       return dto;

    }
}
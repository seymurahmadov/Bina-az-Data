package com.bina.az.binaazdata.JsoupService.purchase;

import com.bina.az.binaazdata.dto.purchase.PurchaseGardenHouseDto;
import com.bina.az.binaazdata.entity.PurchaseGardenHouseEntity;
import com.bina.az.binaazdata.repository.PurchaseGardenHouseRepository;
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
public class JsoupPurchaseGardenHouse {

        private final    PurchaseGardenHouseRepository repository;


    public PurchaseGardenHouseDto purchaseJsoupGardenHouseData() throws IOException {
        PurchaseGardenHouseDto dto = new PurchaseGardenHouseDto();

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

                    if (categoryStringTest.equalsIgnoreCase("Bağ")) {


                        dto.setPrice(element1.getElementsByClass("price-val").text());
                        dto.setLocation(element1.getElementsByClass("location").text());

                        try {
                            String repairString = element1.getElementsByClass("repair").tagName("span").text();
                            dto.setRepair(repairString);

                            if (repairString.equals("")){
                                dto.setRepair("Təmirsiz");
                            }
                        }catch (Exception e) {
                            dto.setRepair("Təmirsiz");
                        }

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
                            String billOfSale = element1.getElementsByClass("bill_of_sale").text();
                            dto.setExtract(billOfSale);

                            if (billOfSale.equals("")){
                                dto.setExtract("Çıxarış yoxdur");
                            }
                        } catch (Exception e) {
                            dto.setExtract("Çıxarış yoxdur");
                        }


                        try {
                            dto.setRooms(element1.select("ul.name li").get(0).text());
                        } catch (IndexOutOfBoundsException exception) {
                            dto.setRooms("No Rooms");
                        }


                        try { //AnnouncementId
                            Elements announcementId = document1.select("div.item_info");
                            String announcementIdString = announcementId.tagName("p").text().split(" ")[2];
                            dto.setAnnouncementId(Integer.parseInt(announcementIdString));
                        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                            dto.setAnnouncementId(Integer.parseInt("No AnnouncementID"));
                        }

                        try { //HomeArea
                            Elements elementofHomeArea = document1.getElementsByTag("td"); //area
                            String area = elementofHomeArea.text();

                            Pattern pattern = Pattern.compile("(?<=Sahə)(.*\\n?)(?=m²)");
                            Matcher matcher = pattern.matcher(area);
                            if (matcher.find()) {
                                String group = matcher.group(1);
                                dto.setHomeArea(group);
                            }

                        } catch (Exception e) {
                            dto.setHomeArea("No Area");
                        }

                        try { //LandArea
                            Elements elementofLandArea = document1.getElementsByTag("td"); //area
                            String area = elementofLandArea.text();

                            Pattern pattern = Pattern.compile("(?<=sahəsi)(.*\\n?)(?=sot)");
                            Matcher matcher = pattern.matcher(area);
                            if (matcher.find()) {
                                String group = matcher.group(1);
                                dto.setLandArea(group);
                            }

                        } catch (Exception e) {
                            dto.setLandArea("No Area");
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

                    PurchaseGardenHouseEntity gardenHouseEntity = PurchaseGardenHouseEntity.builder()
                            .id(dto.getId())
                            .announcementId(dto.getAnnouncementId())
                            .price(dto.getPrice())
                            .location(dto.getLocation())
                            .extract(dto.getExtract())
                            .repair(dto.getRepair())
                            .rooms(dto.getRooms())
                            .homeArea(dto.getHomeArea())
                            .landArea(dto.getLandArea())
                            .category(dto.getCategory())
                            .longitude(dto.getLongitude())
                            .latitude(dto.getLatitude())
                            .date(dto.getDate())
                            .build();

                    repository.save(gardenHouseEntity);
                }
                i++;
                if (i == 4) {
                    break;
                }

            }
        }
        return dto;

    }
}

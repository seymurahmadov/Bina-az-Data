package com.bina.az.binaazdata.JsoupService.purchase;

import com.bina.az.binaazdata.dto.purchase.PurchaseOfficeDto;
import com.bina.az.binaazdata.entity.PurchaseOfficeEntity;
import com.bina.az.binaazdata.repository.PurchaseOfficeRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class JsoupPurchaseOffice {

    private final PurchaseOfficeRepository repository;

    public PurchaseOfficeDto purchaseJsoupOfficeData() throws IOException {

        PurchaseOfficeDto dto = new PurchaseOfficeDto();


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

                    if (categoryStringTest.equalsIgnoreCase("Ofis")) {


                        dto.setPrice(element1.getElementsByClass("price-val").text());
                        dto.setLocation(element1.getElementsByClass("location").text());
                        dto.setRepair(element1.getElementsByClass("repair").tagName("span").text());

                        try {
                            dto.setExtract(element1.getElementsByClass("bill_of_sale").text());
                        } catch (Exception e) {
                            dto.setExtract("No Extract");
                        }
                        try {
                            String rooms = element1.select("ul.name li").get(0).text();
                            String roomsSubstring= rooms.substring(0,1);
                            dto.setRooms(roomsSubstring);
                        } catch (IndexOutOfBoundsException exception) {
                            dto.setRooms("No Rooms");
                        }

                        try {
                            dto.setArea(element1.select("ul.name li").get(1).text());
                        } catch (IndexOutOfBoundsException exception) {
                            dto.setArea("No Area");
                        }

                        try { //AnnouncementId
                            Elements announcementId = document1.select("div.item_info");
                            String announcementIdString = announcementId.tagName("p").text().split(" ")[2];
                            dto.setAnnouncementId(Integer.parseInt(announcementIdString));
                        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                            dto.setAnnouncementId(Integer.parseInt("No AnnouncementID"));
                        }

                        try {
                            Elements elementOfTypeOfBuilding = document1.getElementsByTag("td"); //area
                            String area = elementOfTypeOfBuilding.text();

                            Pattern pattern = Pattern.compile("(?<=növü)(.*\\n?)(?=Otaq)");
                            Matcher matcher = pattern.matcher(area);
                            if (matcher.find()) {
                                String typeOfBuilding = matcher.group(1);
                                dto.setTypeOfBuilding(typeOfBuilding);
                            }

                        } catch (Exception e) {
                            dto.setTypeOfBuilding("No Type");
                        }

                        try {
                            Elements elementOfArea = document1.getElementsByTag("td"); //area
                            String area = elementOfArea.text();

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

                    PurchaseOfficeEntity officeEntity = PurchaseOfficeEntity.builder()
                            .id(dto.getId())
                            .announcementId(dto.getAnnouncementId())
                            .price(dto.getPrice())
                            .location(dto.getLocation())
                            .extract(dto.getExtract())
                            .repair(dto.getRepair())
                            .rooms(dto.getRooms())
                            .typeOfBuilding(dto.getTypeOfBuilding())
                            .area(dto.getArea())
                            .category(dto.getCategory())
                            .longitude(dto.getLongitude())
                            .latitude(dto.getLatitude())
                            .date(dto.getDate())
                            .build();

                    repository.save(officeEntity);

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

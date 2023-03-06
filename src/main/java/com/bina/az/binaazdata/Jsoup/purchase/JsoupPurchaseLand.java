package com.bina.az.binaazdata.Jsoup.purchase;

import com.bina.az.binaazdata.dto.purchase.PurchaseHomeVillaDto;
import com.bina.az.binaazdata.dto.purchase.PurchaseLandDto;
import com.bina.az.binaazdata.entity.PurchaseLandEntity;
import com.bina.az.binaazdata.repository.PurchaseLandRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class JsoupPurchaseLand {

    private final PurchaseLandRepository repository;
    public PurchaseLandDto purchaseJsoupLandData() throws IOException {

        PurchaseLandDto dto = new PurchaseLandDto();


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

                    if (categoryStringTest.equalsIgnoreCase("Torpaq")) {


                        dto.setPrice(element1.getElementsByClass("price-val").text());
                        dto.setLocation(element1.getElementsByClass("location").text());

                        try {
                            dto.setExtract(element1.getElementsByClass("bill_of_sale").text());
                        } catch (Exception e) {
                            dto.setExtract("No Extract");
                        }


                        try { //AnnouncementId
                            Elements announcementId = document1.select("div.item_info");
                            String announcementIdString = announcementId.tagName("p").text().split(" ")[2];
                            dto.setAnnouncementId(Integer.parseInt(announcementIdString));
                        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                            dto.setAnnouncementId(Integer.parseInt("No AnnouncementID"));
                        }


                        try { //LandArea
                            Elements elementofArea = document1.getElementsByTag("td"); //area
                            String area = elementofArea.text();

                            Pattern pattern = Pattern.compile("(?<=Sahə)(.*\\n?)(?=sot)");
                            Matcher matcher = pattern.matcher(area);
                            if (matcher.find()) {
                                String landArea = matcher.group(1);
                                dto.setLandArea(landArea);
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

                    PurchaseLandEntity landEntity = PurchaseLandEntity.builder()
                            .id(dto.getId())
                            .announcementId(dto.getAnnouncementId())
                            .price(dto.getPrice())
                            .location(dto.getLocation())
                            .extract(dto.getExtract())
                            .landArea(dto.getLandArea())
                            .category(dto.getCategory())
                            .latitude(dto.getLatitude())
                            .longitude(dto.getLongitude())
                            .build();

                    repository.save(landEntity);
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

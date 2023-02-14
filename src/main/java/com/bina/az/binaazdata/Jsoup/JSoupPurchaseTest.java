package com.bina.az.binaazdata.Jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JSoupPurchaseTest {
    public static void main(String[] args) throws IOException {
        Document pageCount = Jsoup.connect("https://bina.az/alqi-satqi").get();

        Elements page = pageCount.getElementsByClass("page");

        int size = page.size();
        Element element = page.get(size - 1);
        String pageNumber = element.text();

        for (int i = 1; i <= Integer.parseInt(pageNumber); i++) {

            Document document = Jsoup.connect("https://bina.az/alqi-satqi?page=" + i).get();


            Elements div = document.getElementsByClass("items-i");


            for (Element element1 : div) {


                Elements price = element1.getElementsByClass("price-val");
                Elements location = element1.getElementsByClass("location");
                Elements extract = element1.getElementsByClass("bill_of_sale");
                Elements repair = element1.getElementsByClass("repair");
                repair.tagName("span");
                Element rooms = element1.select("ul.name li").get(0);




                Elements href = element1.getElementsByAttribute("href");
                String link = href.attr("abs:href");
                Document document1 = Jsoup.connect(link).get();






//////
//                Elements latitude = document1.select("#item_map");
//                String latitudeString=   latitude.attr("data-lat");
//                System.out.println(latitudeString);
//
//                Elements longitude = document1.select("#item_map");
//                String longitudeString=   longitude.attr("data-lng");
//                System.out.println(longitudeString);







//                try{
//                    Elements announcementId = document1.select("div.item_info"); // sehv var birin gosterir birin gostermir !!!
//                    String announcementIdString = announcementId.tagName("p").text().split(" ")[2];
//                    System.out.println(announcementIdString);
//                }catch (ArrayIndexOutOfBoundsException exception){
//                    System.out.println("Reklam elandir. Id yoxdur");
//                }
//
//                Element category = document1.getElementsByTag("tr").first();
//
//                String categoryString = category.text().substring(11);
//                System.out.println(categoryString);


//                Element element2 = element1.select("ul.name li").get(1);
//                System.out.println(element2.text());

//                        try {
//                        Element squareMeter = element1.select("ul.name li").get(1);
//                        Element countOfFloor = element1.select("ul.name li").get(2);
//
//
//

                Elements elements = document1.getElementsByTag("td"); //area
                String area = elements.text();

                System.out.println(area);

//
//
//
//
//
//
//
//                    System.out.println(price.text() + " AZN " + "****" + location.text() + "****" + extract.text() +
//                            "****" + repair.text() + "**** " + rooms.text() + "****" + squareMeter.text() + "****"
//                            + countOfFloor.text() + "****" + categoryString + "****" + announcementIdString);
//
//                    } catch (Exception   e) {
//                    System.out.println(price.text() + " AZN " + "****" + location.text() + "****" + extract.text() +
//                            "****" + repair.text() + "**** " + rooms.text() +  "****" + announcementIdString);
//                    }
////
////
            }


            }
        }
    }

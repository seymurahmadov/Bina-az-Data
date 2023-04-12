package com.bina.az.binaazdata.JsoupService.Test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSoupPurchaseTest {


    public static void main(String[] args) throws IOException {
        Document pageCount = Jsoup.connect("https://bina.az/alqi-satqi").get();
//        https://bina.az/alqi-satqi
        Elements page = pageCount.getElementsByClass("page");

        int size = page.size();
        Element element = page.get(size - 1);
        String pageNumber = element.text();

        for (int i = 1; i <= Integer.parseInt(pageNumber); i++) {

            Document document = Jsoup.connect(" https://bina.az/alqi-satqi?page=" + i).get();
//            https://bina.az/alqi-satqi?page=

//            Elements div = document.select("div.items_list div.items-i").not(".vipped");
          Elements div = document.getElementsByClass("items-i");




            for (Element element1 : div) {


                Elements date = element1.getElementsByClass("city_when");

                Pattern patternDate = Pattern.compile("(?<=, )(.*\\n?)(?= )");
                Matcher matcherDate = patternDate.matcher(date.text());
                if (matcherDate.find()) {
                    String group = matcherDate.group(1);
                    if (group.equals("bugün")){
                        LocalDate today = LocalDate.now();
                        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                        String format = today.format(myFormatObj);
                        Date dateToday = java.sql.Date.valueOf(String.valueOf(today));
                        System.out.println(dateToday);

                    }else if (group.equals("dünən")){
                        LocalDate  today = LocalDate.now();
                        Date dateYesterday = java.sql.Date.valueOf(today.minusDays(1));
                        System.out.println(dateYesterday);
                    }else {
                        Date dateAnother = java.sql.Date.valueOf(group);
                        System.out.println(dateAnother);
                    }
                }




//            for (Element element1 : div) {
//
//
//                Elements date = element1.getElementsByClass("city_when");
//
//                Pattern patternDate = Pattern.compile("(?<=, )(.*\\n?)(?= )");
//                Matcher matcherDate = patternDate.matcher(date.text());
//                if (matcherDate.find()) {
//                    String group = matcherDate.group(1);
//                    if (group.equals("bugün")){
//                        LocalDateTime today = LocalDateTime.now();
//                        Date dateToday = java.sql.Date.valueOf(String.valueOf(today));
//                        System.out.println(dateToday);
//                    }else if (group.equals("dünən")){
//                        LocalDate  today = LocalDate.now();
//                        Date dateYesterday = java.sql.Date.valueOf(today.minusDays(1));
//                        System.out.println(dateYesterday);
//                    }else {
//                        Date dateAnother = java.sql.Date.valueOf(group);
//                        System.out.println(dateAnother);
//                    }
//                }


//
//                Elements areaElement = document1.getElementsByTag("td"); //area
//                String area = areaElement.text();
//
//                Pattern pattern = Pattern.compile("(?<=Sahə)(.*\\n?)(?=m²)");
//                Matcher matcher = pattern.matcher(area);
//                String areaString=" ";
//                if (matcher.find()) {
//                    areaString =matcher.group(1);
//                }


//                Elements date = element1.getElementsByClass("city_when");
////                System.out.println(date.text());
//
//                Pattern pattern = Pattern.compile("(?<=, )(.*\\n?)(?= )");
//                Matcher matcher = pattern.matcher(date.text());
//                if (matcher.find()) {
//                    String group = matcher.group(1);
//                    if (group.equals("bugün")){
//
//                        LocalDate today = LocalDate.now();
//                        System.out.println(today);
//                    }else if (group.equals("dünən")){
//                        LocalDate today = LocalDate.now();
//                        System.out.println(today.minusDays(1));
//                    }else {
//                        System.out.println(group);
//                    }
//                }

//
//            String count = element1.select("ul.name li").get(2).text();
//
//                String[] s = count.split(" ");
//                String floor = s[0];
//                System.out.println(floor);
//
//
//
//
//                Elements price = element1.getElementsByClass("price-val");
//                Elements location = element1.getElementsByClass("location");
//                Elements extract = element1.getElementsByClass("bill_of_sale");
//
//                System.out.println(price.text() + " " + location.text() + " " + extract.text()) ;
////                Elements repair = element1.getElementsByClass("repair");
////                repair.tagName("span");
//                Element rooms = element1.select("ul.name li").get(0);
//                String roomsSubstring= rooms.text().substring(0,1);
//                System.out.println(roomsSubstring);
//
//
//                Elements href = element1.getElementsByAttribute("href");
//                String link = href.attr("abs:href");
//                Document document1 = Jsoup.connect(link).get();





//
//                Element category = document1.getElementsByTag("tr").first();
//                String categoryString = category.text().substring(11);
//                System.out.println(categoryString);

//
//                Elements elementofLandArea = document1.getElementsByTag("td"); //area
//                String area = elementofLandArea.text();
//                System.out.println(area);





//                Elements extract = document1.getElementsByTag("td");
//
//                String string= extract.text();
//
//                Pattern pattern = Pattern.compile("(?<=Çıxarış\\s)(\\w+)");
//                Matcher matcher = pattern.matcher(string);
//                if (matcher.find()){
//                    System.out.println(matcher.group(1));
//                }


//                Elements repair = document1.getElementsByTag("td");
//                String repairString = repair.text();
//
//                Pattern pattern = Pattern.compile("(?<=Təmir\\s)(\\w+)");
//                Matcher matcher = pattern.matcher(repairString);
//                if (matcher.find()){
//                    System.out.println(matcher.group(1));
//                }

//
//                Elements elementofHomeArea = document1.getElementsByTag("td"); //area
//                String area1 = elementofHomeArea.text();
//                System.out.println(area1);
//
//                Pattern pattern = Pattern.compile("(?<=sahəsi)(.*\\n?)(?=sot)");
//                Matcher matcher = pattern.matcher(area1);
//                if (matcher.find()) {
//                    String group = matcher.group(1);
//                    System.out.println(group);
//                }

















































//                Pattern pattern = Pattern.compile("\\\\bvar\\\\b");
//                Matcher matcher = pattern.matcher(allWord);
//                if (matcher.find()){
//                    String group= matcher.group(1);
//                    System.out.println(group);
//                }




//                Elements elements = document1.getElementsByTag("td"); //area
//                String area = elements.text();
////                System.out.println(area);
//
//
//                Pattern pattern = Pattern.compile("(?<=Villa)(.*\\n?)(?=Otaq)");
//                Matcher matcher = pattern.matcher(area);
//                if (matcher.find()) {
//                    String group = matcher.group(1);
//                    System.out.println(group);


//                Elements extractNew = document1.getElementsByTag("tr");
////                System.out.println(extractNew.text());
//
//                String extraxtString = extractNew.text();
//                Pattern pattern = Pattern.compile("(?<=Sahə)(.*\\n?)(?=m²)")


//                Elements elements = document1.getElementsByTag("td"); //area
//                String area = elements.text();
//
//                Pattern pattern = Pattern.compile("(?<=Sahə)(.*\\n?)(?=m²)");
//                Matcher matcher=pattern.matcher(area);
//                if(matcher.find()){
//                    dto.setArea((matcher.group(1)));
//                }

//
                /*Kateqoriya Qaraj Sahə 15.6 m² Çıxarış yoxdur
Kateqoriya Torpaq Sahə 42 sot Çıxarış var
Kateqoriya Yeni tikili Mərtəbə 2 / 7 Sahə 65 m² Otaq sayı 3 Çıxarış yoxdur */


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


//                Element element2 = element1.select("ul.name li").get(1);
//                System.out.println(element2.text());

//                        try {
//                        Element squareMeter = element1.select("ul.name li").get(1);
//                        Element countOfFloor = element1.select("ul.name li").get(2);
//
//


                    // Kateqoriya Ofis Sahə 560 m² Binanın növü Ev / Mənzil Otaq sayı 8 Çıxarış var İpoteka var Təmir var

//                Elements elements = document1.getElementsByTag("td"); //area
//                String area = elements.text();
//                System.out.println(area);


//
//                    Pattern pattern= Pattern.compile("(?<=Sahə)(.*\\n?)(?=m²)");
//                    Matcher matcher= pattern.matcher(area);
//                    if (matcher.find()){
//                        System.out.println(matcher.group(1));
//                    }


//                System.out.println(elements.text());

//                Pattern pattern = Pattern.compile("(?<=Sahə)(.*\\n?)(?=m²)");
//                Matcher matcher=pattern.matcher(area);
//                if(matcher.find()){
//                    System.out.println(matcher.group(1));
//                }


                    // Sahə 600 m² Torpaq sahəsi 11 sot

//                Pattern pattern = Pattern.compile("(?<=Villa)(.*\\n?)(?=Otaq)");
//                Matcher matcher = pattern.matcher(area);
//                if (matcher.find()){
//                    String group = matcher.group(1);
////                    System.out.println(group);
//                    String[] sentence = group.split(" ");
//                    String homeArea = sentence[2];
//                    String landArea = sentence[6];
//                    System.out.println("Kv " +  homeArea + "Tor: " +  landArea);
//                }
//


//
//       ****         Element category = document1.getElementsByTag("tr").first();
//                String categoryString = category.text().substring(11);
//                System.out.println(categoryString);
//
//                if (category.equals("Ev / Villa")){
//
//                }


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

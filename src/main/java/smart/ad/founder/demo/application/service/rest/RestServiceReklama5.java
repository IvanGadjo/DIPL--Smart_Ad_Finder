package smart.ad.founder.demo.application.service.rest;


import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import smart.ad.founder.demo.domain.model.FactoryClass;
import smart.ad.founder.demo.domain.model.entities.FoundAdvert;
import smart.ad.founder.demo.domain.model.entities.UserInterest;
import smart.ad.founder.demo.domain.model.valueObjects.Keywords;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestServiceReklama5 {

    private FactoryClass factory;

    public RestServiceReklama5(FactoryClass factory){
        this.factory = factory;
    }


    public List<FoundAdvert> getAdsUrls_reklama5(UserInterest userInterest) throws IOException {

        String url = constructReklama5Url(userInterest);

        Document doc = Jsoup.connect(url).get();


        Elements adLinks = doc.getElementsByClass("SearchAdTitle");

        List<FoundAdvert> foundAds = new ArrayList<>();



        adLinks.forEach(el -> {
            // System.out.println(el.toString());
            String secondPart = el.toString().substring(9,30);
            String adTitle = el.text();
            String adPrice = el.parent().nextElementSibling().nextElementSibling().nextElementSibling().child(0).child(0).text();
            String adImgUrl = el.parent().parent().parent().child(0).child(0).child(0).attr("style");

            adImgUrl = adImgUrl.split("\\(")[1].substring(1);
            adImgUrl = adImgUrl.substring(1, adImgUrl.length()-1);      // ! Mozebi ke treba https:// pred ova

//            System.out.println(secondPart);
//            System.out.println(adTitle);
//            System.out.println(adPrice);
//            System.out.println(adImgUrl);
//            System.out.println("%%%%%%%%%%%%%%%%%%%");

//            foundAdvertsUrls.add("https://reklama5.mk" + secondPart);       // go vadi linkot

            FoundAdvert newFoundAd = factory.createNewFoundAdvert("https://reklama5.mk" + secondPart, false,
                    adImgUrl, adTitle, adPrice);
            newFoundAd.setUserInterest(userInterest);
            foundAds.add(newFoundAd);
        });

        return foundAds;
    }




    public String constructReklama5Url(UserInterest userInterest){

        Keywords keywords = userInterest.getKeywords();
        String category = userInterest.getCategory();
        String region = userInterest.getRegion();

        // Part 1 - keywords
        String firstPart = "https://reklama5.mk/Search?q="+keywords.getMainKeyword();
        String otherParams = "&sell=0&sell=1&buy=0&buy=1&trade=0&trade=1&includeOld=0&includeOld=1&includeNew=0&includeNew=1&private=0&company=0&page=1&SortByPrice=0&zz=1";

        String otherKeywordsCombined = keywords.getOtherKeywords().stream().reduce("", (partialString, element) -> partialString + "+" + element);

        firstPart = firstPart.concat(otherKeywordsCombined);

        // Part 2 - region
        firstPart = firstPart.concat("&city=");
        firstPart = firstPart.concat(getRegionNumberForUrl(region));

        // Part 3 - other params
        firstPart = firstPart.concat(otherParams);

        // Part 3 - category
        firstPart = firstPart.concat("&cat=");
        firstPart = firstPart.concat(getCategoryNumberForUrl(category));



        return firstPart;
    }


    public String getCategoryNumberForUrl(String category){
        if(category.equals("Avtomobili")) return "24";
        if(category.equals("Stanovi")) return "159";
        if(category.equals("Kukji/Vili")) return "158";
        if(category.equals("Mobilni telefoni")) return "559";
        if(category.equals("Desktop kompjuteri")) return "581";
        if(category.equals("Laptop kompjuteri")) return "582";
        return "0";
    }

    public String getRegionNumberForUrl(String region){

        if(region.equals("Skopje")) return "1";
        if(region.equals("Bitola")) return "2";
        if(region.equals("Kumanovo")) return "3";
        if(region.equals("Prilep")) return "4";
        if(region.equals("Tetovo")) return "5";
        if(region.equals("Veles")) return "6";
        if(region.equals("Stip")) return "7";
        if(region.equals("Ohrid")) return "8";
        if(region.equals("Gostivar")) return "9";
        if(region.equals("Strumica")) return "10";

        if(region.equals("Kavadarci")) return "11";
        if(region.equals("Kocani")) return "12";
        if(region.equals("Kicevo")) return "13";
        if(region.equals("Struga")) return "14";
        if(region.equals("Radovis")) return "15";
        if(region.equals("Gevgelija")) return "16";
        if(region.equals("Debar")) return "17";
        if(region.equals("Kriva Palanka")) return "18";
        if(region.equals("Sveti Nikole")) return "19";
        if(region.equals("Negotino")) return "20";

        if(region.equals("Delcevo")) return "21";
        if(region.equals("Vinica")) return "22";
        if(region.equals("Resen")) return "23";
        if(region.equals("Probistip")) return "24";
        if(region.equals("Berovo")) return "25";
        if(region.equals("Kratovo")) return "26";
        if(region.equals("Krusevo")) return "28";
        if(region.equals("Makedonski Brod")) return "29";
        if(region.equals("Valandovo")) return "30";
        if(region.equals("Demir Hisar")) return "34";

        return "0";
    }

}

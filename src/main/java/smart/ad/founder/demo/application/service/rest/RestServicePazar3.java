package smart.ad.founder.demo.application.service.rest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import smart.ad.founder.demo.domain.model.FactoryClass;
import smart.ad.founder.demo.domain.model.entities.FoundAdvert;
import smart.ad.founder.demo.domain.model.entities.UserInterest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestServicePazar3 {

    private FactoryClass factory;



    public RestServicePazar3(FactoryClass factory) {
        this.factory = factory;
    }


    public List<FoundAdvert> getAdsUrls_pazar3(UserInterest userInterest) throws IOException {

        String url = constructPazar3Url(userInterest);

        System.out.println(url);

        Document doc = Jsoup.connect(url).get();

        Elements adLinks =  doc.getElementsByClass("Link_vis");

        List<FoundAdvert> foundAds = new ArrayList<>();

        adLinks.forEach(el -> {
            // System.out.println(el.toString());

            String adUrl = "";
            if(el.parent().toString().startsWith("<h2>"))
                adUrl = el.attr("href");

            // System.out.println(el.parent());
            String adTitle = el.attr("title");
            String adPrice = el.parent().nextElementSibling().text();


            if(!adTitle.equals("")){        // * Kako posleden element se fakja nekoj koj nema title - i potoa pagja na vadenje adImgUrl zatoa vaka mora

                String adImgUrl = el.parent().parent().parent().child(0).child(0).child(0).child(0).attr("data-src");

//                System.out.println(adUrl);
//                System.out.println(adTitle);
//                System.out.println(adPrice);
//                System.out.println(adImgUrl);
//                System.out.println("%%%%%%%%%%%%%%%%%%%");

                // * Ako nema pronajdeni rezultati so main keywordot vo niv - ne gi sejvnuva
                String capitalizedMain = userInterest.getKeywords().getMainKeyword().substring(0,1).toUpperCase() +
                        userInterest.getKeywords().getMainKeyword().substring(1);

                if(!adUrl.equals("") && (adUrl.contains(userInterest.getKeywords().getMainKeyword())
                        || adUrl.contains(capitalizedMain))){

                    FoundAdvert newFoundAd = factory.createNewFoundAdvert("https://www.pazar3.mk" + adUrl, false,
                            adImgUrl, adTitle, adPrice);
                    newFoundAd.setUserInterest(userInterest);

                    foundAds.add(newFoundAd);


                }

            }

        });

        return foundAds;
    }


    public String constructPazar3Url(UserInterest userInterest){

        String baseUrl = "https://www.pazar3.mk/oglasi";

        // Part 1 - category
        baseUrl = baseUrl.concat(getCategoryStringForUrl(userInterest.getCategory()));

        // Part 2 - region
        baseUrl = baseUrl.concat(getRegionStringForUrl(userInterest.getRegion()));

        // Part 3 - keywords
        baseUrl = baseUrl.concat("/q-" + userInterest.getKeywords().getMainKeyword());

        String otherKeywordsCombined = userInterest.getKeywords().getOtherKeywords()
                .stream().reduce("", (partialString, element) -> {
                    return partialString + "-" + element;
                });

        baseUrl = baseUrl.concat(otherKeywordsCombined);


        return baseUrl;
    }



    public String getCategoryStringForUrl(String category){
        if(category.equals("Avtomobili")) return "/vozila/avtomobili/se-prodava";
        if(category.equals("Stanovi")) return "/zivealista/stanovi";
        if(category.equals("Kukji/Vili")) return "/zivealista/kukji-vili";
        if(category.equals("Mobilni telefoni")) return "/elektronika/mobilni-telefoni/se-prodava";
        if(category.equals("Desktop kompjuteri")) return "/elektronika/kompjuteri-komplet/desktop-kompjuteri/se-prodava";
        if(category.equals("Laptop kompjuteri")) return "/elektronika/kompjuteri-komplet/laptopi-notebook/se-prodava";
        return "";
    }

    public String getRegionStringForUrl(String region){

        if(region.equals("Skopje")) return "/skopje";
        if(region.equals("Bitola")) return "/bitola";
        if(region.equals("Kumanovo")) return "/kumanovo";
        if(region.equals("Prilep")) return "/prilep";
        if(region.equals("Tetovo")) return "/tetovo";
        if(region.equals("Veles")) return "/veles";
        if(region.equals("Stip")) return "/stip";
        if(region.equals("Ohrid")) return "/ohrid";
        if(region.equals("Gostivar")) return "/gostivar";
        if(region.equals("Strumica")) return "/strumica";

        if(region.equals("Kavadarci")) return "/kavadarci";
        if(region.equals("Kocani")) return "/kocani";
        if(region.equals("Kicevo")) return "/kicevo";
        if(region.equals("Struga")) return "/struga";
        if(region.equals("Radovis")) return "/radovis";
        if(region.equals("Gevgelija")) return "/gevgelija";
        if(region.equals("Debar")) return "/debar";
        if(region.equals("Kriva Palanka")) return "/kriva-palanka";
        if(region.equals("Sveti Nikole")) return "/sveti-nikole";
        if(region.equals("Negotino")) return "/negotino";

        if(region.equals("Delcevo")) return "/delcevo";
        if(region.equals("Vinica")) return "/vinica";
        if(region.equals("Resen")) return "/resen";
        if(region.equals("Probistip")) return "/probistip";
        if(region.equals("Berovo")) return "/berovo";
        if(region.equals("Kratovo")) return "/kratovo";
        if(region.equals("Krusevo")) return "/krusevo";
        if(region.equals("Makedonski Brod")) return "/makedonski-brod";
        if(region.equals("Valandovo")) return "/valandovo";
        if(region.equals("Demir Hisar")) return "/demir-hisar";

        return "";
    }



}





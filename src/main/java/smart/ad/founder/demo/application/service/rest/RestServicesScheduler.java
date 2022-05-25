package smart.ad.founder.demo.application.service.rest;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import smart.ad.founder.demo.application.service.FoundAdvertService;
import smart.ad.founder.demo.application.service.UserInterestsService;
import smart.ad.founder.demo.domain.model.entities.FoundAdvert;
import smart.ad.founder.demo.domain.model.entities.UserInterest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestServicesScheduler {

    private RestServicePazar3 restServicePazar3;
    private RestServiceReklama5 restServiceReklama5;
    private UserInterestsService userInterestsService;
    private FoundAdvertService foundAdvertService;

    public RestServicesScheduler(RestServicePazar3 restServicePazar3, RestServiceReklama5 restServiceReklama5,
                                 UserInterestsService userInterestsService,
                                 FoundAdvertService foundAdvertService) {
        this.restServicePazar3 = restServicePazar3;
        this.restServiceReklama5 = restServiceReklama5;
        this.userInterestsService = userInterestsService;
        this.foundAdvertService = foundAdvertService;
    }

    @Scheduled(fixedDelay = 30000)       // 30 sec
    public void callRestServiceMethods() {

        System.out.println("----------------- Search for Ads 30 sec ---------------");

        List<UserInterest> userInterests = userInterestsService.findAllUserInterests().stream().filter(ui -> {          // * Search only for active user interests
            return ui.isActive();
        }).collect(Collectors.toList());

        userInterests.forEach(ui -> {
            try {
                List<FoundAdvert> foundAdverts = restServicePazar3.getAdsUrls_pazar3(ui);       // * Finds ads from pazar3
                foundAdverts.addAll(restServiceReklama5.getAdsUrls_reklama5(ui));           // * Finds ads from reklama5

                foundAdverts.forEach(fa -> {
                    List<FoundAdvert> alreadyFoundAds = foundAdvertService.findAllFoundAdverts();
                    List<String> alreadyFoundAdUrls = alreadyFoundAds.stream()         // * Get all ad urls from DB
                            .map(foundAdvFromDb -> foundAdvFromDb.getUrl()).collect(Collectors.toList());

                    if(!alreadyFoundAdUrls.contains(fa.getUrl())) {               // * If the new ad url is not found in already present ad urls - add it

                        checkForDuplicateAds(fa, alreadyFoundAds);

                        foundAdvertService.createNewFoundAdvert(fa, fa.getUserInterest().getId());
                    }
                });

                System.out.println("----- Found Ads Added");

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void checkForDuplicateAds(FoundAdvert newFoundAd, List<FoundAdvert> alreadyFoundAds) {
//        alreadyFoundAds.stream().forEach(alreadyFoundAd -> {
//            if(alreadyFoundAd.getPrice().equals(newFoundAd.getPrice()) && alreadyFoundAd.getTitle().equals(newFoundAd.getTitle()))
//
//        });
    }
}

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

        List<UserInterest> userInterests = userInterestsService.findAllUserInterests().stream().filter(ui -> {
            return ui.isActive();
        }).collect(Collectors.toList());

        userInterests.forEach(ui -> {
            try {
                List<FoundAdvert> foundAdverts = restServicePazar3.getAdsUrls_pazar3(ui);
                foundAdverts.addAll(restServiceReklama5.getAdsUrls_reklama5(ui));

                foundAdverts.forEach(fa -> {
                    List<String> alreadyFoundAdUrls = foundAdvertService.findAllFoundAdverts().stream()
                            .map(foundAdvFromDb -> foundAdvFromDb.getUrl()).collect(Collectors.toList());

                    if(!alreadyFoundAdUrls.contains(fa.getUrl()))
                        foundAdvertService.createNewFoundAdvert(fa, fa.getUserInterest().getId());
                });

                System.out.println("DADA");

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }
}

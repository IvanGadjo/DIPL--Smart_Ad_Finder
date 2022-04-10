package smart.ad.founder.demo.web;

import org.springframework.web.bind.annotation.*;
import smart.ad.founder.demo.application.service.UserInterestsService;
import smart.ad.founder.demo.application.service.rest.RestServicePazar3;
import smart.ad.founder.demo.application.service.rest.RestServiceReklama5;
import smart.ad.founder.demo.domain.model.entities.FoundAdvert;
import smart.ad.founder.demo.domain.model.entities.UserInterest;
import smart.ad.founder.demo.domain.model.valueObjects.Keywords;
import smart.ad.founder.demo.domain.model.valueObjects.TimeValObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/userInterests")
@CrossOrigin("*")

public class UserInterestsController {

    UserInterestsService userInterestsService;
    RestServiceReklama5 restServiceReklama5;
    RestServicePazar3 restServicePazar3;

    public UserInterestsController(UserInterestsService userInterestsService, RestServiceReklama5 restServiceReklama5,
                                    RestServicePazar3 restServicePazar3) {
        this.userInterestsService = userInterestsService;
        this.restServiceReklama5 = restServiceReklama5;
        this.restServicePazar3 = restServicePazar3;
    }

    @GetMapping("/all")
    public List<UserInterest> getAllUserInterests() {
        return userInterestsService.findAllUserInterests();
    }

    @GetMapping("/all/byUser/{id}")
    public List<UserInterest> getAllUserInterestsOfUser(@PathVariable("id") Long userId) {
        return userInterestsService.findAllUserInterestsOfUser(userId);
    }

    @GetMapping("/{id}")
    public UserInterest getUserInterestById(@PathVariable("id") Long id) {
        return userInterestsService.findUserInterestById(id);
    }

    @PatchMapping("/editUserInterest")
    public UserInterest editUserInterest(@RequestBody UserInterest newUserInterest,
                                         @RequestParam Long userId) throws Exception {
        return userInterestsService.editUserInterest(newUserInterest,  userId);
    }

    // * Ako category ne e od default 5 categories, stavi "Other" (ne smee null)
    @PostMapping("/createUserInterest")
    public UserInterest createUserInterest(@RequestBody UserInterest userInterest,
                                           @RequestParam Long userId) throws Exception {
        return userInterestsService.addNewUserInterest(userInterest, userId);
    }




    @DeleteMapping("/{id}")
    public void deleteUserInterest(@PathVariable("id") Long id) {
        userInterestsService.deleteUserInterestById(id);
    }



    // ? TESTING REST SERVICE
    @GetMapping("/test/reklama5")
    public List<FoundAdvert> testReklama5() throws Exception {


        // * Koga se prima od front keywords, ke mora sve sto e so prazni mesta da go podelis
        // * na main i other keywords. pr userot vnesol Dacia Sandero 45, ti tuka go delis na Dacia (mainKw),
        // * Sandero (prv otherKw) i 45 (vtor OtherKw)

        List<String> okwrds = new ArrayList<>();
        okwrds.add("sandero");
        Keywords keywords = new Keywords("dacia", okwrds);

        TimeValObject tvo = new TimeValObject(LocalDateTime.now(), LocalDateTime.of(2021,1,1,1,1));
        String category = "Avtomobili";
        String region = "Skopje";

        UserInterest userInterest = new UserInterest(keywords, tvo, category, region, true);

//        return restServiceReklama5.getAdsUrls_reklama5(userInterest);
        restServicePazar3.getAdsUrls_pazar3(userInterest);
        return null;
    }
}

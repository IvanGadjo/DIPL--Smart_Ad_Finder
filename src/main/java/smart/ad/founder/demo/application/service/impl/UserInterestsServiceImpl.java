package smart.ad.founder.demo.application.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.ad.founder.demo.application.repo.FoundAdvertsRepo;
import smart.ad.founder.demo.application.repo.UserInterestsRepo;
import smart.ad.founder.demo.application.repo.UsersRepo;
import smart.ad.founder.demo.application.service.UserInterestsService;
import smart.ad.founder.demo.application.service.rest.RestServicePazar3;
import smart.ad.founder.demo.application.service.rest.RestServiceReklama5;
import smart.ad.founder.demo.application.service.rest.RestServicesScheduler;
import smart.ad.founder.demo.domain.model.entities.FoundAdvert;
import smart.ad.founder.demo.domain.model.entities.User;
import smart.ad.founder.demo.domain.model.entities.UserInterest;
import smart.ad.founder.demo.domain.model.valueObjects.Keywords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional

public class UserInterestsServiceImpl implements UserInterestsService {

    UserInterestsRepo userInterestsRepo;
    UsersRepo usersRepo;
    FoundAdvertsRepo foundAdvertsRepo;


    public UserInterestsServiceImpl(UserInterestsRepo userInterestsRepo, UsersRepo usersRepo,
                                    FoundAdvertsRepo foundAdvertsRepo
    ) {
        this.userInterestsRepo = userInterestsRepo;
        this.usersRepo = usersRepo;
        this.foundAdvertsRepo = foundAdvertsRepo;
    }

    @Override
    public List<UserInterest> findAllUserInterests() {
        return userInterestsRepo.findAll();
    }

    @Override
    public List<UserInterest> findAllUserInterestsOfUser(Long userId) {
        List<UserInterest> userInterestsOfUser = userInterestsRepo.findAll().stream().filter(usInt -> {
            return usInt.getUser().getId() == userId;
        }).collect(Collectors.toList());

        return userInterestsOfUser;
    }

    @Override
    public UserInterest findUserInterestById(Long id) {
        return userInterestsRepo.findById(id);
    }


    // TODO: testiraj dali pravi queries kon servisite
    @Override
    public UserInterest editUserInterest(UserInterest newUserInterest, Long userId) throws Exception {
        User theUser = usersRepo.findById(userId);
        newUserInterest.setUser(theUser);

        System.out.println(newUserInterest.getId()+ " " + newUserInterest.isActive());

        // filter keywords
        Keywords restructuredKeywords = restructureKeywords(newUserInterest.getKeywords());
        newUserInterest.setKeywords(restructuredKeywords);

        UserInterest savedUserInterest = userInterestsRepo.editUserInterest(newUserInterest);

        // perform timed calls to reklama5 and pazar3
        // restServiceReklama5.getAdsUrls_timed(savedUserInterest);
        // restServicePazar3.getAdsUrls_timed(savedUserInterest);

        return savedUserInterest;
    }

    // TODO: testiraj dali pravi queries kon servisite
    @Override
    public UserInterest addNewUserInterest(UserInterest userInterest, Long userId) throws Exception {
        User theUser = usersRepo.findById(userId);
        userInterest.setUser(theUser);

        // filter keywords
        Keywords restructuredKeywords = restructureKeywords(userInterest.getKeywords());
        userInterest.setKeywords(restructuredKeywords);

        UserInterest savedUserInterest = userInterestsRepo.saveNewUserInterest(userInterest);

        // perform timed calls to reklama5 and pazar3
        // restServiceReklama5.getAdsUrls_timed(savedUserInterest);
        // restServicePazar3.getAdsUrls_timed(savedUserInterest);

        return savedUserInterest;
    }

    @Override
    public void deleteUserInterestById(Long id) {
        userInterestsRepo.deleteById(id);
    }


    public Keywords restructureKeywords(Keywords keywords){
        String []keywordsArray = keywords.getMainKeyword().split(" ");

        System.out.println(keywords.getMainKeyword());
//        System.out.println(keywords.getOtherKeywords().get(0));

        String mainKeyword = keywordsArray[0];
        List<String> otherKeywords = new ArrayList<>();

        otherKeywords.addAll(Arrays.asList(keywordsArray).subList(1, keywordsArray.length));

        return new Keywords(mainKeyword, otherKeywords);
    }

}

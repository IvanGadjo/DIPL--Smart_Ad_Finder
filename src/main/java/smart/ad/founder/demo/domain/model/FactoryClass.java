package smart.ad.founder.demo.domain.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import smart.ad.founder.demo.domain.model.DTOs.KafkaFoundAdMessage;
import smart.ad.founder.demo.domain.model.entities.FoundAdvert;
import smart.ad.founder.demo.domain.model.entities.User;
import smart.ad.founder.demo.domain.model.entities.UserAdvert;
import smart.ad.founder.demo.domain.model.entities.UserInterest;
import smart.ad.founder.demo.domain.model.valueObjects.Keywords;
import smart.ad.founder.demo.domain.model.valueObjects.TimeValObject;

@Component
public class FactoryClass {

    public UserInterest createNewUserInterest(Keywords keywords, TimeValObject timeValObject,
                                              String category, String region, boolean active){
        return new UserInterest(keywords,timeValObject,category,region,active);
    }

    public User createNewUser(String userToken){
        return new User(userToken);
    }

    public FoundAdvert createNewFoundAdvert(String url, boolean alreadyShownToUser, String imageUrl, String title, String price){
        return new FoundAdvert(url, alreadyShownToUser, imageUrl, title, price);
    }

    public KafkaFoundAdMessage createNewKafkaFoundAdMessage(Long foundAdId, String url, String imageUrl, String title, String price, Long userInterestId, String userEmail){
        return new KafkaFoundAdMessage(foundAdId, url, imageUrl, title, price, userInterestId, userEmail);
    }

    public UserAdvert createNewUserAdvert(String description, String title, String category, String region, String price, Boolean isActive, Byte[] image, String contactInfo){
        return new UserAdvert(description, title, category, region, price, isActive, image, contactInfo);
    }
}

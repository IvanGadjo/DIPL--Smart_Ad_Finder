package smart.ad.founder.demo.domain.model.DTOs;


import lombok.Data;

@Data
public class KafkaFoundAdMessage {      // * Cel ad da se prakja, isto taka i za koj user e da se prakja userID

    private Long foundAdId;

    private String url;

    private String imageUrl;

    private String title;

    private String price;

    private Long userInterestId;

    private String userEmail;

    private Integer carYear;

    private Integer carMileage;


    // ? Maybe add userId also

    public KafkaFoundAdMessage(){}

    public KafkaFoundAdMessage(Long foundAdId, String url, String imageUrl, String title, String price, Long userInterestId, String userEmail, Integer carYear, Integer carMileage) {
        this.foundAdId = foundAdId;
        this.url = url;
        this.imageUrl = imageUrl;
        this.title = title;
        this.price = price;
        this.userInterestId = userInterestId;
        this.userEmail = userEmail;
        this.carYear = carYear;
        this.carMileage = carMileage;
    }

    @Override
    public String toString(){
        return "{" +
                "userInterestId: " + userInterestId +
                "userEmail: " + userEmail +
                "url: " + url +
                "title: " + title +
                "imageUrl: " + imageUrl +
                "price: " + price +
                "carYear: " + carYear +
                "carMileage: " + carMileage + "}";
    }
}

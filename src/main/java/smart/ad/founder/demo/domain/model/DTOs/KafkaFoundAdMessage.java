package smart.ad.founder.demo.domain.model.DTOs;


import lombok.Data;

@Data
public class KafkaFoundAdMessage {      // * Cel ad da se prakja, isto taka i za koj user e da se prakja userID

    private String adUrl;

    private Long userInterestId;

    public KafkaFoundAdMessage(){}

    public KafkaFoundAdMessage(String adUrl, Long userInterestId) {
        this.adUrl = adUrl;
        this.userInterestId = userInterestId;
    }

    @Override
    public String toString(){
        return "Message{" +
                " userInterestId: " + userInterestId +
                " adUrl: " + adUrl + "}";
    }
}

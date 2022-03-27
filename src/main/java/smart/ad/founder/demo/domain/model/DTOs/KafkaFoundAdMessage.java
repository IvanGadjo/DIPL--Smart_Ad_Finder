package smart.ad.founder.demo.domain.model.DTOs;


import lombok.Data;

@Data
public class KafkaFoundAdMessage {

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

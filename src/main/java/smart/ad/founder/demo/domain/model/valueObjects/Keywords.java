package smart.ad.founder.demo.domain.model.valueObjects;

import lombok.Getter;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
public class Keywords {

    private String mainKeyword;

    @ElementCollection
    private List<String> otherKeywords;

    @SuppressWarnings("unused")
    public Keywords(){
        mainKeyword = "";
        otherKeywords = new ArrayList<>();
    }

    public Keywords(String mainKeyword, List<String> otherKeywords){
        this.mainKeyword = mainKeyword;
        this.otherKeywords = otherKeywords;
    }

    // factory method
    public Keywords createKeywords(String mainKeyword, List<String> otherKeywords){
        return new Keywords(mainKeyword, otherKeywords);
    }

    public Keywords addOtherKeyword(String keyword){
        List<String> newOtherKeywords = otherKeywords;
        newOtherKeywords.add(keyword);
        return new Keywords(this.mainKeyword, newOtherKeywords);
    }
}

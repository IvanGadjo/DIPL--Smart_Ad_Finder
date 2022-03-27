package smart.ad.founder.demo.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.lang.Nullable;
import smart.ad.founder.demo.domain.model.valueObjects.Keywords;
import smart.ad.founder.demo.domain.model.valueObjects.TimeValObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usersInterests")
@Getter

public class UserInterest {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //value object
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "mainKeyword", column = @Column(name = "mainKeyword")),
            @AttributeOverride(name = "otherKeywords", column = @Column(name = "otherKeywords"))
    })
    private Keywords keywords;

    //value object
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "createdAt", column = @Column(name = "createdAt")),
            @AttributeOverride(name = "searchUntil", column = @Column(name = "searchUntil"))
    })
    private TimeValObject timeValObject;

    @Column(name = "categories")
    private String category;

    @Column(name = "regions")
    private String region;

    @Column(name = "actives")
    private boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    User user;


    @OneToMany(mappedBy = "userInterest", fetch = FetchType.EAGER)
    @Nullable
    private List<FoundAdvert> foundAdverts;

    @SuppressWarnings("unused")
    public UserInterest() {

    }

    public UserInterest(Keywords keywords, TimeValObject timeValObject,
                        String category, String region, boolean active) {
        this.keywords = keywords;
        this.timeValObject = timeValObject;
        this.category = category;
        this.region = region;
        this.active = active;
        foundAdverts = new ArrayList<>();
    }



    public void setKeywords(Keywords keywords) {
        this.keywords = keywords;
    }

    public void setTimeValObject(TimeValObject timeValObject) {
        this.timeValObject = timeValObject;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFoundAdverts(List<FoundAdvert> foundAdverts) {
        this.foundAdverts = foundAdverts;
    }

    // add keywords f-ja
    public void addKeywords(String keyword) {
        this.keywords = keywords.addOtherKeyword(keyword);
    }
}

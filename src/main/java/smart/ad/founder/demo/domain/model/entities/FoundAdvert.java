package smart.ad.founder.demo.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "foundAdverts")
@Getter

//@JsonIgnoreProperties(ignoreUnknown = true)           // indicates that any properties not bound in this type should be ignored
public class FoundAdvert {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ad_urls")
    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    // @Nullable
    private UserInterest userInterest;

    @Column(name = "alreadyShownToUsers")
    private Boolean alreadyShownToUser;

    @Column(name = "imageUrls")
    private String imageUrl;

    @Column(name = "titles")
    private String title;

    @Column(name = "prices")
    private String price;


    // * Ovie 2 props ne se null ako userInterestot e od kategorija Avtomobili. Togas se pravi dodatno skrejpanje za ovie.
    @Column(name = "car_years")
    @Nullable
    private Integer carYear;

    @Column(name = "car_mileages")
    @Nullable
    private Integer carMileage;

    @SuppressWarnings("unused")
    public FoundAdvert() {

    }

    public FoundAdvert(String url, boolean alreadyShownToUser, String imageUrl, String title, String price, Integer carYear, Integer carMileage) {
        this.url = url;
        this.alreadyShownToUser = alreadyShownToUser;
        this.imageUrl = imageUrl;
        this.title = title;
        this.price = price;
        this.carYear = carYear;
        this.carMileage = carMileage;
    }

    public void setUrl(String url) {
        this.url = url;

    }

    public void setUserInterest(UserInterest userInterest) {
        this.userInterest = userInterest;
    }

    public void setAlreadyShownToUser(boolean alreadyShownToUser) {
        this.alreadyShownToUser = alreadyShownToUser;
    }
}

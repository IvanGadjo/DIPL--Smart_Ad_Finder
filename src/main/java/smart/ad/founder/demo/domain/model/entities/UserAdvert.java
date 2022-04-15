package smart.ad.founder.demo.domain.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "userAdverts")
@Getter
public class UserAdvert {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descriptions")
    private String description;

    @Column(name = "titles")
    private String title;

    @Column(name = "categories")
    private String category;

    @Column(name = "regions")
    private String region;

    @Column(name = "prices")
    private String price;

    @Column(name = "isActives")
    private Boolean isActive;

    @Lob
    @Column(name = "images")
    private Byte[] image;


    // * DB Connections

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;





    @SuppressWarnings("unused")
    public UserAdvert() {}

    public UserAdvert(String description, String title, String category, String region, String price, Boolean isActive, Byte[] image) {
        this.description = description;
        this.title = title;
        this.category = category;
        this.region = region;
        this.price = price;
        this.isActive = isActive;
        this.image = image;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

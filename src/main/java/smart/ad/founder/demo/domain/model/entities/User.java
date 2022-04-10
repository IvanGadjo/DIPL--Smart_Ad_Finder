package smart.ad.founder.demo.domain.model.entities;

import lombok.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter


public class User {

    @Version
    private long Version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userEmails", unique = true)        // TODO: Ova treba da bide USER EMAIL
    private String userEmail;


    // * DB Connections

    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserInterest> userInterests;

    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserAdvert> userAdverts;

    @SuppressWarnings("unused")
    public User() {

    }

    public User(String userEmail) {
        this.userEmail = userEmail;
        userInterests = new ArrayList<>();
        userAdverts = new ArrayList<>();
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserInterests(List<UserInterest> userInterests) {
        this.userInterests = userInterests;
    }
    public void setUserAdverts(List<UserAdvert> userAdverts) {
        this.userAdverts = userAdverts;
    }
}

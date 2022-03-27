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


    // FIXME: Ova pravi samo edna konekcija kaj db - kaj userInterest se cuva samo userId
    // nema distinkcija pomegju dveve listi, pa zatoa koga se kreira userInterest go stava
    // i vo dvete listi. Nie sega ke proveruvame spored active prop na userInterestot,
    // ama ova treba da bide samo edna lista


//    @Fetch(value = FetchMode.SUBSELECT)
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    private List<UserInterest> activeInterests;
//
//    @Fetch(value = FetchMode.SUBSELECT)
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    private List<UserInterest> pastInterests;

    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserInterest> userInterests;

    @SuppressWarnings("unused")
    public User() {

    }

    public User(String userEmail) {
        this.userEmail = userEmail;
//        activeInterests = new ArrayList<>();
//        pastInterests = new ArrayList<>();
        userInterests = new ArrayList<>();
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

//    public void setActiveInterests(List<UserInterest> activeInterests) {
//        this.activeInterests = activeInterests;
//    }
//
//    public void setPastInterests(List<UserInterest> pastInterests) {
//        this.pastInterests = pastInterests;
//    }

    public void setUserInterests(List<UserInterest> userInterests) {
        this.userInterests = userInterests;
    }
}

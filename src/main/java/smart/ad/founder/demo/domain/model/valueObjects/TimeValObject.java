package smart.ad.founder.demo.domain.model.valueObjects;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Embeddable
@Getter
public class TimeValObject {

    private LocalDateTime createdAt;

    private LocalDateTime searchUntil;

    @SuppressWarnings("unused")
    public TimeValObject(){
        createdAt = LocalDateTime.now();
        searchUntil = LocalDateTime.now();
    }

    public TimeValObject(LocalDateTime createdAt, LocalDateTime searchUntil) {
        this.createdAt = createdAt;
        this.searchUntil = searchUntil;
    }

    // factory method
    public TimeValObject createTimeValObject(LocalDateTime createdAt, LocalDateTime searchUntil){
        if(searchUntil.isBefore(createdAt)){
            throw new IllegalArgumentException("Search until date must be after creation date");
        }
        return new TimeValObject(createdAt, searchUntil);
    }

    public TimeValObject changeSearchUntilDate(LocalDateTime newSearchUntil){
        if(newSearchUntil.isBefore(createdAt)){
            throw new IllegalArgumentException("Search until date must be after creation date");
        }
        return new TimeValObject(this.createdAt, newSearchUntil);
    }
}

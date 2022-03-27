package smart.ad.founder.demo.application.repo;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.ad.founder.demo.domain.model.entities.FoundAdvert;
import smart.ad.founder.demo.domain.repo.FoundAdvertRepoJPA;

import java.util.List;

@Service
@Transactional
//@Repository
public class FoundAdvertsRepo {

    FoundAdvertRepoJPA foundAdvertRepoJPA;

    public FoundAdvertsRepo(FoundAdvertRepoJPA foundAdvertRepoJPA) {
        this.foundAdvertRepoJPA = foundAdvertRepoJPA;
    }

    public List<FoundAdvert> findAll(){
        return foundAdvertRepoJPA.findAll();
    }

    public FoundAdvert findById(Long id){
        return foundAdvertRepoJPA.findById(id).orElseThrow(RuntimeException::new);
    }

    public FoundAdvert saveNewFoundAdvert(FoundAdvert foundAdvert){
        return foundAdvertRepoJPA.save(foundAdvert);
    }

    public FoundAdvert editFoundAdvert(FoundAdvert foundAdvert){
        FoundAdvert old = foundAdvertRepoJPA.findById(foundAdvert.getId()).orElseThrow(RuntimeException::new);
        old.setUrl(foundAdvert.getUrl());
        old.setUserInterest(foundAdvert.getUserInterest());
        return foundAdvertRepoJPA.save(old);
    }

    public void deleteById(Long id){
        foundAdvertRepoJPA.deleteById(id);
    }

}

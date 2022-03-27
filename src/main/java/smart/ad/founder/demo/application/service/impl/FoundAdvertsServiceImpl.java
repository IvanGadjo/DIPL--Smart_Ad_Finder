package smart.ad.founder.demo.application.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.ad.founder.demo.application.repo.FoundAdvertsRepo;
import smart.ad.founder.demo.application.repo.UserInterestsRepo;
import smart.ad.founder.demo.application.service.FoundAdvertService;
import smart.ad.founder.demo.domain.model.entities.FoundAdvert;
import smart.ad.founder.demo.domain.model.entities.UserInterest;

import java.util.List;


@Service
@Transactional
public class FoundAdvertsServiceImpl implements FoundAdvertService {

    FoundAdvertsRepo foundAdvertsRepo;
    UserInterestsRepo userInterestsRepo;

    public FoundAdvertsServiceImpl(FoundAdvertsRepo foundAdvertsRepo, UserInterestsRepo userInterestsRepo) {
        this.foundAdvertsRepo = foundAdvertsRepo;
        this.userInterestsRepo = userInterestsRepo;
    }

    @Override
    public List<FoundAdvert> findAllFoundAdverts() {
        return foundAdvertsRepo.findAll();
    }

    @Override
    public FoundAdvert findFoundAdvertById(Long id) {
        return foundAdvertsRepo.findById(id);
    }


    @Override
    public FoundAdvert editFoundAdvert(FoundAdvert newFoundAdvert, Long userInterestId) {
        UserInterest theUserInterest = userInterestsRepo.findById(userInterestId);
        newFoundAdvert.setUserInterest(theUserInterest);

        return foundAdvertsRepo.editFoundAdvert(newFoundAdvert);
    }

    @Override
    public FoundAdvert createNewFoundAdvert(FoundAdvert foundAdvert, Long userInterestId) {
        UserInterest theUserInterest = userInterestsRepo.findById(userInterestId);
        foundAdvert.setUserInterest(theUserInterest);

        return foundAdvertsRepo.saveNewFoundAdvert(foundAdvert);
    }

    @Override
    public void deleteFoundAdvertById(Long id) {
        foundAdvertsRepo.deleteById(id);
    }
}

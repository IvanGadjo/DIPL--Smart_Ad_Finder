package smart.ad.founder.demo.web;


import org.springframework.web.bind.annotation.*;
import smart.ad.founder.demo.application.service.FoundAdvertService;
import smart.ad.founder.demo.domain.model.entities.FoundAdvert;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/foundAdverts")
@CrossOrigin("*")
public class FoundAdvertsController {

    private FoundAdvertService foundAdvertService;

    public FoundAdvertsController(FoundAdvertService foundAdvertService) {
        this.foundAdvertService = foundAdvertService;
    }

    @GetMapping("/all")
    public List<FoundAdvert> getAllFoundAdverts(){
        return foundAdvertService.findAllFoundAdverts();
    }

    @GetMapping("/{id}")
    public FoundAdvert getFoundAdvertById(@PathVariable("id") Long id){
        return foundAdvertService.findFoundAdvertById(id);
    }

    @PatchMapping("/editFoundAdvert")
    public FoundAdvert editFoundAdvert(@RequestBody FoundAdvert newFoundAdvert,
                                       @RequestParam Long userInterestId){
        return foundAdvertService.editFoundAdvert(newFoundAdvert, userInterestId);
    }

    @PostMapping("/createFoundAdvert")
    public FoundAdvert createFoundAdvert(@RequestBody FoundAdvert user,
                                         @RequestParam Long userInterestId){
        return foundAdvertService.createNewFoundAdvert(user, userInterestId);
    }

    @DeleteMapping("/{id}")
    public void deleteFoundAdvert(@PathParam("id") Long id){
        foundAdvertService.deleteFoundAdvertById(id);
    }
}

package smart.ad.founder.demo.web;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.ArrayUtils;
import smart.ad.founder.demo.application.service.UserAdvertService;
import smart.ad.founder.demo.domain.model.FactoryClass;
import smart.ad.founder.demo.domain.model.entities.UserAdvert;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/userAdverts")
@CrossOrigin("*")
public class UserAdvertsController {

    UserAdvertService userAdvertService;
    FactoryClass factory;

    public UserAdvertsController(UserAdvertService userAdvertService, FactoryClass factory) {
        this.userAdvertService = userAdvertService;
        this.factory = factory;
    }


    @GetMapping("/all")
    public List<UserAdvert> getAllUserAdverts() {
        return userAdvertService.findAllUserAdverts();
    }

    @GetMapping("/all/byUser/{id}")
    public List<UserAdvert> getAllUserAdvertsOfUser(@PathVariable("id") Long userId) {
        return userAdvertService.findAllUserAdvertsOfUser(userId);
    }

    @GetMapping("/{id}")
    public UserAdvert getUserAdvertById(@PathVariable("id") Long id) {
        return userAdvertService.findUserAdvertById(id);
    }

    // ! TREBA DA E KAKO CREATE USER ADVERT - VIDI VO INTELIGENTA PROEKT
    @PatchMapping(value = "/editUserAdvert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserAdvert editUserAdvert(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "region", required = false) String region,
            @RequestParam(value = "price", required = false) String price,
            @RequestParam(value = "isActive", required = false) Boolean isActive,
            @RequestParam(value = "contactInfo", required = false) String contactInfo,

            @RequestParam(value = "image", required = false) MultipartFile image,

            @RequestParam(value = "userId", required = false) Long userId) throws Exception {

        byte[] imageBytes = null;

        if (image != null) {
            if (!image.getContentType().equals("image/png") && !image.getContentType().equals("image/jpeg") && !image.getContentType().equals("image/gif")) {
                throw new RuntimeException();      // ! Treba da e custom exception - file not in image format
            }
            imageBytes = image.getBytes();
        }

        Byte[] byteObjects = new Byte[imageBytes.length];
        int i=0;
        for(byte b: imageBytes)
            byteObjects[i++] = b;  // Autoboxing.

        UserAdvert newUserAdvert = factory.createNewUserAdvert(description, title, category, region, price, isActive, byteObjects, contactInfo);

        return userAdvertService.editUserAdvert(newUserAdvert, id,  userId);
    }



    // ! MOZEBI NE TREBA VAKA SLIKA SO BYTE ARRAY
    @PostMapping(value = "/createUserAdvert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserAdvert createUserAdvert(
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "region", required = false) String region,
            @RequestParam(value = "price", required = false) String price,
            @RequestParam(value = "isActive", required = false) Boolean isActive,
            @RequestParam(value = "contactInfo", required = false) String contactInfo,

            @RequestParam(value = "image", required = false) MultipartFile image,

            @RequestParam(value = "userId", required = false) Long userId) throws Exception {

        byte[] imageBytes = null;

        if (image != null) {
            if (!image.getContentType().equals("image/png") && !image.getContentType().equals("image/jpeg") && !image.getContentType().equals("image/gif")) {
                throw new RuntimeException();      // ! Treba da e custom exception - file not in image format
            }
            imageBytes = image.getBytes();
        }

        Byte[] byteObjects = new Byte[imageBytes.length];
        int i=0;
        for(byte b: imageBytes)
            byteObjects[i++] = b;  // Autoboxing.



        UserAdvert newUserAdvert = factory.createNewUserAdvert(description, title, category, region, price, isActive, byteObjects, contactInfo);

        return userAdvertService.addNewUserAdvert(newUserAdvert, userId);
    }


    @PatchMapping("/setIsActive")
    public UserAdvert setisActive(@RequestBody UserAdvert newUserAdvert, @RequestParam Long userId) throws Exception {
        return userAdvertService.editUserAdvert(newUserAdvert, newUserAdvert.getId(),  userId);
    }


    @DeleteMapping("/{id}")
    public void deleteUserAdvert(@PathVariable("id") Long id) {
        userAdvertService.deleteUserAdvertById(id);
    }

}

package com.portfolio.josebackend.Controller;

import com.portfolio.josebackend.Dto.DtoBanner;
import com.portfolio.josebackend.Entity.Banner;
import com.portfolio.josebackend.Service.BannerService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
@CrossOrigin(origins = "https://porftfoliojose.web.app")
public class BannerController {

    @Autowired
    BannerService bannerService;

    @GetMapping("/list")
    public ResponseEntity<List<Banner>> list() {
        List<Banner> list = bannerService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/traer")
    public List<Banner> getBanner() {
        return bannerService.getBanner();
    }

    @PutMapping("/update/{id}")
    public Banner update(@PathVariable int id,
            @RequestBody Banner banner) {
        bannerService.save(banner);
        return banner;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!bannerService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este Id no Existe"), HttpStatus.NOT_FOUND);
        }
        bannerService.delete(id);
        return new ResponseEntity(new Mensaje("Item eliminado con éxito"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoBanner dtoBanner) {
        if (StringUtils.isBlank(dtoBanner.getName())) {
            return new ResponseEntity(new Mensaje("Debe incluir nombre"), HttpStatus.BAD_REQUEST);
        }
        if (bannerService.existsByName(dtoBanner.getName())) {
            return new ResponseEntity(new Mensaje("Item no registrado"), HttpStatus.BAD_REQUEST);
        }
        Banner banner = new Banner(dtoBanner.getName(), dtoBanner.getBackImage(),
                dtoBanner.getPhoto(), dtoBanner.getAbout(), dtoBanner.getTitle());
        bannerService.save(banner);
        return new ResponseEntity(new Mensaje("Item Registrado con éxito"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Banner> getById(@PathVariable("id") int id) {
        if (!bannerService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este item no existe"), HttpStatus.NOT_FOUND);
        }
        Banner banner = bannerService.getOne(id).get();
        return new ResponseEntity(banner, HttpStatus.OK);
    }

}

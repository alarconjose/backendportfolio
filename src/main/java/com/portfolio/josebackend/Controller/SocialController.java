package com.portfolio.josebackend.Controller;

import com.portfolio.josebackend.Dto.DtoSocial;
import com.portfolio.josebackend.Entity.Social;
import com.portfolio.josebackend.Service.SocialService;
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
@RequestMapping("/social")
@CrossOrigin(origins = "https://porftfoliojose.web.app")
public class SocialController {

    @Autowired
    SocialService socialService;

    @GetMapping("/list")
    public ResponseEntity<List<Social>> list() {
        List<Social> list = socialService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Social> getById(@PathVariable("id") int id) {
        if (!socialService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Item no existe"), HttpStatus.NOT_FOUND);
        }
        Social social = socialService.getOne(id).get();
        return new ResponseEntity(social, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoSocial dtoSocial) {
        if (StringUtils.isBlank(dtoSocial.getName())) {
            return new ResponseEntity(new Mensaje("Necesario insertar nombre"), HttpStatus.BAD_REQUEST);
        }
        if (socialService.existsByName(dtoSocial.getName())) {
            return new ResponseEntity(new Mensaje("Item no registrado"), HttpStatus.BAD_REQUEST);
        }

        Social social = new Social(dtoSocial.getName(),
                dtoSocial.getImage());
        socialService.save(social);
        return new ResponseEntity(new Mensaje("Item Registrado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public Social update(@PathVariable int id,
            @RequestBody Social social) {

        socialService.save(social);
        return social;
    }

    /*
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoExperienciaLaboral dtoExperienciaLaboral) {
        if (!experienciaLaboralService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este Id no existe"), HttpStatus.BAD_REQUEST);
        }
        if (experienciaLaboralService.existsByName(dtoExperienciaLaboral.getName())
                && experienciaLaboralService.getByName(dtoExperienciaLaboral.getName()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Experiencia ya existente"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoExperienciaLaboral.getName())) {
            return new ResponseEntity(new Mensaje("Nombre requerido"), HttpStatus.BAD_REQUEST);
        }

        ExperienciaLaboral experienciaLaboral = experienciaLaboralService.getOne(id).get();
        experienciaLaboral.setImage(dtoExperienciaLaboral.getImage());
        experienciaLaboral.setName(dtoExperienciaLaboral.getName());
        experienciaLaboral.setTitle(dtoExperienciaLaboral.getTitle());
        experienciaLaboral.setDescription(dtoExperienciaLaboral.getDescription());
        experienciaLaboral.setDate(dtoExperienciaLaboral.getDate());

        experienciaLaboralService.save(experienciaLaboral);
        return new ResponseEntity(new Mensaje("Experiencia Laboral Actualizada con éxito"), HttpStatus.OK);
    }*/

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!socialService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El Item no Existe"), HttpStatus.NOT_FOUND);
        }
        socialService.delete(id);
        return new ResponseEntity(new Mensaje("Item Eliminado"), HttpStatus.OK);
    }
}

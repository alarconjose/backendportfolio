package com.portfolio.josebackend.Controller;

import com.portfolio.josebackend.Dto.DtoEducacion;
import com.portfolio.josebackend.Entity.Educacion;
import com.portfolio.josebackend.Service.EducacionService;
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
@RequestMapping("/educacion")
@CrossOrigin(origins = "http://localhost:4200")
public class EducacionController {

    @Autowired
    EducacionService educacionService;

    @GetMapping("/list")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = educacionService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id) {
        if (!educacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Item no existe"), HttpStatus.NOT_FOUND);
        }
        Educacion educacion = educacionService.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoEducacion dtoEducacion) {
        if (StringUtils.isBlank(dtoEducacion.getSchool())) {
            return new ResponseEntity(new Mensaje("Necesario insertar nombre"), HttpStatus.BAD_REQUEST);
        }
        if (educacionService.existsBySchool(dtoEducacion.getSchool())) {
            return new ResponseEntity(new Mensaje("Item no registrado"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = new Educacion(dtoEducacion.getImage(),
                dtoEducacion.getSchool(), dtoEducacion.getTitle(),
                dtoEducacion.getDate(), dtoEducacion.getDescription());
        educacionService.save(educacion);
        return new ResponseEntity(new Mensaje("Item Registrado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public Educacion update(@PathVariable int id,
            @RequestBody Educacion educacion) {

        educacionService.save(educacion);
        return educacion;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!educacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El Item no Existe"), HttpStatus.NOT_FOUND);
        }
        educacionService.delete(id);
        return new ResponseEntity(new Mensaje("Item Eliminado"), HttpStatus.OK);
    }
}

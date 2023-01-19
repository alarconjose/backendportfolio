package com.portfolio.josebackend.Controller;

import com.portfolio.josebackend.Dto.DtoHabilidad;
import com.portfolio.josebackend.Entity.Habilidad;
import com.portfolio.josebackend.Service.HabilidadService;
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
@RequestMapping("/habilidad")
@CrossOrigin(origins = "https://porftfoliojose.web.app/")
public class HabilidadController {

    @Autowired
    HabilidadService habilidadService;

    @GetMapping("/list")
    public ResponseEntity<List<Habilidad>> list() {
        List<Habilidad> list = habilidadService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Habilidad> getById(@PathVariable("id") int id) {
        if (!habilidadService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Item no existe"), HttpStatus.NOT_FOUND);
        }
        Habilidad habilidad = habilidadService.getOne(id).get();
        return new ResponseEntity(habilidad, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoHabilidad dtoHabilidad) {
        if (StringUtils.isBlank(dtoHabilidad.getName())) {
            return new ResponseEntity(new Mensaje("Necesario insertar nombre"), HttpStatus.BAD_REQUEST);
        }
        if (habilidadService.existsByName(dtoHabilidad.getName())) {
            return new ResponseEntity(new Mensaje("Item no registrado"), HttpStatus.BAD_REQUEST);
        }

        Habilidad habilidad = new Habilidad(dtoHabilidad.getName(),
                dtoHabilidad.getValue());
        habilidadService.save(habilidad);
        return new ResponseEntity(new Mensaje("Item Registrado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public Habilidad update(@PathVariable int id,
            @RequestBody Habilidad habilidad) {

        habilidadService.save(habilidad);
        return habilidad;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!habilidadService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El Item no Existe"), HttpStatus.NOT_FOUND);
        }
        habilidadService.delete(id);
        return new ResponseEntity(new Mensaje("Item Eliminado"), HttpStatus.OK);
    }
}

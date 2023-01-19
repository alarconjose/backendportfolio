package com.portfolio.josebackend.Controller;

import com.portfolio.josebackend.Dto.DtoProyecto;
import com.portfolio.josebackend.Entity.Proyecto;
import com.portfolio.josebackend.Service.ProyectoService;
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
@RequestMapping("/proyecto")
@CrossOrigin(origins = "https://porftfoliojose.web.app/")
public class ProyectoController {

    @Autowired
    ProyectoService proyectoService;

    @GetMapping("/list")
    public ResponseEntity<List<Proyecto>> list() {
        List<Proyecto> list = proyectoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id) {
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Item no existe"), HttpStatus.NOT_FOUND);
        }
        Proyecto proyecto = proyectoService.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoProyecto dtoProyecto) {
        if (StringUtils.isBlank(dtoProyecto.getName())) {
            return new ResponseEntity(new Mensaje("Necesario insertar nombre"), HttpStatus.BAD_REQUEST);
        }
        if (proyectoService.existsByName(dtoProyecto.getName())) {
            return new ResponseEntity(new Mensaje("Item no registrado"), HttpStatus.BAD_REQUEST);
        }

        Proyecto proyecto = new Proyecto(dtoProyecto.getImage(),
                dtoProyecto.getName(), dtoProyecto.getDate(),
                dtoProyecto.getDescription(), dtoProyecto.getUrl());
        proyectoService.save(proyecto);
        return new ResponseEntity(new Mensaje("Item Registrado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public Proyecto update(@PathVariable int id,
            @RequestBody Proyecto proyecto) {

        proyectoService.save(proyecto);
        return proyecto;
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El Item no Existe"), HttpStatus.NOT_FOUND);
        }
        proyectoService.delete(id);
        return new ResponseEntity(new Mensaje("Item Eliminado"), HttpStatus.OK);
    }
}


package com.portfolio.josebackend.Controller;

import com.portfolio.josebackend.Dto.DtoLaboral;
import com.portfolio.josebackend.Entity.Laboral;
import com.portfolio.josebackend.Service.LaboralService;
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
@RequestMapping("/laboral")
@CrossOrigin(origins = "https://porftfoliojose.web.app/")
public class LaboralController {
    @Autowired
    LaboralService laboralService;

    @GetMapping("/list")
    public ResponseEntity<List<Laboral>> list() {
        List<Laboral> list = laboralService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Laboral> getById(@PathVariable("id") int id) {
        if (!laboralService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Item no existe"), HttpStatus.NOT_FOUND);
        }
        Laboral laboral = laboralService.getOne(id).get();
        return new ResponseEntity(laboral, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoLaboral dtoLaboral) {
        if (StringUtils.isBlank(dtoLaboral.getName())) {
            return new ResponseEntity(new Mensaje("Necesario insertar nombre"), HttpStatus.BAD_REQUEST);
        }
        if (laboralService.existsByName(dtoLaboral.getName())) {
            return new ResponseEntity(new Mensaje("Item no registrado"), HttpStatus.BAD_REQUEST);
        }

        Laboral laboral = new Laboral(dtoLaboral.getImage(),
                dtoLaboral.getName(), dtoLaboral.getTitle(),
                 dtoLaboral.getDate(),dtoLaboral.getDescription());
        laboralService.save(laboral);
        return new ResponseEntity(new Mensaje("Item Registrado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
        public Laboral update(@PathVariable int id,
            @RequestBody Laboral laboral) {

        laboralService.save(laboral);
        return laboral;
        }
   

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!laboralService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El Item no Existe"), HttpStatus.NOT_FOUND);
        }
        laboralService.delete(id);
        return new ResponseEntity(new Mensaje("Item Eliminado"), HttpStatus.OK);
    }
}

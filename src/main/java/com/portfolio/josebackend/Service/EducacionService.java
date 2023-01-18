package com.portfolio.josebackend.Service;

import com.portfolio.josebackend.Entity.Educacion;
import com.portfolio.josebackend.Repository.IEducacion;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EducacionService {

    @Autowired
    IEducacion iEducacion;

    public List<Educacion> list() {
        return iEducacion.findAll();
    }

    public Optional<Educacion> getOne(int id) {
        return iEducacion.findById(id);
    }

    public Optional<Educacion> getBySchool(String school) {
        return iEducacion.findBySchool(school);
    }

    public void save(Educacion educacion) {
        iEducacion.save(educacion);
    }

    public void delete(int id) {
        iEducacion.deleteById(id);
    }

    public boolean existsById(int id) {
        return iEducacion.existsById(id);
    }

    public boolean existsBySchool(String school) {
        return iEducacion.existsBySchool(school);
    }
}

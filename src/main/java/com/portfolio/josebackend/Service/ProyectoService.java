package com.portfolio.josebackend.Service;

import com.portfolio.josebackend.Entity.Proyecto;
import com.portfolio.josebackend.Repository.IProyecto;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProyectoService {

    @Autowired
    IProyecto iProyecto;

    public List<Proyecto> list() {
        return iProyecto.findAll();
    }

    public Optional<Proyecto> getOne(int id) {
        return iProyecto.findById(id);
    }

    public Optional<Proyecto> getByName(String name) {
        return iProyecto.findByName(name);
    }

    public void save(Proyecto proyecto) {
        iProyecto.save(proyecto);
    }

    public void delete(int id) {
        iProyecto.deleteById(id);
    }

    public boolean existsById(int id) {
        return iProyecto.existsById(id);
    }

    public boolean existsByName(String name) {
        return iProyecto.existsByName(name);
    }
}

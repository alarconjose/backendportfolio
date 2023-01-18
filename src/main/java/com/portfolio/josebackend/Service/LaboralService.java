package com.portfolio.josebackend.Service;

import com.portfolio.josebackend.Entity.Laboral;
import com.portfolio.josebackend.Repository.ILaboral;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LaboralService {

    @Autowired
    ILaboral iLaboral;

    public List<Laboral> list() {
        return iLaboral.findAll();
    }

    public Optional<Laboral> getOne(int id) {
        return iLaboral.findById(id);
    }

    public Optional<Laboral> getByName(String name) {
        return iLaboral.findByName(name);
    }

    public void save(Laboral laboral) {
        iLaboral.save(laboral);
    }

    public void delete(int id) {
        iLaboral.deleteById(id);
    }

    public boolean existsById(int id) {
        return iLaboral.existsById(id);
    }

    public boolean existsByName(String name) {
        return iLaboral.existsByName(name);
    }
}

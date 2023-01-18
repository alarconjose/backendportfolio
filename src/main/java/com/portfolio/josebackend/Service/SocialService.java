package com.portfolio.josebackend.Service;

import com.portfolio.josebackend.Entity.Social;
import com.portfolio.josebackend.Repository.ISocial;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SocialService {

    @Autowired
    ISocial iSocial;

    public List<Social> list() {
        return iSocial.findAll();
    }

    public Optional<Social> getOne(int id) {
        return iSocial.findById(id);
    }

    public Optional<Social> getByName(String name) {
        return iSocial.findByName(name);
    }

    public void save(Social social) {
        iSocial.save(social);
    }

    public void delete(int id) {
        iSocial.deleteById(id);
    }

    public boolean existsById(int id) {
        return iSocial.existsById(id);
    }

    public boolean existsByName(String name) {
        return iSocial.existsByName(name);
    }
}

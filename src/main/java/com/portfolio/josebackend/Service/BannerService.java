package com.portfolio.josebackend.Service;

import com.portfolio.josebackend.Entity.Banner;
import com.portfolio.josebackend.Repository.IBanner;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BannerService {

    @Autowired
    IBanner iBanner;

    public List<Banner> list() {
        return iBanner.findAll();
    }

    public Optional<Banner> getOne(int id) {
        return iBanner.findById(id);
    }

    public Optional<Banner> getByName(String name) {
        return iBanner.findByName(name);
    }

    public void save(Banner banner) {
        iBanner.save(banner);
    }

    public void delete(int id) {
        iBanner.deleteById(id);
    }

    public boolean existsById(int id) {
        return iBanner.existsById(id);
    }

    public boolean existsByName(String name) {
        return iBanner.existsByName(name);
    }

    public List<Banner> getBanner() {
        List<Banner> banner = iBanner.findAll();
        return banner;
    }

    public Banner findBanner(int id) {
        Banner banner = iBanner.findById(id).orElse(null);
        return banner;
    }
}

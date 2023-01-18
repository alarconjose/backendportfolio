package com.portfolio.josebackend.Repository;

import com.portfolio.josebackend.Entity.Banner;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBanner extends JpaRepository<Banner, Integer> {

    public Optional<Banner> findByName(String name);

    public boolean existsByName(String name);
}

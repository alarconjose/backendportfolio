package com.portfolio.josebackend.Repository;

import com.portfolio.josebackend.Entity.Laboral;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILaboral extends JpaRepository<Laboral, Integer> {

    public Optional<Laboral> findByName(String name);

    public boolean existsByName(String name);
}

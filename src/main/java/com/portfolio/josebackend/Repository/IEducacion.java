package com.portfolio.josebackend.Repository;

import com.portfolio.josebackend.Entity.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEducacion extends JpaRepository<Educacion, Integer> {

    public Optional<Educacion> findBySchool(String school);

    public boolean existsBySchool(String school);
}

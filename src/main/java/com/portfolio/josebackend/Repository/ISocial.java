package com.portfolio.josebackend.Repository;

import com.portfolio.josebackend.Entity.Social;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISocial extends JpaRepository<Social, Integer> {

    public Optional<Social> findByName(String name);

    public boolean existsByName(String name);
}

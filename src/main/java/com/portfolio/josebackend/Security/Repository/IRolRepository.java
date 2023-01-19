
package com.portfolio.josebackend.Security.Repository;

import com.portfolio.josebackend.Security.Entity.Rol;
import com.portfolio.josebackend.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}

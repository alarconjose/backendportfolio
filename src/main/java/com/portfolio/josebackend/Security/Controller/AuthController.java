
package com.portfolio.josebackend.Security.Controller;

import com.portfolio.josebackend.Security.Dto.DtoJwt;
import com.portfolio.josebackend.Security.Dto.DtoLoginUsuario;
import com.portfolio.josebackend.Security.Dto.DtoNuevoUsuario;
import com.portfolio.josebackend.Security.Entity.Rol;
import com.portfolio.josebackend.Security.Entity.Usuario;
import com.portfolio.josebackend.Security.Enums.RolNombre;
import com.portfolio.josebackend.Security.Jwt.JwtProvider;
import com.portfolio.josebackend.Security.Service.RolService;
import com.portfolio.josebackend.Security.Service.UsuarioService;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://porftfoliojose.web.app")
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvide;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody DtoNuevoUsuario dtoNuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos o email invalido"), HttpStatus.BAD_REQUEST);
        }
        if (usuarioService.existsByNombreUsuario(dtoNuevoUsuario.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("El nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (usuarioService.existsByEmail(dtoNuevoUsuario.getEmail())) {
            return new ResponseEntity(new Mensaje("El email ya existe"), HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario(dtoNuevoUsuario.getNombre(), dtoNuevoUsuario.getNombreUsuario(),
                dtoNuevoUsuario.getEmail(), passwordEncoder.encode(dtoNuevoUsuario.getPassword()));

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());

        if (dtoNuevoUsuario.getRoles().contains("admin")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        usuario.setRoles(roles);
        usuarioService.save(usuario);

        return new ResponseEntity(new Mensaje("Usuario guardado con exito"), HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<DtoJwt> login(@Valid @RequestBody DtoLoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Error de campos"), HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvide.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        DtoJwt jwtDto = new DtoJwt(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}

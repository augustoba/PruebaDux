package com.dux.pruebatecnica.controller;

import com.dux.pruebatecnica.Security.JwtGenerator;
import com.dux.pruebatecnica.dto.loginDTO.AuthResponse;
import com.dux.pruebatecnica.dto.loginDTO.LoginDto;
import com.dux.pruebatecnica.dto.loginDTO.RegistroDTO;
import com.dux.pruebatecnica.model.Role;
import com.dux.pruebatecnica.model.Usuario;
import com.dux.pruebatecnica.repository.RoleRepository;
import com.dux.pruebatecnica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping({"/auth"})

public class UserController {

    private AuthenticationManager authenticationManager;
    private PasswordEncoder   passwordEncoder;
    private RoleRepository roleRepository;
    private UsuarioRepository usuarioRepository;
    private JwtGenerator jwtGenerator;

@Autowired
    public UserController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UsuarioRepository usuarioRepository, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/register" )
    public ResponseEntity<String> registrarUser(@RequestBody RegistroDTO registro){
        if(usuarioRepository.existsByUsername(registro.getUsername())){
            return new ResponseEntity<>("el usuario ya existe", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(registro.getUsername());
        usuario.setPassword(passwordEncoder.encode(registro.getPassword()));
        Role rol = roleRepository.findByName("USER").get();
        usuario.setRoles(Collections.singletonList(rol));
        usuarioRepository.save(usuario);
        return new ResponseEntity<>("Registro exitoso",HttpStatus.OK);
}


    @PostMapping("/register/adm" )
    public ResponseEntity<String> registrarAdmin(@RequestBody RegistroDTO registro){
        if(usuarioRepository.existsByUsername(registro.getUsername())){
            return new ResponseEntity<>("el usuario ya existe", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(registro.getUsername());
        usuario.setPassword(passwordEncoder.encode(registro.getPassword()));
        Role rol = roleRepository.findByName("ADMIN").get();
        usuario.setRoles(Collections.singletonList(rol));
        usuarioRepository.save(usuario);
        return new ResponseEntity<>("Registro exitoso",HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto){

    try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generarToken(authentication);
        return new ResponseEntity<>(new AuthResponse(token),HttpStatus.OK);
    }catch (AuthenticationException e){
        return new ResponseEntity<>(new AuthResponse("Error: Usuario o contraseña incorrectos"), HttpStatus.UNAUTHORIZED);
    }

 }

}
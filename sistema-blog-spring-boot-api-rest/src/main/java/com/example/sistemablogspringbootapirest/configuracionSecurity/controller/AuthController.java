/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemablogspringbootapirest.configuracionSecurity.controller;

import com.example.sistemablogspringbootapirest.configuracionSecurity.DTO.LoginDTO;
import com.example.sistemablogspringbootapirest.configuracionSecurity.DTO.RegistroUsuarioDTO;
import com.example.sistemablogspringbootapirest.configuracionSecurity.model.Rol;
import com.example.sistemablogspringbootapirest.configuracionSecurity.model.Usuario;
import com.example.sistemablogspringbootapirest.configuracionSecurity.repository.RolRepository;
import com.example.sistemablogspringbootapirest.configuracionSecurity.repository.UsuarioRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UsuarioRepository usuarioRepositorio;
    
    @Autowired
    private RolRepository rolRepositorio;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
        
    @PostMapping("/iniciarSesion")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDTO){
		
        Authentication authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
		
	SecurityContextHolder.getContext().setAuthentication(authentication);
		
	//obtenemos el token del jwtTokenProvider
	//String token = jwtTokenProvider.generarToken(authentication);
		
	return new ResponseEntity<>("Ha iniciado sesion con exito!", HttpStatus.OK);
	}
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroUsuarioDTO registroDTO){
	if(usuarioRepositorio.existsByUsername(registroDTO.getUsername())) {
		return new ResponseEntity<>("Ese nombre de usuario ya existe",HttpStatus.BAD_REQUEST);
	}
        
        if(usuarioRepositorio.existsByEmail(registroDTO.getEmail())) {
		return new ResponseEntity<>("Ese email de usuario ya existe",HttpStatus.BAD_REQUEST);
	}
        
        Usuario usuario = new Usuario();
	usuario.setNombre(registroDTO.getNombre());
	usuario.setUsername(registroDTO.getUsername());
	usuario.setEmail(registroDTO.getEmail());
	usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
        
        Rol roles = rolRepositorio.findByNombre("ROLE_ADMIN").get();
	usuario.setRoles(Collections.singleton(roles));
		
	usuarioRepositorio.save(usuario);
        
	return new ResponseEntity<>("Usuario registrado exitosamente",HttpStatus.OK);
        
    }
    
}

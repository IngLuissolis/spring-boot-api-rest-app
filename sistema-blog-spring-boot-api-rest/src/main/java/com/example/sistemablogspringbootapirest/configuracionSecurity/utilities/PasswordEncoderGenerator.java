/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemablogspringbootapirest.configuracionSecurity.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author eduso
 */
public class PasswordEncoderGenerator {
    
    public static void main(String[] args) {

        //metodo que se utiliza para codificar password, en este caso el nombre del password
        //es "password" y el mismo codificado se imprime por pantalla
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	System.out.println(passwordEncoder.encode("password"));
        
        //password codificado generado: $2a$10$Jm2FGNBONTSuGsfjipjURu78325uTK6OMn2hhEcxPYkW7uBHQF7vu
		
    }
    
}

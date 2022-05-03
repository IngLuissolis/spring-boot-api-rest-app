/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemablogspringbootapirest.excepcions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class BlogAppException extends RuntimeException{
    
    private static final Long serialVersionUID = 1L;
    
    private HttpStatus estado;
    private String mensaje;

    public BlogAppException(HttpStatus estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }
    
    public BlogAppException(HttpStatus estado, String mensaje, String mensaje1) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.mensaje = mensaje1;
    }

    public BlogAppException() {
    }
    
    
}

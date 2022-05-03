/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemablogspringbootapirest.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.*;

@Getter @Setter
public class ComentarioDTO {
    
    private Long id;
    @NotEmpty (message="El nombre no debe ser vacio o nulo")
    private String nombre;
    @NotEmpty (message="El email no debe ser vacio o nulo")
    @Email
    private String email;
    @NotEmpty (message="El cuerpo no debe ser vacio o nulo")
    @Size (min=5, message="El cuerpo del comentario deberia tener al menos 5 caracteres")
    private String cuerpo;

    public ComentarioDTO() {
    }

    public ComentarioDTO(Long id, String nombre, String email, String cuerpo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.cuerpo = cuerpo;
    }
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemablogspringbootapirest.DTO;

import com.example.sistemablogspringbootapirest.model.Comentario;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PublicacionDTO {
    
    private Long id;
    @NotEmpty
    @Size(min=2, message="El titulo de la publicacion deberia tener al menos 2 caracteres")
    private String titulo;
    @NotEmpty
    @Size(min=5, message="El titulo de la publicacion deberia tener al menos 5 caracteres")
    private String descripcion;
    @NotEmpty
    private String contenido;
    
    private Set<Comentario> comentarios;

    public PublicacionDTO() {
    }

    public PublicacionDTO(Long id, String titulo, String descripcion, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
    }
     
}

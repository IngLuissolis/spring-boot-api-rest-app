/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemablogspringbootapirest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.*;

@Getter @Setter
@Entity
//uniqueConstraints indica que en la columna titulo no puede tener dos titulos con el mismo valor
@Table(name = "publicaciones", uniqueConstraints = {@UniqueConstraint(columnNames ={"titulo"})})
public class Publicacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "titulo", nullable = false)
    private String titulo;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "contenido", nullable = false)
    private String contenido;

    //JsonBackReference sirve para solucionar recurrencia infinita, ignora serializacion
    @JsonBackReference
    //orphanRemoval, si se elimina publicacion se eliminan comentarios
    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comentario> comentarios = new HashSet<>();
    
    public Publicacion() {
    }

    public Publicacion(Long id, String titulo, String descripcion, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
    }

}

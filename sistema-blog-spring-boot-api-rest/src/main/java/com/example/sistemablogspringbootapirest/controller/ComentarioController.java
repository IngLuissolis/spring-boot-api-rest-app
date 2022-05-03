/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemablogspringbootapirest.controller;

import com.example.sistemablogspringbootapirest.DTO.ComentarioDTO;
import com.example.sistemablogspringbootapirest.service.ComentarioService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class ComentarioController {
    
    @Autowired
    private ComentarioService comentarioService;
    
    @GetMapping("/publicaciones/{publicacionId}/comentarios")
    public List<ComentarioDTO> listarComentariosPorPublicacionId(@PathVariable (value = "publicacionId") Long publicacionId){
        return comentarioService.ObtenerComentariosPorPublicacionId(publicacionId);
    }
    
    @GetMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDTO> obtenerComentarioPorId(@PathVariable (value = "publicacionId") Long publicacionId,
            @PathVariable (value = "comentarioId") Long comentarioId){
        
        ComentarioDTO comentarioDTO = comentarioService.ObtenerComentariosPorId(publicacionId, comentarioId);
        
        return new ResponseEntity<>(comentarioDTO, HttpStatus.OK);
        
    }
    
    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDTO> guardarComentario(@PathVariable (value = "publicacionId") Long publicacionId,
            @Valid @RequestBody ComentarioDTO comentarioDTO){
        
        return new ResponseEntity<>(comentarioService.crearComentario(publicacionId, comentarioDTO), HttpStatus.CREATED);        
    }
    
    @PutMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDTO> actualizarComentariosPorId(@PathVariable (value = "publicacionId") Long publicacionId,
            @PathVariable (value = "comentarioId") Long comentarioId,
            @Valid  @RequestBody ComentarioDTO solicitudComentarioId){
        
        ComentarioDTO comentarioActualizado = comentarioService.actualizarComentariosPorId(publicacionId, comentarioId, solicitudComentarioId);
        
        return new ResponseEntity<>(comentarioActualizado, HttpStatus.OK);       
    }
    
    @DeleteMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<String> eliminarComentarioPorId(@PathVariable (value = "publicacionId") Long publicacionId,
            @PathVariable (value = "comentarioId") Long comentarioId){
        
        comentarioService.eliminarComentarioPorId(publicacionId, comentarioId);
        
        return new ResponseEntity<>("comentario eliminado con exito!", HttpStatus.OK);
    }
    
}

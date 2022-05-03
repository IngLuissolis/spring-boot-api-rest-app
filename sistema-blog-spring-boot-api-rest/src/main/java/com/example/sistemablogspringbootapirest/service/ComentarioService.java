/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.sistemablogspringbootapirest.service;

import com.example.sistemablogspringbootapirest.DTO.ComentarioDTO;
import java.util.List;


public interface ComentarioService {
    
    public ComentarioDTO crearComentario(Long publicacionId, ComentarioDTO comentarioDTO);
    
    public List<ComentarioDTO> ObtenerComentariosPorPublicacionId(Long publicacionId);
    
    public ComentarioDTO ObtenerComentariosPorId(Long publicacionId, Long comentarioId);
    
    public ComentarioDTO actualizarComentariosPorId(Long publicacionId, Long comentarioId, ComentarioDTO solicitudComentarioId);
    
    public void eliminarComentarioPorId(Long publicacionId, Long comentarioId);
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.sistemablogspringbootapirest.service;

import com.example.sistemablogspringbootapirest.DTO.PublicacionDTO;
import com.example.sistemablogspringbootapirest.DTO.PublicacionRespuesta;
import java.util.List;

/**
 *
 * @author eduso
 */
public interface PublicacionService {
    
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
    
    public PublicacionRespuesta obtenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);
    
    public PublicacionDTO obtenerPublicacionPorId(Long id);
    
    public PublicacionDTO actualizarPublicacionPorId(PublicacionDTO publicacionDTO, Long id);
    
    public void eliminarPublicacion(Long id);
    
}

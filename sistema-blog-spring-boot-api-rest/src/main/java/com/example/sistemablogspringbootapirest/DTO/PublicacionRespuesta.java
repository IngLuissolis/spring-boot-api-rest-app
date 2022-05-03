/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemablogspringbootapirest.DTO;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PublicacionRespuesta {
    
    private List<PublicacionDTO> contenido;
    private int numeroPagina;
    private int medidaPagina;
    private Long totalElementos;
    private int totalPaginas;
    private boolean ultima;

    public PublicacionRespuesta() {
    }
    
}

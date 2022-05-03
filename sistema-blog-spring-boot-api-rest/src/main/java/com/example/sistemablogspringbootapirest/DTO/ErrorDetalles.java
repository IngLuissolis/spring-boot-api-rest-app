/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemablogspringbootapirest.DTO;

import java.util.Date;
import lombok.*;

/**
 *
 * @author eduso
 */
@Getter @Setter
public class ErrorDetalles {
    
    private Date marcaDeTiempo;
    private String mensaje;
    private String detalles;

    public ErrorDetalles() {
    }

    public ErrorDetalles(Date marcaDeTiempo, String mensaje, String detalles) {
        this.marcaDeTiempo = marcaDeTiempo;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }
    
    
    
}

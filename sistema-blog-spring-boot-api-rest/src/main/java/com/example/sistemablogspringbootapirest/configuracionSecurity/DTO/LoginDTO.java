/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemablogspringbootapirest.configuracionSecurity.DTO;

import lombok.*;

@Getter @Setter
public class LoginDTO {
    
    private String usernameOrEmail;
    private String password;    
    
}

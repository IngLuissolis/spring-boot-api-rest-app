/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemablogspringbootapirest.service;

import com.example.sistemablogspringbootapirest.DTO.ComentarioDTO;
import com.example.sistemablogspringbootapirest.excepcions.BlogAppException;
import com.example.sistemablogspringbootapirest.excepcions.ResourceNotFound;
import com.example.sistemablogspringbootapirest.model.Comentario;
import com.example.sistemablogspringbootapirest.model.Publicacion;
import com.example.sistemablogspringbootapirest.repository.ComentarioRepository;
import com.example.sistemablogspringbootapirest.repository.PublicacionRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServiceImpl implements ComentarioService{
    
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ComentarioRepository comentarioRepository;
    
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public ComentarioDTO crearComentario(Long publicacionId, ComentarioDTO comentarioDTO) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Comentario comentario = mapearEntidad(comentarioDTO);
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow(()-> 
            new ResourceNotFound("Publicacion","id", publicacionId));
        
        comentario.setPublicacion(publicacion);
        Comentario nuevoComentario = comentarioRepository.save(comentario);
        
        return mapearDTO(nuevoComentario);
    }


    @Override
    public List<ComentarioDTO> ObtenerComentariosPorPublicacionId(Long publicacionId) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        List<Comentario> comentarios = comentarioRepository.findByPublicacionId(publicacionId);
        
        return comentarios.stream().map(comentario ->
            mapearDTO(comentario)).collect(Collectors.toList());
                
    }

    @Override
    public ComentarioDTO ObtenerComentariosPorId(Long publicacionId, Long comentarioId) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(()-> new ResourceNotFound("Publicacion","id", publicacionId));
        
        Comentario comentario = comentarioRepository.findById(comentarioId)
            .orElseThrow(()-> new ResourceNotFound("Comentario","id", comentarioId));
        
        if(!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }
        
        return mapearDTO(comentario);
    }

    @Override
    public ComentarioDTO actualizarComentariosPorId(Long publicacionId, Long comentarioId, ComentarioDTO solicitudComentarioId) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(()-> new ResourceNotFound("Publicacion","id", publicacionId));
        
        Comentario comentario = comentarioRepository.findById(comentarioId)
            .orElseThrow(()-> new ResourceNotFound("Comentario","id", comentarioId));
        
        if(!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }
        
        comentario.setNombre(solicitudComentarioId.getNombre());
        comentario.setEmail(solicitudComentarioId.getEmail());
        comentario.setCuerpo(solicitudComentarioId.getCuerpo());
        
        Comentario comentarioActualizado = comentarioRepository.save(comentario);
        
        return mapearDTO(comentarioActualizado);
    }

    @Override
    public void eliminarComentarioPorId(Long publicacionId, Long comentarioId) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(()-> new ResourceNotFound("Publicacion","id", publicacionId));
        
        Comentario comentario = comentarioRepository.findById(comentarioId)
            .orElseThrow(()-> new ResourceNotFound("Comentario","id", comentarioId));
        
        if(!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }
        
        comentarioRepository.delete(comentario);
    }
    
            //Metodo que convierte de Entidad a DTO
    private ComentarioDTO mapearDTO(Comentario comentario){
        
        //ComentarioDTO comentarioDTO = new ComentarioDTO();
        //setea los valores objeto DTO con los valores de objeto comentario
        //comentarioDTO.setId(comentario.getId());
        //comentarioDTO.setNombre(comentario.getNombre());
        //comentarioDTO.setEmail(comentario.getEmail());
        //comentarioDTO.setCuerpo(comentario.getCuerpo());
        
        ComentarioDTO comentarioDTO = modelMapper.map(comentario, ComentarioDTO.class);
        
        return comentarioDTO;
    }
    
    ////Metodo que convierte de DTO a entidad
        private Comentario mapearEntidad(ComentarioDTO comentarioDTO){
        
        //Comentario comentario = new Comentario();
        //setea los valores nuevo comentario con los valores de objeto DTO
        //comentario.setId(comentarioDTO.getId());
        //comentario.setNombre(comentarioDTO.getNombre());
        //comentario.setCuerpo(comentarioDTO.getCuerpo());
        //comentario.setEmail(comentarioDTO.getEmail());
        
        Comentario comentario = modelMapper.map(comentarioDTO, Comentario.class);
        
        return comentario;
    }
    
}

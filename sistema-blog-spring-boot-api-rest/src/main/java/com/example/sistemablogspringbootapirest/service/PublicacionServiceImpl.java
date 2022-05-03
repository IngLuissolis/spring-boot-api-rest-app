/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemablogspringbootapirest.service;

import com.example.sistemablogspringbootapirest.DTO.PublicacionDTO;
import com.example.sistemablogspringbootapirest.DTO.PublicacionRespuesta;
import com.example.sistemablogspringbootapirest.excepcions.ResourceNotFound;
import com.example.sistemablogspringbootapirest.model.Publicacion;
import com.example.sistemablogspringbootapirest.repository.PublicacionRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PublicacionServiceImpl implements PublicacionService{

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    PublicacionRepository publicacionRepository;
    
    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        
        //Convertimos de DTO a entidad
        
        //Crea nuevo objeto publicacion
        Publicacion publicacion = mapearEntidad(publicacionDTO);
        
        //guarda el objeto DTO publicacion en el repositorio - persistencia en la base de datos
        Publicacion nuevaPublicacion = publicacionRepository.save(publicacion);
        
        //Convertimos de entidad a DTO
        
        //Crea nuevo objeto DTO publicacion
        PublicacionDTO publicacionResponse = mapearDTO(nuevaPublicacion);
        
        return publicacionResponse;
    }

    @Override
    public PublicacionRespuesta obtenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
        
        //Si sortDir es igual a la direccion ascendente y con un nombre se ordena en forma ascendente por el nombre que le paso
        //sino ordena en forma descendente
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
        PageRequest pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);
        
        Page<Publicacion> publicaciones = publicacionRepository.findAll(pageable);
        
        List<Publicacion> listaDePublicaciones = publicaciones.getContent();
        
        List<PublicacionDTO> contenido = listaDePublicaciones.stream().map(publicacion ->
        mapearDTO(publicacion)).collect(Collectors.toList());
        
        PublicacionRespuesta publicacionRespuesta = new PublicacionRespuesta();
        publicacionRespuesta.setContenido(contenido);
        publicacionRespuesta.setNumeroPagina(publicaciones.getNumber());
        publicacionRespuesta.setMedidaPagina(publicaciones.getSize());
        publicacionRespuesta.setTotalElementos(publicaciones.getTotalElements());
        publicacionRespuesta.setTotalPaginas(publicaciones.getTotalPages());
        publicacionRespuesta.setUltima(publicaciones.isLast());
        
        return publicacionRespuesta;
    }

    @Override
    public PublicacionDTO obtenerPublicacionPorId(Long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(()-> 
        new ResourceNotFound("Publicacion","id", id));
        return mapearDTO(publicacion);
    }

    @Override
    public PublicacionDTO actualizarPublicacionPorId(PublicacionDTO publicacionDTO, Long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(()-> 
        new ResourceNotFound("Publicacion","id", id));
        
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setContenido(publicacionDTO.getContenido());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        
        Publicacion publicacionActualizada = publicacionRepository.save(publicacion);
        
        return mapearDTO(publicacionActualizada);
    }

    @Override
    public void eliminarPublicacion(Long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(()-> 
        new ResourceNotFound("Publicacion","id", id)); 
        
        publicacionRepository.delete(publicacion);
    }
    
    //Metodo que convierte de Entidad a DTO
    private PublicacionDTO mapearDTO(Publicacion publicacion){
        
        //PublicacionDTO publicacionDTO = new PublicacionDTO();
        //setea los valores objeto DTO con los valores de objeto publicacion
        //publicacionDTO.setId(publicacion.getId());
        //publicacionDTO.setTitulo(publicacion.getTitulo());
        //publicacionDTO.setContenido(publicacion.getContenido());
        //publicacionDTO.setDescripcion(publicacion.getDescripcion());
        
        PublicacionDTO publicacionDTO = modelMapper.map(publicacion, PublicacionDTO.class);
        
        return publicacionDTO;
    }
    
    //Metodo que convierte de DTO a entidad
        private Publicacion mapearEntidad(PublicacionDTO publicacionDTO){
        
        //Publicacion publicacion = new Publicacion();
        //setea los valores nueva publicacion con los valores de objeto DTO 
        //publicacion.setTitulo(publicacionDTO.getTitulo());
        //publicacion.setContenido(publicacionDTO.getContenido());
        //publicacion.setDescripcion(publicacionDTO.getDescripcion());
        
        Publicacion publicacion = modelMapper.map(publicacionDTO, Publicacion.class);
        
        return publicacion;
    }
    
}

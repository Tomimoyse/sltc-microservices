package com.sltc.contenedores.service;

import com.sltc.contenedores.dto.ContenedorRequestDTO;
import com.sltc.contenedores.dto.ContenedorResponseDTO;

import java.util.List;

public interface ContenedorService {
    
    ContenedorResponseDTO createContenedor(ContenedorRequestDTO requestDTO);
    
    ContenedorResponseDTO getContenedorById(Long id);
    
    List<ContenedorResponseDTO> getAllContenedores();
    
    ContenedorResponseDTO updateContenedor(Long id, ContenedorRequestDTO requestDTO);
    
    void deleteContenedor(Long id);
    
    List<ContenedorResponseDTO> getContenedoresByCliente(Long idCliente);
    
    List<ContenedorResponseDTO> getContenedoresByEstado(String estado);
}

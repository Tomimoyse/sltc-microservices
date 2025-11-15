package com.sltc.contenedores.service;

import com.sltc.contenedores.dto.ContenedorRequestDTO;
import com.sltc.contenedores.dto.ContenedorResponseDTO;
import com.sltc.contenedores.exception.ResourceNotFoundException;
import com.sltc.contenedores.model.Contenedor;
import com.sltc.contenedores.repository.ContenedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContenedorServiceImpl implements ContenedorService {

    private final ContenedorRepository contenedorRepository;

    @Override
    @Transactional
    public ContenedorResponseDTO createContenedor(ContenedorRequestDTO requestDTO) {
        Contenedor contenedor = mapToEntity(requestDTO);
        Contenedor savedContenedor = contenedorRepository.save(contenedor);
        return mapToDTO(savedContenedor);
    }

    @Override
    @Transactional(readOnly = true)
    public ContenedorResponseDTO getContenedorById(Long id) {
        Contenedor contenedor = contenedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contenedor no encontrado con id: " + id));
        return mapToDTO(contenedor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContenedorResponseDTO> getAllContenedores() {
        return contenedorRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ContenedorResponseDTO updateContenedor(Long id, ContenedorRequestDTO requestDTO) {
        Contenedor contenedor = contenedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contenedor no encontrado con id: " + id));
        
        contenedor.setNumeroContenedor(requestDTO.getNumeroContenedor());
        contenedor.setTipo(requestDTO.getTipo());
        contenedor.setPeso(requestDTO.getPeso());
        contenedor.setVolumen(requestDTO.getVolumen());
        contenedor.setEstado(requestDTO.getEstado());
        contenedor.setIdCliente(requestDTO.getIdCliente());
        
        Contenedor updatedContenedor = contenedorRepository.save(contenedor);
        return mapToDTO(updatedContenedor);
    }

    @Override
    @Transactional
    public void deleteContenedor(Long id) {
        if (!contenedorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contenedor no encontrado con id: " + id);
        }
        contenedorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContenedorResponseDTO> getContenedoresByCliente(Long idCliente) {
        return contenedorRepository.findByIdCliente(idCliente).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContenedorResponseDTO> getContenedoresByEstado(String estado) {
        return contenedorRepository.findByEstado(estado).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private Contenedor mapToEntity(ContenedorRequestDTO dto) {
        Contenedor contenedor = new Contenedor();
        contenedor.setNumeroContenedor(dto.getNumeroContenedor());
        contenedor.setTipo(dto.getTipo());
        contenedor.setPeso(dto.getPeso());
        contenedor.setVolumen(dto.getVolumen());
        contenedor.setEstado(dto.getEstado());
        contenedor.setIdCliente(dto.getIdCliente());
        return contenedor;
    }

    private ContenedorResponseDTO mapToDTO(Contenedor contenedor) {
        ContenedorResponseDTO dto = new ContenedorResponseDTO();
        dto.setId(contenedor.getId());
        dto.setNumeroContenedor(contenedor.getNumeroContenedor());
        dto.setTipo(contenedor.getTipo());
        dto.setPeso(contenedor.getPeso());
        dto.setVolumen(contenedor.getVolumen());
        dto.setEstado(contenedor.getEstado());
        dto.setIdCliente(contenedor.getIdCliente());
        dto.setCreatedAt(contenedor.getCreatedAt());
        dto.setUpdatedAt(contenedor.getUpdatedAt());
        return dto;
    }
}

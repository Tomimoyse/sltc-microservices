package com.sltc.contenedores.repository;

import com.sltc.contenedores.model.Contenedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContenedorRepository extends JpaRepository<Contenedor, Long> {
    
    Optional<Contenedor> findByNumeroContenedor(String numeroContenedor);
    
    List<Contenedor> findByIdCliente(Long idCliente);
    
    List<Contenedor> findByEstado(String estado);
}

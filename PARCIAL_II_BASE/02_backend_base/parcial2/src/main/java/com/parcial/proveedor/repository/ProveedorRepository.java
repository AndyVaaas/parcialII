package com.parcial.proveedor.repository;

import com.parcial.proveedor.entity.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    // =================================================================
    // TODO 1 (MOSTRAR): declare aqui el metodo derivado de Spring Data
    // que devuelva UNICAMENTE los proveedores con ESTADO = true,
    // ordenados por ID_PROVEEDOR de forma descendente.
    // =================================================================
    List<Proveedor> findByEstadoTrueOrderByIdProveedorDesc();

}
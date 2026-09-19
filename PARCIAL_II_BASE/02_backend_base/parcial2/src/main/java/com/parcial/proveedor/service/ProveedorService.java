package com.parcial.proveedor.service;

import com.parcial.proveedor.dto.ProveedorDTO;
import com.parcial.proveedor.entity.Proveedor;
import com.parcial.proveedor.repository.ProveedorRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // =================================================================
    // METODO DE EJEMPLO YA RESUELTO (no se califica).
    // Devuelve TODOS los proveedores, activos y anulados.
    // Uselo como guia para construir los metodos que faltan.
    // =================================================================
    public List<ProveedorDTO> findAll() {
        return proveedorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // =================================================================
    // TODO 1 - MOSTRAR
    // Devuelva la lista de proveedores ACTIVOS (estado = true),
    // ordenados por idProveedor descendente, convertidos a ProveedorDTO.
    // Use el metodo que declaro en ProveedorRepository.
    // =================================================================
    public List<ProveedorDTO> mostrarActivos() {
        return proveedorRepository.findByEstadoTrueOrderByIdProveedorDesc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // =================================================================
    // TODO 2 - GUARDAR
    // Inserte un nuevo proveedor en la base de datos.
    // El registro nuevo SIEMPRE debe quedar con estado = true.
    // Devuelva el ProveedorDTO ya guardado (con su idProveedor generado).
    // =================================================================
    public ProveedorDTO crearProveedor(ProveedorDTO dto) {
        Proveedor proveedor = convertToEntity(dto);
        Proveedor guardado = proveedorRepository.save(proveedor);
        return convertToDTO(guardado);
    }

    // =================================================================
    // TODO 3 - MODIFICAR
    // Busque el proveedor por su idProveedor. Si no existe, lance
    // RuntimeException con el mensaje: "El proveedor no existe con id " + idProveedor
    // Si existe, actualice nombre, nit, telefono y direccion, guarde y
    // devuelva el ProveedorDTO actualizado.
    // OJO: no debe cambiar el estado del registro.
    // =================================================================
    public ProveedorDTO modificarProveedor(Integer idProveedor, ProveedorDTO dto) {
        Proveedor p = proveedorRepository.findById(idProveedor)
                .orElseThrow(() -> new RuntimeException("El proveedor no existe con id " + idProveedor));

        p.setNombre(dto.getNombre());
        p.setNit(dto.getNit());
        p.setTelefono(dto.getTelefono());
        p.setDireccion(dto.getDireccion());

        Proveedor actualizado = proveedorRepository.save(p);
        return convertToDTO(actualizado);
    }

    // =================================================================
    // TODO 4 - ANULAR (borrado logico)
    // Busque el proveedor por su idProveedor. Si no existe, lance
    // RuntimeException con el mensaje: "El proveedor no existe con id " + idProveedor
    // Si existe, cambie su estado a false, guarde y devuelva el DTO.
    // NO debe borrar fisicamente el registro de la tabla.
    // =================================================================
    public ProveedorDTO anularProveedor(Integer idProveedor) {
        Proveedor p = proveedorRepository.findById(idProveedor)
                .orElseThrow(() -> new RuntimeException("El proveedor no existe con id " + idProveedor));

        p.setEstado(false);

        Proveedor anulado = proveedorRepository.save(p);
        return convertToDTO(anulado);
    }

    // =================================================================
    // METODOS DE CONVERSION YA RESUELTOS - NO MODIFICAR
    // =================================================================
    private ProveedorDTO convertToDTO(Proveedor p) {
        ProveedorDTO dto = new ProveedorDTO();
        dto.setIdProveedor(p.getIdProveedor());
        dto.setEstado(p.getEstado());
        dto.setNombre(p.getNombre());
        dto.setNit(p.getNit());
        dto.setTelefono(p.getTelefono());
        dto.setDireccion(p.getDireccion());
        return dto;
    }

    private Proveedor convertToEntity(ProveedorDTO dto) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(dto.getNombre());
        proveedor.setNit(dto.getNit());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setDireccion(dto.getDireccion());
        proveedor.setEstado(true);
        return proveedor;
    }

}
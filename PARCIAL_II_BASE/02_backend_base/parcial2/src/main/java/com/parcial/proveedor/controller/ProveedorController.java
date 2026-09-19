package com.parcial.proveedor.controller;

import com.parcial.proveedor.dto.MessageResponse;
import com.parcial.proveedor.dto.ProveedorDTO;
import com.parcial.proveedor.service.ProveedorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    // =================================================================
    // ENDPOINT DE EJEMPLO YA RESUELTO (no se califica).
    // GET  http://localhost:8080/proveedores
    // =================================================================
    @GetMapping
    public List<ProveedorDTO> getAllProveedores() {
        return proveedorService.findAll();
    }

    // =================================================================
    // TODO 1 - MOSTRAR
    // Verbo y ruta:  GET  /proveedores/mostrarActivos
    // Debe devolver: List<ProveedorDTO> con los proveedores activos.
    // Agregue la anotacion que corresponde y llame al servicio.
    // =================================================================
    @GetMapping("/mostrarActivos")
    public List<ProveedorDTO> mostrarActivos() {
        return proveedorService.mostrarActivos();
    }

    // =================================================================
    // TODO 2 - GUARDAR
    // Verbo y ruta:  POST  /proveedores
    // Recibe el ProveedorDTO en el cuerpo de la peticion.
    // Si todo sale bien responde 200 con:
    //      new MessageResponse("Proveedor creado con exito")
    // Si ocurre un error responde 400 (HttpStatus.BAD_REQUEST) con:
    //      new MessageResponse("Error al crear el proveedor")
    // Use try / catch.
    // =================================================================
    @PostMapping
    public ResponseEntity<MessageResponse> crearProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        try {
            proveedorService.crearProveedor(proveedorDTO);
            return ResponseEntity.ok(new MessageResponse("Proveedor creado con exito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error al crear el proveedor"));
        }
    }

    // =================================================================
    // TODO 3 - MODIFICAR
    // Verbo y ruta:  PUT  /proveedores/{idProveedor}
    // Recibe el id en la ruta y el ProveedorDTO en el cuerpo.
    // Exito -> 200 con "Proveedor actualizado con exito"
    // Error -> 400 con "Error al actualizar el proveedor"
    // =================================================================
    @PutMapping("/{idProveedor}")
    public ResponseEntity<MessageResponse> actualizarProveedor(@PathVariable Integer idProveedor, @RequestBody ProveedorDTO proveedorDTO) {
        try {
            proveedorService.modificarProveedor(idProveedor, proveedorDTO);
            return ResponseEntity.ok(new MessageResponse("Proveedor actualizado con exito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error al actualizar el proveedor"));
        }
    }

    // =================================================================
    // TODO 4 - ANULAR
    // Verbo y ruta:  PUT  /proveedores/anular/{idProveedor}
    // Exito -> 200 con "Proveedor anulado con exito"
    // Error -> 400 con "Error al anular el proveedor"
    // =================================================================
    @PutMapping("/anular/{idProveedor}")
    public ResponseEntity<MessageResponse> anularProveedor(@PathVariable Integer idProveedor) {
        try {
            proveedorService.anularProveedor(idProveedor);
            return ResponseEntity.ok(new MessageResponse("Proveedor anulado con exito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error al anular el proveedor"));
        }
    }

}
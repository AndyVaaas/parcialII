import { useState, useEffect, type ChangeEvent, type FormEvent } from "react";
import axios from "axios";
import {
  listarProveedoresActivos,
  crearProveedor,
  actualizarProveedor,
  anularProveedor
} from "../services/proveedorServices";
import type { Proveedor } from "../types/proveedor";

// 12. Limpiar/Estado inicial
const formInicial: Proveedor = {
  idProveedor: null,
  nombre: "",
  nit: "",
  telefono: "",
  direccion: ""
};

export default function Proveedores() {
  // 14. Estados de la vista
  const [proveedores, setProveedores] = useState<Proveedor[]>([]);
  const [form, setForm] = useState<Proveedor>(formInicial);
  const [modoEdicion, setModoEdicion] = useState(false);
  const [mensaje, setMensaje] = useState("");

  // 22. Obtener mensajes de error
  const obtenerMensajeError = (error: unknown): string => {
    if (axios.isAxiosError(error)) {
      return error.response?.data?.mensaje ?? error.response?.data?.message ?? error.message;
    }
    if (error instanceof Error) {
      return error.message;
    }
    return "Ocurrió un error inesperado";
  };

  // 15. Cargar proveedores desde el backend
  const cargarProveedores = async () => {
    try {
      const respuesta = await listarProveedoresActivos();
      setProveedores(respuesta.data);
    } catch (error) {
      console.error("Error al listar proveedores", error);
    }
  };

  // 16. Cargar datos al inicio
  useEffect(() => {
    cargarProveedores();
  }, []);

  // 16.1. Manejador de cambios en los inputs
  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  // 18. Guardar / Modificar en el backend
  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      if (modoEdicion && form.idProveedor !== null) {
        await actualizarProveedor(form.idProveedor, form);
        setMensaje("Proveedor actualizado correctamente");
      } else {
        await crearProveedor(form);
        setMensaje("Proveedor creado correctamente");
      }
      setForm(formInicial);
      setModoEdicion(false);
      cargarProveedores();
    } catch (error) {
      setMensaje(obtenerMensajeError(error));
      console.error("Error al guardar proveedor", error);
    }
  };

  // 19. Cargar datos para edición
  const handleModificar = (proveedor: Proveedor) => {
    setForm(proveedor);
    setModoEdicion(true);
  };

  // 20. Anular proveedor (borrado lógico)
  const handleAnular = async (idProveedor: number) => {
    const confirmar = window.confirm("¿Seguro que deseas anular este proveedor?");
    if (!confirmar) return;
    try {
      await anularProveedor(idProveedor);
      setMensaje("Proveedor anulado correctamente");
      cargarProveedores();
    } catch (error) {
      setMensaje(obtenerMensajeError(error));
      console.error("Error al anular el proveedor", error);
    }
  };

  return (
    <div style={{ padding: "20px", fontFamily: "sans-serif" }}>
      {/* ENCABEZADO CON CARNÉ */}
      <h2>Parcial II</h2>
      <h3>Andy Estuardo Vásquez Alvarado - 0904 23 15598</h3>
      <hr />

      <h2>{modoEdicion ? "Modificar Proveedor" : "Ingresar Proveedor"}</h2>
      {mensaje && <p style={{ color: "blue", fontWeight: "bold" }}>{mensaje}</p>}

      {/* 21. Formulario */}
      <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", gap: "10px", maxWidth: "400px" }}>
        <div>
          <label htmlFor="nombre">Nombre: </label>
          <input
            type="text"
            id="nombre"
            name="nombre"
            value={form.nombre}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="nit">NIT: </label>
          <input
            type="text"
            id="nit"
            name="nit"
            value={form.nit}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="telefono">Teléfono: </label>
          <input
            type="text"
            id="telefono"
            name="telefono"
            value={form.telefono}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="direccion">Dirección: </label>
          <input
            type="text"
            id="direccion"
            name="direccion"
            value={form.direccion}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <button type="submit">{modoEdicion ? "Actualizar" : "Guardar"}</button>
          {modoEdicion && (
            <button
              type="button"
              onClick={() => {
                setForm(formInicial);
                setModoEdicion(false);
              }}
              style={{ marginLeft: "10px" }}
            >
              Cancelar
            </button>
          )}
        </div>
      </form>

      <h2>Listado de Proveedores Activos</h2>
      {/* Tabla de Listado */}
      <table border={1} cellPadding={8} style={{ borderCollapse: "collapse", width: "100%" }}>
        <thead>
          <tr>
            <th>Nombre</th>
            <th>NIT</th>
            <th>Teléfono</th>
            <th>Dirección</th>
            <th>Modificar</th>
            <th>Eliminar</th>
          </tr>
        </thead>
        <tbody>
          {proveedores.map((proveedor) => (
            <tr key={proveedor.idProveedor}>
              <td>{proveedor.nombre}</td>
              <td>{proveedor.nit}</td>
              <td>{proveedor.telefono}</td>
              <td>{proveedor.direccion}</td>
              <td>
                <button onClick={() => handleModificar(proveedor)}>Modificar</button>
              </td>
              <td>
                <button
                  onClick={() =>
                    proveedor.idProveedor !== null && handleAnular(proveedor.idProveedor)
                  }
                >
                  Eliminar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

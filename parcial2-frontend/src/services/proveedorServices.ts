import api from "../api/axios";
import type { Proveedor, ApiMensaje } from "../types/proveedor";

export const listarProveedoresActivos = () =>
  api.get<Proveedor[]>("/proveedores/mostrarActivos");

export const crearProveedor = (data: Omit<Proveedor, "idProveedor">) =>
  api.post<ApiMensaje>("/proveedores", data);

export const actualizarProveedor = (
  id: number,
  data: Omit<Proveedor, "idProveedor">
) => api.put<ApiMensaje>(`/proveedores/${id}`, data);

export const anularProveedor = (id: number) =>
  api.put<ApiMensaje>(`/proveedores/anular/${id}`);
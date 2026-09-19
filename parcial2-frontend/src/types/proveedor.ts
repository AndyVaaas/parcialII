export interface Proveedor {
  idProveedor: number | null;
  nombre: string;
  nit: string;
  telefono: string;
  direccion: string;
  estado?: boolean;
}

export interface ApiMensaje {
  mensaje: string;
}


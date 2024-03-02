package construcciones;

import construcciones.dao.*;
import construcciones.servicios.ProyectosAPIREST;


public class Servidor {

    public static void main(String[] args) {

        ProyectosAPIREST api = new ProyectosAPIREST(
                new ProyectoDAO(),
                new ClienteDAO(),
                new AvanceDAO(),
                new MaterialDAO(),
                new AvanceMaterialDAO(),
                new ProveedorDAO());
    }
}

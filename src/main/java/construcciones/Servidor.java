package construcciones;

import construcciones.dao.*;
import construcciones.servicios.APIREST;


public class Servidor {

    public static void main(String[] args) {

        APIREST api = new APIREST(
                new ProyectoDAO(),
                new ClienteDAO(),
                new AvanceDAO(),
                new MaterialDAO(),
                new AvanceMaterialDAO(),
                new ProveedorDAO());
    }
}

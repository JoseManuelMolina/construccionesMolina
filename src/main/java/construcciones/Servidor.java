package construcciones;

import construcciones.dao.APIKeyDAO;
import construcciones.dao.ProyectoDAO;
import construcciones.entidades.Proyecto;
import construcciones.servicios.APIKeyAPIREST;
import construcciones.servicios.ProyectosAPIREST;


public class Servidor {

    public static void main(String[] args) {

        ProyectosAPIREST api = new ProyectosAPIREST(
                new ProyectoDAO(),
                new APIKeyDAO()
                );
    }
}

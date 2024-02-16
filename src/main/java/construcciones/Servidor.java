package construcciones;

import construcciones.dao.APIKeyDAO;
import construcciones.dao.ClienteDAO;
import construcciones.dao.ProyectoDAO;
import construcciones.servicios.ProyectosAPIREST;


public class Servidor {

    public static void main(String[] args) {

        ProyectosAPIREST api = new ProyectosAPIREST(
                new ProyectoDAO(),
                new APIKeyDAO(),
                new ClienteDAO());
    }
}

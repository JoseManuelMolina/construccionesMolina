package construcciones.servicios;

import com.google.gson.Gson;
import construcciones.dao.APIKeyDAO;
import construcciones.dao.APIKeyDAOInterface;
import construcciones.entidades.APIKey;
import spark.Spark;

import java.util.List;

public class APIKeyAPIREST {

    private APIKeyDAOInterface dao;

    private Gson gson = new Gson();

    public APIKeyAPIREST(APIKeyDAOInterface implementacion) {
        Spark.port(8080);
        dao = implementacion;

        Spark.before((request, response) -> {
            response.type("application/json");
        });

        Spark.get("/keys", (request, response) -> {
            List<APIKey> keys = dao.devolverTodas();
            return gson.toJson(keys);
        });
    }
}

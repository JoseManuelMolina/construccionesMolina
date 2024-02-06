package construcciones.dao;

import construcciones.entidades.APIKey;

import java.util.List;

public interface APIKeyDAOInterface {

    List<APIKey> devolverTodas();
    List<APIKey> devolverTodas(int pagina, int tamaño);
    APIKey buscarId(Long id);
    APIKey buscarPorKey(String key);
    APIKey usarKey(Long idKey);
    APIKey activarKey(Long idKey);
    boolean activada(Long idKey);
    APIKey create();
    APIKey update();
    boolean delete(APIKey key);
}

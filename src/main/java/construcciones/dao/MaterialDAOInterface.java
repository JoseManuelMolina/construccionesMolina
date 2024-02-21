package construcciones.dao;

import construcciones.entidades.Material;

import java.math.BigDecimal;
import java.util.List;

public interface MaterialDAOInterface {

    List<Material> devolverTodos();
    List<Material> devolverTodos(int pag, int tam);
    Material buscarPorId(Long id);
    int cantidadDeID(Long id);
    List<Material> buscarPorNombre(String nombre);
    List<Material> buscarPorCosteSuperior(BigDecimal coste);
    List<Material> buscarPorCantidadSuperior(int cantidad);
    Material create(Material material);
    Material update(Material material);
    boolean delete(Long id);

}

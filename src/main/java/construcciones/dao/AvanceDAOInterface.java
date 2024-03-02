package construcciones.dao;

import construcciones.entidades.Avance;
import construcciones.entidades.Material;

import java.util.Date;
import java.util.List;

public interface AvanceDAOInterface {

    List<Avance> devolverTodos();
    List<Avance> devolverTodos(int pag, int tam);
    Avance buscarPorId(Long id);
    List<Avance> buscarPorDesc(String desc);
    List<Avance> buscarPorFechaPosterior(Date fecha);

    List<Avance> buscarPorPorcentaje(int porcentaje);
    List<Material> obtenerMaterialesDeAvance(Long id);

    Avance create(Avance avance);
    Avance update(Avance avance);

    boolean delete(Long id);

}

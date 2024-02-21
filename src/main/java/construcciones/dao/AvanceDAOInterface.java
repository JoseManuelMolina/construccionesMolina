package construcciones.dao;

import construcciones.entidades.Avance;

import java.util.Date;
import java.util.List;

public interface AvanceDAOInterface {

    List<Avance> devolverTodos();
    List<Avance> devolverTodos(int pag, int tam);
    Avance buscarPorId(Long id);
    List<Avance> buscarPorDesc(String desc);
    List<Avance> buscarPorFechaPosterior(Date fecha);

    List<Avance> buscarPorPorcentaje(int porcentaje);

    Avance create(Avance avance);
    Avance update(Avance avance);

    boolean delete(Long id);

}

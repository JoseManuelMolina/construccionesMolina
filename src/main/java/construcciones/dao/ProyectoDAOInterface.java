package construcciones.dao;

import construcciones.dto.ProyectoDTO;
import construcciones.entidades.Proyecto;

import java.util.Date;
import java.util.List;

public interface ProyectoDAOInterface {

    List<Proyecto> devolverTodos();
    List<Proyecto> devolverTodos(int pagina, int tamaño);
    Proyecto devolverMayorPresupuesto();
    Proyecto devolverMenorPresupuesto();
    List<Proyecto> devolverPresupuestoEntre(Double min, Double max);
    List<Proyecto> devolverProyectosDeClienteLike(String cliente);
    List<Proyecto> iniciadosDespuesDe(Date fecha);
    List<Proyecto> iniciadosAntesDe(Date fecha);
    List<Proyecto> devolverTodosCategoria(List<String> categorias);
    Double totalProyectos();
    Long numeroProyectos();
    Long numeroCategorias();
    Proyecto buscarPorId(Long id);
    List<Proyecto> buscarPorNombre(String nombre);
    Double mediaPresupuestos();
    Double mediaPresupuestoCategoria(String categoria);

    Proyecto create(Proyecto proyecto);
    Proyecto update(Proyecto proyecto);

    boolean deleteById(Long id);

    boolean deleteAll();

    List<ProyectoDTO> listarProyectosConCondicion(String condicion);
}

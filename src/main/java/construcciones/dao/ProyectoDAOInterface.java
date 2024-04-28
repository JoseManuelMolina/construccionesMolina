package construcciones.dao;

import construcciones.dto.ProyectoCompletoDTO;
import construcciones.dto.ProyectoDTO;
import construcciones.entidades.Avance;
import construcciones.entidades.Proyecto;

import java.sql.Date;
import java.util.List;

public interface ProyectoDAOInterface {

    List<Proyecto> devolverTodos();
    List<Proyecto> devolverTodos(int pagina, int tamaño);

    List<ProyectoDTO> devolverTodosDTO();
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
    List<Avance> obtenerAvancesDeProyecto(Long idProyecto);
    Proyecto create(Proyecto proyecto);
    Proyecto createByDTO(ProyectoDTO proyecto);
    Proyecto update(Proyecto proyecto);

    boolean deleteById(Long id);

    boolean deleteAll();

    List<ProyectoDTO> listarProyectosConCondicion(String condicion);

    List<ProyectoCompletoDTO> devolverTodosCompletos();
}

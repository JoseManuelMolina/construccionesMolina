package construcciones.dao;


import construcciones.entidades.Cliente;
import construcciones.entidades.Proyecto;

import java.util.List;

public interface ClienteDAOInterface {

    List <Cliente> devolverTodos();
    List <Cliente> devolverTodos(int pag, int tam);
    List<Proyecto> devolverProyectos(Long clienteId);
    Cliente devolverMasProyectos();
    Cliente devolverMenosProyectos();

    Long numeroClientes();
    Cliente buscarPorId(Long id);
    List <Cliente> buscarPorRazon(String razon);
    Cliente buscarPorCIF(String cif);

    Cliente create(Cliente cliente);
    Cliente update(Cliente cliente);

    boolean deleteById(Long id);
}

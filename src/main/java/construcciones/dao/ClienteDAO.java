package construcciones.dao;

import construcciones.entidades.Cliente;
import construcciones.entidades.Proyecto;
import construcciones.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.List;

public class ClienteDAO implements ClienteDAOInterface{
    @Override
    public List<Cliente> devolverTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Cliente> todos = session.createQuery("from Cliente", Cliente.class).list();

        return todos;
    }

    @Override
    public List<Cliente> devolverTodos(int pag, int tam) {
        return null;
    }


    public List<Proyecto> devolverProyectos(Long clienteId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Proyecto> query = session.createQuery(
                "SELECT p FROM Proyecto p WHERE p.cliente.id = :clienteId", Proyecto.class);
        query.setParameter("clienteId", clienteId);
        return query.getResultList();
    }

    @Override
    public Cliente devolverMasProyectos() {
        return null;
    }

    @Override
    public Cliente devolverMenosProyectos() {
        return null;
    }

    @Override
    public Long numeroClientes() {
        return null;
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return null;
    }

    @Override
    public List<Cliente> buscarPorRazon(String razon) {
        return null;
    }

    @Override
    public Cliente buscarPorCIF(String cif) {
        return null;
    }

    @Override
    public Cliente create(Cliente cliente) {
        return null;
    }

    @Override
    public Cliente update(Cliente cliente) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}

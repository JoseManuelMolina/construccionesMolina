package construcciones.dao;

import construcciones.entidades.Cliente;
import construcciones.entidades.Proyecto;
import construcciones.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;


import javax.persistence.PersistenceException;
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
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Cliente", Cliente.class);
        query.setMaxResults(tam);
        query.setFirstResult((pag-1)*tam);
        List<Cliente> todos = query.list();
        return todos;
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT p.cliente, COUNT(p) FROM Proyecto p GROUP BY p.cliente ORDER BY COUNT(p) DESC");
        query.setMaxResults(1); // Limitar el resultado a un solo valor
        Object result = query.uniqueResult(); // Obtener el resultado único
        if (result != null) {
            // El primer elemento del array es el Cliente con más proyectos
            Object[] resultArray = (Object[]) result;
            Cliente clienteConMasProyectos = (Cliente) resultArray[0];
            return clienteConMasProyectos;
        }
        return null;
    }

    @Override
    public Cliente devolverMenosProyectos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT p.cliente, COUNT(p) FROM Proyecto p GROUP BY p.cliente ORDER BY COUNT(p) ASC");
        query.setMaxResults(1); // Limitar el resultado a un solo valor
        Object result = query.uniqueResult(); // Obtener el resultado único
        if (result != null) {
            // El primer elemento del array es el Cliente con más proyectos
            Object[] resultArray = (Object[]) result;
            Cliente clienteConMasProyectos = (Cliente) resultArray[0];
            return clienteConMasProyectos;
        }
        return null;
    }

    @Override
    public Long numeroClientes() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Long numero = session.createQuery("select count(*) from Cliente", Long.class).uniqueResult();
            session.close();
            return numero;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Cliente buscarPorId(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Cliente cliente = session.find(Cliente.class, id);
            session.close();
            return cliente;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Cliente> buscarPorRazon(String razon) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Cliente> query = session.createQuery("from Cliente where razonSocial like :razon");
            List<Cliente> filtro = query.setParameter("razon", "%"+razon+"%").list();
            return filtro;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Cliente> buscarPorCIF(String cif) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Cliente> query = session.createQuery("from Cliente where cif like :cif");
            List<Cliente> filtro = query.setParameter("cif", "%"+cif+"%").list();
            return filtro;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Cliente create(Cliente cliente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(cliente);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return cliente;
    }

    @Override
    public Cliente update(Cliente cliente) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try{
            session.beginTransaction();
            session.update(cliente);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return cliente;
    }

    @Override
    public boolean deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Cliente cliente = this.buscarPorId(id);
            if(cliente != null){
                session.delete(cliente);
            }else{
                return false;
            }
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }finally {
            session.close();
        }
        return true;
    }
}

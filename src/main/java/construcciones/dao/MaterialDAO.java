package construcciones.dao;

import construcciones.entidades.Avance;
import construcciones.entidades.Material;
import construcciones.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.util.List;

public class MaterialDAO implements MaterialDAOInterface{
    @Override
    public List<Material> devolverTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Material> todos = session.createQuery("from Material", Material.class).list();
        return todos;
    }

    @Override
    public List<Material> devolverTodos(int pag, int tam) {
        return null;
    }

    @Override
    public Material buscarPorId(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Material material = session.find(Material.class, id);
            session.close();
            return material;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int cantidadDeID(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "SELECT m.cantidad FROM Material m WHERE m.id = :id";
            int query = (int) session.createQuery(hql).setParameter("id", id).uniqueResult();

            return query;

        }catch (Exception e){
            e.printStackTrace();
            return -100;
        }
    }

    @Override
    public List<Material> buscarPorNombre(String nombre) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Material> query = session.createQuery("from Material where nombre like :nom");
            List<Material> filtro = query.setParameter("nom", "%"+nombre+"%").list();
            return filtro;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Material> buscarPorCosteSuperior(BigDecimal coste) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Material> query = session.createQuery("from Material where coste > :cos");
            List<Material> filtro = query.setParameter("cos", coste).list();
            return filtro;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Material> buscarPorCantidadSuperior(int cantidad) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Material> query = session.createQuery("from Material where cantidad > :can");
            List<Material> filtro = query.setParameter("can", cantidad).list();
            return filtro;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Material create(Material material) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(material);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
        session.close();
        return material;
    }

    @Override
    public Material update(Material material) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(material);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
        return material;
    }

    @Override
    public boolean delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Material material = this.buscarPorId(id);
            if(material != null){
                session.delete(material);
            }else{
                return false;
            }
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }finally{
            session.close();
        }
        return true;
    }
}

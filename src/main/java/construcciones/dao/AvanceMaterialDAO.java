package construcciones.dao;

import construcciones.entidades.Avance;
import construcciones.entidades.AvanceMaterial;
import construcciones.entidades.Material;
import construcciones.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.util.List;

public class AvanceMaterialDAO implements AvanceMaterialDAOInterface {

    @Override
    public List<AvanceMaterial> obtenerTodos() {
        Session session =HibernateUtil.getSessionFactory().openSession();
        List<AvanceMaterial> todos = session.createQuery("from AvanceMaterial", AvanceMaterial.class).list();
        return todos;
    }

    @Override
    public AvanceMaterial buscarPorId(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            AvanceMaterial avanceMaterial = session.find(AvanceMaterial.class, id);
            session.close();
            return avanceMaterial;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AvanceMaterial> buscarPorMaterial(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<AvanceMaterial> query = session.createQuery("from AvanceMaterial where material.id = :id");
            List<AvanceMaterial> filtro = query.setParameter("id", id).list();
            return filtro;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<AvanceMaterial> buscarPorAvance(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<AvanceMaterial> query = session.createQuery("from AvanceMaterial where avance.id = :id");
            List<AvanceMaterial> filtro = query.setParameter("id", id).list();
            return filtro;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private Avance buscarAvance(Long id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Avance avance = session.find(Avance.class, id);
            session.close();
            return avance;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private Material buscarMaterial(Long id){
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
    public AvanceMaterial create(Long avanceId, Long materialId, Long cantidad) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AvanceMaterial nuevo = new AvanceMaterial();
        try{
            session.beginTransaction();
            Material mat = this.buscarMaterial(materialId);
            Avance av = this.buscarAvance(avanceId);
            nuevo.setAvance(av);
            nuevo.setMaterial(mat);
            nuevo.setCantidad(cantidad);
            session.save(nuevo);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
        session.close();
        return nuevo;
    }

    @Override
    public AvanceMaterial update(AvanceMaterial avanceMaterial) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(avanceMaterial);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
        session.close();
        return avanceMaterial;
    }

    @Override
    public boolean delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            AvanceMaterial avanceMaterial = this.buscarPorId(id);
            if(avanceMaterial != null){
                session.delete(avanceMaterial);
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

package construcciones.dao;

import construcciones.entidades.Avance;
import construcciones.entidades.AvanceMaterial;
import construcciones.entidades.Material;
import construcciones.utils.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AvanceDAO implements AvanceDAOInterface{
    @Override
    public List<Avance> devolverTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Avance> todos = session.createQuery("from Avance", Avance.class).list();

        return todos;
    }

    @Override
    public List<Avance> devolverTodos(int pag, int tam) {
        return null;
    }

    @Override
    public Avance buscarPorId(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Avance avance = session.find(Avance.class, id);
            session.close();
            return avance;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Avance> buscarPorDesc(String desc) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Avance> query = session.createQuery("from Avance where descripcion like :desc");
            List<Avance> filtro = query.setParameter("desc", "%"+desc+"%").list();
            return filtro;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Avance> buscarPorFechaPosterior(Date fecha) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "FROM Avance a WHERE a.fecha > :fecha order by a.fecha asc";
            Query<Avance> query = session.createQuery(hql, Avance.class);
            query.setParameter("fecha", fecha);
            return query.list();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Avance> buscarPorPorcentaje(int porcentaje) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "FROM Avance a WHERE a.porcentajeCompletado > :porc order by a.porcentajeCompletado asc";
            Query<Avance> query = session.createQuery(hql, Avance.class);
            query.setParameter("porc", porcentaje);
            return query.list();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Material> obtenerMaterialesDeAvance(Long id) {
        List<Material> usados = new ArrayList<>();
        Avance avance = this.buscarPorId(id);
        List<AvanceMaterial> avancesMateriales = avance.getAvanceMateriales();
        for (AvanceMaterial avanceMaterial: avancesMateriales) {
            Material material = avanceMaterial.getMaterial();
            usados.add(material);
        }
        return usados;
    }

    @Override
    public Avance create(Avance avance) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(avance);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
        session.close();
        return avance;
    }

    @Override
    public Avance update(Avance avance) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(avance);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
        return avance;
    }

    @Override
    public boolean delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Avance avance = this.buscarPorId(id);
            if(avance != null){
                session.delete(avance);
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

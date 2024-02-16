package construcciones.dao;

import construcciones.dto.ProyectoCompletoDTO;
import construcciones.dto.ProyectoDTO;
import construcciones.entidades.Proyecto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import construcciones.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;

public class ProyectoDAO implements ProyectoDAOInterface{

    public List<Proyecto> devolverTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Proyecto> todos = session.createQuery("from Proyecto", Proyecto.class).list();
        session.close();

        return todos;
    }



    @Override
    public List<Proyecto> devolverTodos(int pagina, int objetos_por_pagina) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Proyecto", Proyecto.class);
        query.setMaxResults(objetos_por_pagina);
        query.setFirstResult((pagina-1)*objetos_por_pagina);
        List<Proyecto> todos = query.list();
        session.close();

        return todos;
    }

    @Override
    public Proyecto devolverMayorPresupuesto() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Proyecto order by presupuesto desc", Proyecto.class);
        query.setMaxResults(1);
        Proyecto mayorPresupuesto = (Proyecto) query.uniqueResult();
        session.close();
        return mayorPresupuesto;
    }

    @Override
    public Proyecto devolverMenorPresupuesto() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Proyecto order by presupuesto asc", Proyecto.class);
        query.setMaxResults(1);
        Proyecto menorPresupuesto = (Proyecto) query.uniqueResult();
        session.close();
        return menorPresupuesto;
    }

    @Override
    public List<Proyecto> devolverPresupuestoEntre(Double min, Double max) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Proyecto> query = session.createQuery("from Proyecto p where p.presupuesto >= :min and p.presupuesto <= :max", Proyecto.class);
        query.setParameter("min", min);
        query.setParameter("max", max);
        List<Proyecto> filtro = query.list();
        session.close();

        return filtro;
    }

    @Override
    public List<Proyecto> devolverProyectosDeClienteLike(String busqueda) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Proyecto> query = session.createQuery("from Proyecto p where p.cliente like :busqueda", Proyecto.class);
        List<Proyecto> filtro = query.setParameter("busqueda", "%"+busqueda+"%").list();

        session.close();
        return filtro;
    }

    @Override
    public List<Proyecto> iniciadosDespuesDe(Date fecha) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "FROM Proyecto p WHERE p.fechaInicio > :fecha order by p.fechaInicio asc";
            Query<Proyecto> query = session.createQuery(hql, Proyecto.class);
            query.setParameter("fecha", fecha);
            return query.list();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Proyecto> iniciadosAntesDe(Date fecha) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "FROM Proyecto p WHERE p.fechaInicio < :fecha order by p.fechaInicio desc";
            Query<Proyecto> query = session.createQuery(hql, Proyecto.class);
            query.setParameter("fecha", fecha);
            return query.list();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Proyecto> devolverTodosCategoria(List<String> categorias) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Proyecto> query = session.createQuery("from Proyecto where categoria in :categorias");
            List<Proyecto> filtro = query.setParameterList("categorias", categorias).list();
            return filtro;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Double totalProyectos() {
        try (Session session =HibernateUtil.getSessionFactory().openSession()){
            Query<Double> query = session.createQuery("select sum(presupuesto) from Proyecto");
            Double total = query.uniqueResult();
            return total;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Long numeroProyectos() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Long numero = session.createQuery("select count(*) from Proyecto", Long.class).uniqueResult();
            session.close();
            return numero;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Long numeroCategorias() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Long numero = session.createQuery("select count(distinct categoria) from Proyecto", Long.class).uniqueResult();
            session.close();
            return numero;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Proyecto buscarPorId(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Proyecto proyecto = session.find(Proyecto.class, id);
            session.close();
            return proyecto;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Proyecto> buscarPorNombre(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Proyecto> query = session.createQuery("from Proyecto where nombre like :busqueda");
            List<Proyecto> filtro = query.setParameter("busqueda", "%"+nombre+"%").list();
            return filtro;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Double mediaPresupuestos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Double media = session.createQuery("select avg(p.presupuesto) from Proyecto p", Double.class).getSingleResult();
            session.close();
            return media;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Double mediaPresupuestoCategoria(String categoria) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Double> mediaCat = session.createQuery("select avg(p.presupuesto) from Proyecto p where p.categoria like :busqueda", Double.class);
            Double media = mediaCat.setParameter("busqueda", categoria).getSingleResult();
            session.close();
            return media;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Proyecto create(Proyecto proyecto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(proyecto);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return proyecto;
    }

    @Override
    public Proyecto update(Proyecto proyecto) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try{
            session.beginTransaction();
            session.update(proyecto);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return proyecto;
    }

    @Override
    public boolean deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Proyecto proyecto = this.buscarPorId(id);
            if(proyecto != null){
                session.delete(proyecto);
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

    @Override
    public boolean deleteAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            String deleteQuery = "DELETE FROM Proyecto";
            Query query = session.createQuery(deleteQuery);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        } finally{
            session.close();
        }
        return true;
    }

    public List<ProyectoDTO> listarProyectosConCondicion(String condicion){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "SELECT new construcciones.dto.ProyectoDTO(p.nombre, p.presupuesto) FROM Proyecto p WHERE " +
                    condicion;
            Query<ProyectoDTO> query = session.createQuery(hql, ProyectoDTO.class);

            return query.list();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<ProyectoCompletoDTO> devolverTodosCompletos(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "SELECT new construcciones.dto.ProyectoCompletoDTO(p.id, p.nombre, p.presupuesto," +
                    " p.fechaInicio, p.plano, p.categoria, p.cliente) FROM Proyecto p";
            Query<ProyectoCompletoDTO> query = session.createQuery(hql, ProyectoCompletoDTO.class);
            return query.list();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

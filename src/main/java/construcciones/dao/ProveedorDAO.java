package construcciones.dao;

import construcciones.entidades.Material;
import construcciones.entidades.Proveedor;
import construcciones.entidades.Proyecto;
import construcciones.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.PersistenceException;
import java.util.List;

public class ProveedorDAO implements ProveedorDAOInterface{
    @Override
    public List<Proveedor> todos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            List<Proveedor> todos = session.createQuery("from Proveedor", Proveedor.class).list();
            return todos;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public Proveedor buscarPorId(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Proveedor proveedor = session.find(Proveedor.class, id);
            session.close();
            return proveedor;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Proveedor create(Proveedor proveedor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(proveedor);
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return proveedor;
    }

    @Override
    public Proveedor update(Proveedor proveedor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(proveedor);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return proveedor;
    }

    @Override
    public boolean delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Proveedor proveedor = this.buscarPorId(id);
            if(proveedor != null){
                session.delete(proveedor);
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
    public List<Material> obtenerMaterialesProveedor(Long idProveedor) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Proveedor proveedor = session.get(Proveedor.class, idProveedor);
            return proveedor.getMateriales();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean agregarMaterialAProveedor(Long idProveedor, Long idMaterial) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Proveedor proveedor = session.get(Proveedor.class, idProveedor);
            Material material = session.get(Material.class, idMaterial);
            proveedor.getMateriales().add(material);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean eliminarMaterialAProveedor(Long idProveedor, Long idMaterial) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            Proveedor proveedor = session.get(Proveedor.class, idProveedor);
            Material material = session.get(Material.class, idMaterial);

            if (proveedor != null && material != null) {
                if (proveedor.getMateriales().contains(material)) {
                    proveedor.getMateriales().remove(material);
                    session.getTransaction().commit();
                } else {
                    System.out.println("El material no está asociado al proveedor.");
                    return false;
                }
            } else {
                System.out.println("Proveedor o material no encontrado.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

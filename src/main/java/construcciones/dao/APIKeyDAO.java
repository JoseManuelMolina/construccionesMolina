package construcciones.dao;

import construcciones.entidades.APIKey;
import construcciones.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.PersistenceException;
import org.hibernate.query.Query;
import java.util.List;

public class APIKeyDAO implements APIKeyDAOInterface{

    private String generarKey(){
        String res = "";
        String caracteres = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 20; i++) {
            int indice = (int) (Math.random() * caracteres.length());
            res += caracteres.charAt(indice);
        }
        return res;
    }

    @Override
    public List<APIKey> devolverTodas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<APIKey> todos = session.createQuery("from APIKey", APIKey.class).list();
        return todos;
    }

    @Override
    public List<APIKey> devolverTodas(int pagina, int tamaño) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from APIKey", APIKey.class);
        query.setMaxResults(tamaño);
        query.setFirstResult((pagina-1)*tamaño);
        List<APIKey> todos = query.list();
        session.close();

        return todos;
    }

    @Override
    public Long numeroKeys() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Long numero = session.createQuery("select count(*) from APIKey", Long.class).uniqueResult();
            session.close();
            return numero;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public APIKey buscarId(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            APIKey apiKey = session.find(APIKey.class,id);
            session.close();
            return apiKey;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public APIKey buscarPorKey(String key) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<APIKey> query = session.createQuery("from APIKey where key like :busqueda");
            APIKey filtro = query.setParameter("busqueda", "%"+key+"%").uniqueResult();
            return filtro;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public APIKey usarKey(Long idKey) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        APIKey apiKey = buscarId(idKey);
        try{
            session.beginTransaction();
            apiKey.setUsados(apiKey.getUsados()+1);
            session.update(apiKey);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return apiKey;
    }

    @Override
    public APIKey activarKey(Long idKey) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        APIKey apiKey = buscarId(idKey);
        try{
            session.beginTransaction();
            apiKey.setActivada(true);
            session.update(apiKey);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return apiKey;
    }

    @Override
    public APIKey desactivarKey(Long idKey) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        APIKey apiKey = buscarId(idKey);
        try{
            session.beginTransaction();
            apiKey.setActivada(false);
            session.update(apiKey);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return apiKey;
    }

    @Override
    public Boolean activada(Long idKey) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try{
            APIKey apiKey = buscarId(idKey);
            return apiKey.getActivada();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return null;
    }

    @Override
    public APIKey create() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        APIKey key = new APIKey(generarKey());
        try{
            session.beginTransaction();
            session.save(key);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return key;
    }

    @Override
    public APIKey update(APIKey apiKey) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try{
            session.beginTransaction();
            session.update(apiKey);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return apiKey;
    }

    @Override
    public boolean delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            APIKey apiKey = buscarId(id);
            if(apiKey != null){
                session.delete(apiKey);
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

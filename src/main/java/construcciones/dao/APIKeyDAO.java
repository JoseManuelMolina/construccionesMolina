package construcciones.dao;

import com.google.protobuf.Api;
import construcciones.entidades.APIKey;
import construcciones.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.PersistenceException;
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
        return null;
    }

    @Override
    public APIKey buscarId(Long id) {
        return null;
    }

    @Override
    public APIKey buscarPorKey(String key) {
        return null;
    }

    @Override
    public APIKey usarKey(Long idKey) {
        return null;
    }

    @Override
    public APIKey activarKey(Long idKey) {
        return null;
    }

    @Override
    public boolean activada(Long idKey) {
        return false;
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
    public APIKey update() {
        return null;
    }

    @Override
    public boolean delete(APIKey key) {
        return false;
    }
}

package construcciones.dao;

import construcciones.entidades.Avance;
import construcciones.utils.HibernateUtil;
import org.hibernate.Session;

import java.sql.Date;
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
        return null;
    }

    @Override
    public List<Avance> buscarPorDesc(String desc) {
        return null;
    }

    @Override
    public List<Avance> buscarPorFechaPosterior(Date fecha) {
        return null;
    }

    @Override
    public List<Avance> buscarPorPorcentaje(int porcentaje) {
        return null;
    }

    @Override
    public Avance create(Avance avance) {
        return null;
    }

    @Override
    public Avance update(Avance avance) {
        return null;
    }

    @Override
    public boolean delete(Avance avance) {
        return false;
    }
}

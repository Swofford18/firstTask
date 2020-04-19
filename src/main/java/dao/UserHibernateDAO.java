package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public boolean updateUser(User user) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET " +
                "age = :age " +
                ", password = :password " +
                "WHERE name = :name");
        query.setParameter("age", user.getAge());
        query.setParameter("password", user.getPassword());
        query.setParameter("name", user.getName());
        int result = query.executeUpdate();
        transaction.commit();
        session.close();
        return result > 0;
    }

    @Override
    public boolean createUser(User user) {
        session.save(user);
        session.close();
        return true;
    }

    @Override
    public boolean deleteUser(String name) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE User WHERE name = :name");
        query.setParameter("name", name);
        int result = query.executeUpdate();
        transaction.commit();
        session.close();
        return result > 0;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = (List<User>) session.createQuery("FROM User").list();
        session.close();
        return users;
    }
}

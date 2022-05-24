package org.example.repository;

import org.example.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final SessionFactory sf;

    @Autowired
    public UserRepositoryImpl(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public User findByUserName(String userName) {
        try (Session session = sf.openSession()) {
            Query<User> query = session.createQuery("from User where userName = :param", User.class);
            query.setParameter("param", userName);
            return query.uniqueResult();
        }
    }

    @Override
    public void save(User user) {
        try (Session session = sf.openSession()) {
            session.save(user);
        }
    }

    @Override
    public void update(User user) {
        try (Session session = sf.openSession()) {
            session.update(user);
        }
    }

    @Override
    public void delete(User user) {
        try (Session session = sf.openSession()) {
            session.remove(user);
        }
    }
}

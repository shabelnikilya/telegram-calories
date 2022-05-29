package org.example.repository;

import org.example.models.UserDB;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final static Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);
    private final SessionFactory sf;

    @Autowired
    public UserRepositoryImpl(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public UserDB findByUserName(String userName) {
        try (Session session = sf.openSession()) {
            Query<UserDB> query = session.createQuery("from UserDB where userName = :param", UserDB.class);
            query.setParameter("param", userName);
            return query.uniqueResult();
        }
    }

    @Override
    public void save(UserDB user) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(UserDB user) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(UserDB user) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.remove(user);
            session.getTransaction().commit();
        }
    }
}

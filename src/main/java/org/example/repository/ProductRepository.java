package org.example.repository;

import org.example.models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository implements Store<Product> {
    private final static Logger LOG = LoggerFactory.getLogger(ProductRepository.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Product findById(int id) {
        LOG.info("Call method findById() products!");
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("from Product where id = :param", Product.class);
            query.setParameter("param", id);
            return query.uniqueResult();
        }
    }

    @Override
    public Product findByName(String name) {
        LOG.info("Call method findByName() products!");
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("from Product where name = :param", Product.class);
            query.setParameter("param", name);
            return query.uniqueResult();
        }
    }

    @Override
    public List<Product> findAll() {
        LOG.info("Call method findAll() products!");
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Product", Product.class).list();
        }
    }

    @Override
    public void save(Product product) {
        LOG.info("Call method save() products!");
        try (Session session = sessionFactory.openSession()) {
            session.save(product);
        }
    }

    @Override
    public void update(Product product) {
        LOG.info("Call method update() products!");
        try (Session session = sessionFactory.openSession()) {
            session.update(product);
        }
    }

    @Override
    public void remove(Product product) {
        LOG.info("Call method remove() products!");
        try (Session session = sessionFactory.openSession()) {
            session.remove(product);
        }
    }
}

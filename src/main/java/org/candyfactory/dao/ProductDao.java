package org.candyfactory.dao;

import jakarta.persistence.Entity;
import org.candyfactory.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ProductDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Product> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Product p", Product.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Product show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Product.class, id);
    }

    @Transactional
    public void save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);
    }

    @Transactional
    public void update(int id, Product updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Product productToBeUpdated = session.get(Product.class, id);

        productToBeUpdated.setName(updatedPerson.getName());

    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Product.class, id));
    }
}

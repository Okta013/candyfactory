package org.candyfactory.dao;

import org.springframework.transaction.annotation.Transactional;
import org.candyfactory.model.ProductType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.TrueFalseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductTypeDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductTypeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<ProductType> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from ProductType", ProductType.class)
                .getResultList();
    }


    @Transactional(readOnly = true)
    public ProductType show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(ProductType.class, id);
    }

    @Transactional
    public void save(ProductType productType) {
        Session session = sessionFactory.getCurrentSession();
        session.save(productType);
    }

    @Transactional
    public void update(int id, ProductType updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        ProductType personToBeUpdated = session.get(ProductType.class, id);

        personToBeUpdated.setName(updatedPerson.getName());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(ProductType.class, id));
    }

}

package org.candyfactory.dao;

import org.springframework.transaction.annotation.Transactional;
import org.candyfactory.model.ProductType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    public void update(int id, ProductType updatedProductType) {
        Session session = sessionFactory.getCurrentSession();
        ProductType productTypeToBeUpdated = session.get(ProductType.class, id);

        productTypeToBeUpdated.setName(updatedProductType.getName());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(ProductType.class, id));
    }

}

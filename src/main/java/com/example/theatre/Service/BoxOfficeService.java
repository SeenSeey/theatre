package com.example.theatre.Service;

import com.example.theatre.Models.BoxOffice;
import com.example.theatre.Models.Theatre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class BoxOfficeService {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public List<BoxOffice> getBoxOfficesByTheatre(Theatre theatre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery("FROM BoxOffice WHERE theatre = :theatre", BoxOffice.class);
            query.setParameter("theatre", theatre);
            return query.getResultList();
        }
    }

    public void saveBoxOffice(BoxOffice boxOffice) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(boxOffice);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateBoxOffice(BoxOffice boxOffice) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(boxOffice);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteBoxOffice(BoxOffice boxOffice) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(boxOffice);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<BoxOffice> getAllBoxOffices() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from BoxOffice", BoxOffice.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

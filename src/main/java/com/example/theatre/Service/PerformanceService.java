package com.example.theatre.Service;

import com.example.theatre.Models.Performance;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class PerformanceService {

    public List<Performance> getAllPerformances() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Performance", Performance.class).list();
        }
    }

    public List<Performance> getPerformancesByTheatreId(int theatreId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Performance where theatre.id = :theatreId", Performance.class)
                    .setParameter("theatreId", theatreId)
                    .list();
        }
    }

    public Performance savePerformance(Performance performance) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(performance);
            transaction.commit();
            return performance;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Optional<Performance> updatePerformance(int id, Performance updatedPerformance) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Performance performance = session.get(Performance.class, id);
            if (performance != null) {
                performance.setName(updatedPerformance.getName());
                performance.setTimeIntervalPerformance(updatedPerformance.getTimeIntervalPerformance());
                performance.setAuthor(updatedPerformance.getAuthor());
                performance.setTheatre(updatedPerformance.getTheatre());
                performance.setDirector(updatedPerformance.getDirector());
                session.update(performance);
                transaction.commit();
                return Optional.of(performance);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public boolean deletePerformance(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Performance performance = session.get(Performance.class, id);
            if (performance != null) {
                session.delete(performance);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }


}

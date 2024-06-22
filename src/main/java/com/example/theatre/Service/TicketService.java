package com.example.theatre.Service;

import com.example.theatre.Models.Ticket;
import com.example.theatre.Models.Viewer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class TicketService {

    public void saveTicket(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Ticket> getTicketsByPerformanceId(int performanceId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Ticket where performance.id = :performanceId", Ticket.class)
                    .setParameter("performanceId", performanceId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Date> getDatesByPerformanceId(int performanceId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT DISTINCT t.timeEvent FROM Ticket t WHERE t.performance.id = :performanceId", Date.class)
                    .setParameter("performanceId", performanceId)
                    .list();
        }
    }

    public Ticket bookTicket(int performanceId, Date timeEvent, int viewerId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Ticket ticket = session.createQuery("FROM Ticket t WHERE t.performance.id = :performanceId AND t.timeEvent = :timeEvent AND t.viewer IS NULL", Ticket.class)
                    .setParameter("performanceId", performanceId)
                    .setParameter("timeEvent", timeEvent)
                    .setMaxResults(1)
                    .uniqueResult();

            if (ticket != null) {
                ticket.setViewer(session.get(Viewer.class, viewerId));
                session.update(ticket);
                transaction.commit();
                return ticket;
            } else {
                transaction.rollback();
                return null;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public void removeBookedTicket(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

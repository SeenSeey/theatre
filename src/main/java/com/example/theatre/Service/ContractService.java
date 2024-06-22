package com.example.theatre.Service;

import com.example.theatre.Models.Contract;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class ContractService {

    public Contract saveContract(Contract contract) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(contract);
            transaction.commit();
            return contract;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<Contract> getContractsByPerformanceAndActor(int performanceId, int actorId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Contract where performance.id = :performanceId and actor.id = :actorId", Contract.class)
                    .setParameter("performanceId", performanceId)
                    .setParameter("actorId", actorId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteContract(int contractId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Contract contract = session.get(Contract.class, contractId);
            if (contract != null) {
                session.delete(contract);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Contract> getContractsByPerformanceId(int performanceId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Contract where performance.id = :performanceId", Contract.class)
                    .setParameter("performanceId", performanceId)
                    .list();
        }
    }
}

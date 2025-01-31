package com.javarush.dao;

import com.javarush.domain.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TaskDAO {

    private final SessionFactory sessionFactory;

    public TaskDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void create(Task task) {
        getSession().persist(task);
    }

    @Transactional(readOnly = true)
    public List<Task> getAll(int offset, int limit) {
        Query<Task> query = getSession().createQuery("SELECT t FROM Task t", Task.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public int getAllCount() {
        Query<Long> query = getSession().createQuery("SELECT count(t) FROM Task t", Long.class);
        return Math.toIntExact(query.uniqueResult());
    }

    @Transactional(readOnly = true)
    public Task getById(int id) {
        Query<Task> query = getSession().createQuery("SELECT t FROM Task t WHERE t.id = :id", Task.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    @Transactional
    public void update(Task task) {
        getSession().merge(task);
    }

    @Transactional
    public void delete(Task task) {
        getSession().remove(task);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}

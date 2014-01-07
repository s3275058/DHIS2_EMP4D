package org.hisp.dhis.college;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public class DefaultCollegeService implements CollegeService {

    protected SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int add(College college) {
        return (Integer)sessionFactory.getCurrentSession().save(college);
    }

    @Override
    public void update(College college) {
        sessionFactory.getCurrentSession().update(college);
    }

    @Override
    public void delete(int id) {
        College college = (College)sessionFactory.getCurrentSession().get(College.class, id);
        sessionFactory.getCurrentSession().delete(college);
    }

    @Override
    public College get(int id) {
        return (College)sessionFactory.getCurrentSession().get(College.class, id);
    }

    @Override
    public Collection<College> getAll() {
        return (Collection<College>) sessionFactory.getCurrentSession().createQuery("from College").list();
    }
}

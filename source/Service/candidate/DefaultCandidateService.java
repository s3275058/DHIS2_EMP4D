package org.hisp.dhis.candidate;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public class DefaultCandidateService implements CandidateService {
    protected SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int add(Candidate candidate) {
        return (Integer)sessionFactory.getCurrentSession().save(candidate);
    }

    @Override
    public void update(Candidate candidate) {
        sessionFactory.getCurrentSession().update(candidate);
    }

    @Override
    public void delete(int id) {
        Candidate candidate = (Candidate)sessionFactory.getCurrentSession().get(Candidate.class, id);
        sessionFactory.getCurrentSession().delete(candidate);
    }

    @Override
    public Candidate get(int id) {
        return (Candidate)sessionFactory.getCurrentSession().get(Candidate.class, id);
    }

    @Override
    public Collection<Candidate> getAll() {
        return (Collection<Candidate>) sessionFactory.getCurrentSession().createQuery("from Candidate").list();
    }
}

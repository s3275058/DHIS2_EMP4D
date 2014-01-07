package org.hisp.dhis.certificate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

@Transactional
public class DefaultCertificateService implements CertificateService {

    protected SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int add(Certificate certificate) {
        return (Integer)sessionFactory.getCurrentSession().save(certificate);
    }

    @Override
    public void update(Certificate certificate) {
        sessionFactory.getCurrentSession().update(certificate);
    }

    @Override
    public void delete(int id) {
		System.out.println("id in service: " + id);
        Certificate certificate = (Certificate)sessionFactory.getCurrentSession().get(Certificate.class, id);

		System.out.println(certificate.getId());
        System.out.println(certificate.getCandidate());
        Session session = sessionFactory.getCurrentSession();
        session.delete(certificate);
    }

    @Override
    public Certificate get(int id) {
        return (Certificate)sessionFactory.getCurrentSession().get(Certificate.class, id);
    }

    @Override
    public Collection<Certificate> getAll() {
        return (Collection<Certificate>) sessionFactory.getCurrentSession().createQuery("from Certificate").list();
    }
}

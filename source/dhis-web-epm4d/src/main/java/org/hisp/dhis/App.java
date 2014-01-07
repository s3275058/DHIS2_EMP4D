package org.hisp.dhis;

//import org.hibernate.classic.Session;


public class App
{
    public static void main( String[] args )
    {
        /*
        //Test mysql Connection
        Connection mysqlCon = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            mysqlCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutorial", "root", "dhis");
            System.out.println( "MySQL Pass!" );
        } catch (Exception e) {
            System.out.println(e);
        }*/

        /*
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        CertificateDAOImpl certificateDAO = (CertificateDAOImpl)context.getBean("certificateDAOImpl");
        AccountDAOImpl accountDAO = (AccountDAOImpl)context.getBean("accountDAOImpl");

        Collection<Certificate> certificates = certificateDAO.getAll();
        for (Certificate record : certificates) {
            System.out.print("ID : " + record.getCid() );
            System.out.print(", Name : " + record.getBoard() );
            System.out.println(", Age : " + record.getSubject());
        }*/

        /*
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date starD = format.parse("2011-11-11");
            java.util.Date endD = format.parse("2013-11-11");

            java.sql.Date sqlStarD = new java.sql.Date(starD.getTime());
            java.sql.Date sqlEndD = new java.sql.Date(endD.getTime());

            Certificate certificate = certificateDAO.getCertificate(2);
            certificate.setSubject("ABC");
            certificate.setBoard("DEF");
            certificateDAO.updateCertificate(certificate);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }*/

        //certificateDAO.deleteCertificate(5);

        //accountDAO.add(new Account(1,"123","123","admin"));
        /*
        Collection<Account> accounts = accountDAO.getAll();
        for(Account record : accounts) {
            System.out.print("ID: " + record.getId());
            System.out.print(", Username: " + record.getUsername());
            System.out.println(", Password: " + record.getPassword());
        }
        */
        /*
        System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Transaction tx = null;
        try {
            //tx = session.beginTransaction();
            List certificates = session.createQuery("FROM Certificate").list();
            for (Iterator iterator = certificates.iterator(); iterator.hasNext();) {
                Certificate certificate = (Certificate) iterator.next();
                System.out.println("C ID: " + certificate.getCid());
                System.out.println("----------------------------------------");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        */
    }
}

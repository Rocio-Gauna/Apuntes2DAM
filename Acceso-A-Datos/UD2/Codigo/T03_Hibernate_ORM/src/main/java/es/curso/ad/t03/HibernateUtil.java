package es.curso.ad.t03;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {
    private HibernateUtil() { }

    public static SessionFactory crear() {
        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        cfg.setProperty("hibernate.connection.url",
                "jdbc:mysql://localhost:3307/ad_ud2?connectionTimeZone=UTC");
        cfg.setProperty("hibernate.connection.username", "ad_user");
        cfg.setProperty("hibernate.connection.password", "ad_pass");
        cfg.setProperty("hibernate.hbm2ddl.auto", "validate");
        cfg.setProperty("hibernate.show_sql", "true");
        cfg.addAnnotatedClass(Club.class);
        cfg.addAnnotatedClass(Jugador.class);
        return cfg.buildSessionFactory();
    }
}

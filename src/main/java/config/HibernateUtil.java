package config;

import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(AlunoEntity.class)
                .addAnnotatedClass(PersonalEntity.class)
                .addAnnotatedClass(DisponibilidadeEntity.class)
                .addAnnotatedClass(TreinoEntity.class)
                .addAnnotatedClass(ExercicioEntity.class)
                .addAnnotatedClass(TreinoExercicioEntity.class)
                .addAnnotatedClass(TreinoExercicioId.class)
                .addAnnotatedClass(AgendamentoEntity.class)
                .addAnnotatedClass(CheckinEntity.class)
                .addAnnotatedClass(EvolucaoEntity.class)
                .addAnnotatedClass(UserEntity.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

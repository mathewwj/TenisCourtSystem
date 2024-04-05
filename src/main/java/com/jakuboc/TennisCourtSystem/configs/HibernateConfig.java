package com.jakuboc.TennisCourtSystem.configs;

import com.jakuboc.TennisCourtSystem.repositories.CourtRepository;
import com.jakuboc.TennisCourtSystem.repositories.Impl.CourtRepositoryImpl;
import com.jakuboc.TennisCourtSystem.repositories.Impl.UserRepositoryImpl;
import com.jakuboc.TennisCourtSystem.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

@Configuration
public class HibernateConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.jakuboc.TennisCourtSystem.domain.entities");
        return sessionFactory;
    }

//    @Bean
//    public CourtRepository courtRepository(LocalSessionFactoryBean sessionFactory) {
//        return new CourtRepositoryImpl(sessionFactory.getObject());
//    }
//
//    @Bean
//    public UserRepository userRepository(LocalSessionFactoryBean sessionFactory) {
//        return new UserRepositoryImpl(sessionFactory.getObject());
//    }
}

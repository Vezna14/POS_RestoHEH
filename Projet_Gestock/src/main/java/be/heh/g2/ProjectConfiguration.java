package be.heh.g2;

import be.heh.g2.adapter.persistence.*;
import be.heh.g2.application.domain.service.ProductManagementImpl;
import be.heh.g2.application.domain.service.TableManagImpl;
import be.heh.g2.application.port.in.ProductManagementUseCase;
import be.heh.g2.application.port.in.TableRestoManagUseCase;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import org.springframework.web.cors.CorsConfiguration;


@Configuration
public class ProjectConfiguration {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private TableRepository tableRepository;


    //private ProductManagementImpl productManagementImpl;

    private PersistenceAdapter persistenceAdapter;
    private TablePersistanceAdaptater tablePersistanceAdaptater;

    @Bean

    ProductManagementUseCase getProductManagementUseCase(){
        persistenceAdapter=new PersistenceAdapter(repository);
        return new ProductManagementImpl(persistenceAdapter);
    }
    @Bean
    TableRestoManagUseCase getTableUseCaseManagement() {
        tablePersistanceAdaptater=new TablePersistanceAdaptater(tableRepository);
        return new TableManagImpl(tablePersistanceAdaptater);

    }
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");

        CorsConfigurationSource source = request -> config;

        return new CorsFilter(source);

    }
}

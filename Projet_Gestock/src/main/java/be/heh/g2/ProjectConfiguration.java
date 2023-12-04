package be.heh.g2;

import be.heh.g2.adapter.persistence.PersistenceAdapter;
import be.heh.g2.adapter.persistence.ProductRepository;
import be.heh.g2.adapter.persistence.ProductRowMapper;
import be.heh.g2.application.domain.service.ProductManagementImpl;
import be.heh.g2.application.port.in.ProductManagementUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfiguration {
    @Autowired
    private ProductRepository repository;

    //private ProductManagementImpl productManagementImpl;

    private PersistenceAdapter persistenceAdapter;
    @Bean

    ProductManagementUseCase getProductManagementUseCase(){
        persistenceAdapter=new PersistenceAdapter(repository);
        return new ProductManagementImpl(persistenceAdapter);
    }

}

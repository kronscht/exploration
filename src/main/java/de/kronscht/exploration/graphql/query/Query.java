package de.kronscht.exploration.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import de.kronscht.exploration.model.AppUser;
import de.kronscht.exploration.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    private AppUserRepository appUserRepository;

    @Autowired
    public Query(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public Iterable<AppUser> getAppUsers() {
        return appUserRepository.findAll();
    }
}

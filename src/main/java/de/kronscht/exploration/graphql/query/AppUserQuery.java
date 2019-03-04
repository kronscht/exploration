package de.kronscht.exploration.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import de.kronscht.exploration.model.AppUser;
import de.kronscht.exploration.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppUserQuery implements GraphQLQueryResolver {

    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserQuery(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    /**
     * Returns all {@link AppUser}s.
     *
     * @return List of {@link AppUser}s
     */
    public Iterable<AppUser> getAppUsers() {
        return appUserRepository.findAll();
    }
}

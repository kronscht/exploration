package de.kronscht.exploration.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import de.kronscht.exploration.exception.AppUserNotFoundException;
import de.kronscht.exploration.model.AppUser;
import de.kronscht.exploration.model.Task;
import de.kronscht.exploration.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Tobias on 03.03.2019
 */
@Component
public class TaskResolver implements GraphQLResolver<Task> {

    private AppUserRepository appUserRepository;

    @Autowired
    public TaskResolver(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    /**
     * Resolves the relationship between {@link AppUser} and {@link Task}.
     * @param task {@link Task}
     * @return {@link AppUser} for the given {@link Task}
     */
    public AppUser getAppUser(Task task) {
        return appUserRepository.findById(task.getAppUser().getId()).orElseThrow(AppUserNotFoundException::new);
    }
}

package de.kronscht.exploration.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import de.kronscht.exploration.exception.TodoException;
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

    public AppUser getAppUser(Task task) {
        return appUserRepository.findById(task.getAppUser().getId()).orElseThrow(TodoException::new);
    }
}

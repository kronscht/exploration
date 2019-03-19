package de.kronscht.exploration.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import de.kronscht.exploration.model.AppUser;
import de.kronscht.exploration.model.Task;
import de.kronscht.exploration.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppUserResolver implements GraphQLResolver<AppUser> {


    private TaskRepository taskRepository;

    @Autowired
    public AppUserResolver(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Iterable<Task> getTasks(AppUser appUser) {
        return taskRepository.findAllByAppUser(appUser);
    }

}

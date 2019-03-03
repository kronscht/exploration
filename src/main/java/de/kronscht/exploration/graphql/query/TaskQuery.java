package de.kronscht.exploration.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import de.kronscht.exploration.model.AppUser;
import de.kronscht.exploration.model.Task;
import de.kronscht.exploration.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Tobias on 03.03.2019
 */
@Component
public class TaskQuery implements GraphQLQueryResolver {

    private TaskRepository taskRepository;

    @Autowired
    public TaskQuery(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Gets all {@link Task}s for a specific {@link AppUser}.
     *
     * @param id The id for the {@link AppUser}
     * @return List of {@link Task}s for a specific {@link AppUser}
     */
    public Iterable<Task> getTasks(Long id) {
        return taskRepository.findAllByAppUser(new AppUser(id));
    }
}

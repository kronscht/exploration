package de.kronscht.exploration.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.kronscht.exploration.exception.AppUserNotFoundException;
import de.kronscht.exploration.exception.TaskNotFoundException;
import de.kronscht.exploration.model.AppUser;
import de.kronscht.exploration.model.Task;
import de.kronscht.exploration.model.input.SaveTaskInput;
import de.kronscht.exploration.model.input.UpdateTaskInput;
import de.kronscht.exploration.repository.AppUserRepository;
import de.kronscht.exploration.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * @author Tobias on 03.03.2019
 */
@Component
public class TaskMutation implements GraphQLMutationResolver {

    private final AppUserRepository appUserRepository;

    private final TaskRepository taskRepository;

    @Autowired
    public TaskMutation(AppUserRepository appUserRepository, TaskRepository taskRepository) {
        this.appUserRepository = appUserRepository;
        this.taskRepository = taskRepository;
    }

    /**
     * Saves a new task.
     *
     * @param input {@link SaveTaskInput} with values to save a new task
     * @return The saved {@link Task}
     */
    public Task saveTask(SaveTaskInput input) {
        AppUser appUser = appUserRepository.findById(input.getUserId()).orElseThrow(AppUserNotFoundException::new);
        Task task = mapToTask(input);
        task.setAppUser(appUser);
        return taskRepository.save(task);
    }

    /**
     * Updates an existing task.
     *
     * @param input {@link UpdateTaskInput} with values to update an existing task
     * @return The updated {@link Task}
     */
    @Transactional
    public Task updateTask(UpdateTaskInput input) {
        Task task = taskRepository.findById(input.getId()).orElseThrow(TaskNotFoundException::new);
        task.setDescription(input.getDescription());
        task.setDone(input.isDone());
        return task;
    }

    /**
     * Maps {@link SaveTaskInput} to a {@link Task}.
     *
     * @param input {@link SaveTaskInput}
     * @return {@link Task}
     */
    private Task mapToTask(SaveTaskInput input) {
        Task task = new Task();
        task.setDescription(input.getDescription());
        task.setDone(input.isDone());
        return task;
    }
}

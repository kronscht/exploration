package de.kronscht.exploration.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.kronscht.exploration.exception.TodoException;
import de.kronscht.exploration.model.AppUser;
import de.kronscht.exploration.model.Task;
import de.kronscht.exploration.model.input.SaveTaskInput;
import de.kronscht.exploration.repository.AppUserRepository;
import de.kronscht.exploration.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


    public Task saveTask(SaveTaskInput input) {
        AppUser appUser = appUserRepository.findById(input.getUserId()).orElseThrow(TodoException::new);


        Task task = mapToTask(input);
        task.setAppUser(appUser);
        return taskRepository.save(task);

    }


    private Task mapToTask(SaveTaskInput input) {
        Task task = new Task();
        task.setDescription(input.getDescription());
        task.setDone(input.isDone());
        return task;

/*
    private Task mapToTodo(SaveTaskInput input) {
        AppUser appUser = appUserRepository.findById(input.getUserId()).orElseThrow(RuntimeException::new);
        Task task = new Task();
        task.setDescription(input.getDescription());
        task.setDone(input.isDone());
        task.setAppUser(appUser);
        return task;
    }
    */
    }
}

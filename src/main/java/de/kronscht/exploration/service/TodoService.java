package de.kronscht.exploration.service;

import de.kronscht.exploration.exception.TodoException;
import de.kronscht.exploration.model.AppUser;
import de.kronscht.exploration.model.Task;
import de.kronscht.exploration.repository.AppUserRepository;
import de.kronscht.exploration.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tobias on 03.03.2019
 */
@Service
public class TodoService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    public Task saveTodo(Task task) {
        if (task.getAppUser() == null) {
            throw new TodoException();
        }
        AppUser appUser = appUserRepository.findById(task.getAppUser().getId()).orElseThrow(TodoException::new);
        task.setAppUser(appUser);
        return taskRepository.save(task);
    }


}

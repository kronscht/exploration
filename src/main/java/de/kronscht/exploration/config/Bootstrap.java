package de.kronscht.exploration.config;

import de.kronscht.exploration.model.AppUser;
import de.kronscht.exploration.model.Task;
import de.kronscht.exploration.repository.AppUserRepository;
import de.kronscht.exploration.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Bootstrap {

    private AppUserRepository appUserRepository;

    private TaskRepository taskRepository;

    @Autowired
    public Bootstrap(AppUserRepository appUserRepository, TaskRepository taskRepository) {
        this.appUserRepository = appUserRepository;
        this.taskRepository = taskRepository;
    }

    @PostConstruct
    public void initialize() {

        AppUser appUser = getAppUser();
        AppUser savedUser = appUserRepository.save(appUser);

        taskRepository.save(getTodo(savedUser));
    }

    private Task getTodo(AppUser appUser) {
        return new Task("Initial Task", false, appUser);
    }

    private AppUser getAppUser() {
        return new AppUser("Max", "Mustermann");
    }

}

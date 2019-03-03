package de.kronscht.exploration.config;

import de.kronscht.exploration.model.AppUser;
import de.kronscht.exploration.model.Todo;
import de.kronscht.exploration.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Bootstrap {

    private AppUserRepository appUserRepository;

    @Autowired
    public Bootstrap(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @PostConstruct
    public void initialize() {

        AppUser appUser = getAppUser();
        appUser.addToTodos(getTodo());

        appUserRepository.save(appUser);

    }

    private Todo getTodo() {
        return new Todo("First", false, null);
    }

    private AppUser getAppUser() {
        return new AppUser("Max", "Mustermann", null);
    }

}

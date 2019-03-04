package de.kronscht.exploration.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.kronscht.exploration.model.AppUser;
import de.kronscht.exploration.model.input.AppUserInput;
import de.kronscht.exploration.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppUserMutation implements GraphQLMutationResolver {

    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserMutation(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser writeAppUser(AppUserInput appUserInput) {
        return appUserRepository.save(mapInputToAppUser(appUserInput));
    }

    //region Helper

    private AppUser mapInputToAppUser(AppUserInput input) {
        AppUser appUser = new AppUser();
        appUser.setName(input.getName());
        appUser.setSurname(input.getSurname());
        return appUser;
    }

    //endregion

}

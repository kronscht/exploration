package de.kronscht.exploration.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.kronscht.exploration.model.AppUser;
import de.kronscht.exploration.model.Task;
import de.kronscht.exploration.model.input.AppUserInput;
import de.kronscht.exploration.model.input.SaveTaskInput;
import de.kronscht.exploration.model.input.TaskInput;
import de.kronscht.exploration.model.input.UpdateTaskInput;
import de.kronscht.exploration.repository.AppUserRepository;
import de.kronscht.exploration.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class Mutation implements GraphQLMutationResolver {

    private AppUserRepository appUserRepository;

    private TaskRepository taskRepository;

    @Autowired
    public Mutation(AppUserRepository appUserRepository, TaskRepository taskRepository) {
        this.appUserRepository = appUserRepository;
        this.taskRepository = taskRepository;
    }

    public AppUser writeAppUser(AppUserInput appUserInput) {
        return appUserRepository.save(mapToAppUser(appUserInput));
    }

    //region Task



    @Transactional
    public Task updateTask(UpdateTaskInput input) throws Exception {
        Task task = taskRepository.findById(input.getId()).orElseThrow(Exception::new);
        task.setDescription(input.getDescription());
        task.setDone(input.isDone());
        return task;
    }

    //endregion

    //region Mapper

    private AppUser mapToAppUser(AppUserInput input) {
        AppUser appUser = new AppUser();
        appUser.setName(input.getName());
        appUser.setSurname(input.getSurname());
        //appUser.addToTodos(input.getTodos().stream().map(this::mapToTodo).collect(Collectors.toList()));
        return appUser;
    }




    //endregion


}

package de.kronscht.exploration.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.kronscht.exploration.model.AppUser;
import de.kronscht.exploration.model.Todo;
import de.kronscht.exploration.model.input.AppUserInput;
import de.kronscht.exploration.model.input.TodoInput;
import de.kronscht.exploration.model.input.UpdateTodoInput;
import de.kronscht.exploration.repository.AppUserRepository;
import de.kronscht.exploration.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Component
public class Mutation implements GraphQLMutationResolver {

    private AppUserRepository appUserRepository;

    private TodoRepository todoRepository;

    @Autowired
    public Mutation(AppUserRepository appUserRepository, TodoRepository todoRepository) {
        this.appUserRepository = appUserRepository;
        this.todoRepository = todoRepository;
    }

    public AppUser writeAppUser(AppUserInput appUserInput) {
        return appUserRepository.save(mapToAppUser(appUserInput));
    }

    //region Todo

    @Transactional
    public Todo updateTodo(UpdateTodoInput input) throws Exception {
        Todo todo = todoRepository.findById(input.getId()).orElseThrow(Exception::new);
        todo.setDescription(input.getDescription());
        todo.setDone(input.isDone());
        return todo;
    }

    //endregion

    //region Mapper

    private AppUser mapToAppUser(AppUserInput input) {
        AppUser appUser = new AppUser();
        appUser.setName(input.getName());
        appUser.setSurname(input.getSurname());
        appUser.addToTodos(input.getTodos().stream().map(this::mapToTodo).collect(Collectors.toList()));
        return appUser;
    }

    private Todo mapToTodo(TodoInput input) {
        Todo todo = new Todo();
        todo.setDescription(input.getDescription());
        todo.setDone(input.isDone());
        return todo;
    }


    //endregion


}

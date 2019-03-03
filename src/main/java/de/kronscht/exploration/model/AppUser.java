package de.kronscht.exploration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUser extends BaseEntity {

    private String name;
    private String surname;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Todo> todos;

    //region Helper

    public List<Todo> getTodos() {
        if (todos == null) {
            todos = new ArrayList<>();
        }
        return todos;
    }

    public void addToTodos(Todo todo) {

        todo.setAppUser(this);
        getTodos().add(todo);
    }

    public void addToTodos(List<Todo> todos) {
        todos.forEach(this::addToTodos);
    }

    //endregion

}

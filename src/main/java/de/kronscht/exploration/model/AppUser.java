package de.kronscht.exploration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUser extends BaseEntity {

    private String name;
    private String surname;

    // @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // private List<Task> todos;


    public AppUser(Long id) {
        super(id);
    }

    //region Helper

    /*
    public List<Task> getTodos() {
        if (todos == null) {
            todos = new ArrayList<>();
        }
        return todos;
    }

    public void addToTodos(Task todo) {

        todo.setAppUser(this);
        getTodos().add(todo);
    }

    public void addToTodos(List<Task> todos) {
        todos.forEach(this::addToTodos);
    }
    */
    //endregion

}

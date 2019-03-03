package de.kronscht.exploration.repository;

import de.kronscht.exploration.model.AppUser;
import de.kronscht.exploration.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findAllByAppUser(AppUser appUser);

}

package de.kronscht.exploration.model.input;

import de.kronscht.exploration.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TodoInput {

    private String description;
    private boolean done;
    private AppUserInput appUser;
}

package de.kronscht.exploration.model.input;

import lombok.*;

/**
 * @author Tobias on 03.03.2019
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SaveTaskInput {


    private Long id;
    private String description;
    private boolean done;
    private Long userId;

}

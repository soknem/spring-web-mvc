// TodoListDataSource.java
package app.springmvc.data;

import app.springmvc.model.Todo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class TodoListDataSource {

    @Bean
    public List<Todo> todoList() {
        return new ArrayList<>(Arrays.asList(
                new Todo(1L, "first task", "no des1", true, LocalDateTime.now()),
                new Todo(2L, "second task", "no des2", false, LocalDateTime.now()),
                new Todo(3L, "third task", "no des3", true, LocalDateTime.now())
        ));
    }
}

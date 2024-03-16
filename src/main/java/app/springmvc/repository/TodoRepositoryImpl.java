package app.springmvc.repository;
import app.springmvc.model.Todo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class TodoRepositoryImpl implements TodoRepository {
    private final AtomicLong idGenerator = new AtomicLong(1);
    public static LocalDateTime formatLocalDateTime(LocalDateTime dateTime) {
        String pattern="dd-MM-yyyy HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formattedDateTimeString = dateTime.format(formatter);
        return LocalDateTime.parse(formattedDateTimeString, formatter);
    }
    private final List<Todo> todoList = new ArrayList<>(Arrays.asList(
            new Todo(idGenerator.getAndIncrement(),"first task","no des1",true, LocalDateTime.now()),
            new Todo(idGenerator.getAndIncrement(),"second task","no des2",false, LocalDateTime.now()),
            new Todo(idGenerator.getAndIncrement(),"third task","no des3",true, LocalDateTime.now())
    ));

    @Override
    public List<Todo> getAllTodos() {
        return todoList;
    }

    @Override
    public Todo getTodoById(Long id) {
        Optional<Todo> todoOptional = todoList.stream().filter(todo -> todo.getId().equals(id)).findFirst();
        return todoOptional.orElse(null);
    }

    @Override
    public void addTodo(Todo todo) {
        todo.setId(idGenerator.getAndIncrement());
        todo.setCreatedAt(formatLocalDateTime(LocalDateTime.now()));
        todoList.add(todo);
    }

    @Override
    public void updateTodo(Todo todo) {
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getId().equals(todo.getId())) {
                todoList.set(i, todo);
                break;
            }
        }
    }

    @Override
    public void deleteTodoById(Long id) {
        todoList.removeIf(todo -> todo.getId().equals(id));
    }

    @Override
    public List<Todo> searchTodos(String task, boolean isDone) {
        List<Todo> searchResults = new ArrayList<>();
        for (Todo todo : todoList) {
            if (todo.getTask().contains(task) && todo.isDone() == isDone) {
                searchResults.add(todo);
            }
        }
        return searchResults;
    }
}
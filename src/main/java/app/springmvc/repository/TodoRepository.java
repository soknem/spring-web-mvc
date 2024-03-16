package app.springmvc.repository;
import app.springmvc.model.Todo;
import java.util.List;

public interface TodoRepository {
    List<Todo> getAllTodos();
    Todo getTodoById(Long id);
    void addTodo(Todo todo);
    void updateTodo(Todo todo);
    void deleteTodoById(Long id);
    List<Todo> searchTodos(String task, boolean isDone);
}

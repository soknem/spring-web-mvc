package app.springmvc.service;

import app.springmvc.model.Todo;
import app.springmvc.repository.TodoRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.getAllTodos();
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepository.getTodoById(id);
    }

    @Override
    public void addTodo(Todo todo) {
        todoRepository.addTodo(todo);
    }

    @Override
    public void updateTodo(Todo todo) {
        todoRepository.updateTodo(todo);
    }

    @Override
    public void deleteTodoById(Long id) {
        todoRepository.deleteTodoById(id);
    }

    @Override
    public List<Todo> searchTodosByTask(String task) {
        return null;
    }

    @Override
    public List<Todo> searchTodosByIsDone(Boolean isDone) {
        return null;
    }

    @Override
    public List<Todo> searchTodos(String task, boolean isDone) {
        return todoRepository.searchTodos(task, isDone);
    }
}
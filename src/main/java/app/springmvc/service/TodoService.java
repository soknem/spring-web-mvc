package app.springmvc.service;

import app.springmvc.model.Todo;

import java.util.List;

public interface TodoService {


    List<Todo> getAllTodos();

    Todo getTodoById(Long id);

    void addTodo(Todo todo);

    void updateTodo(Todo todo);

    void deleteTodoById(Long id);

    List<Todo> searchTodosByTask(String task);

    List<Todo> searchTodosByIsDone(Boolean isDone);

    List<Todo> searchTodos(String task, boolean isDone);
}

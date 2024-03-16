package app.springmvc.repository;

import app.springmvc.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class TodoRepositoryImpl implements TodoRepository {

    private final List<Todo> todoList;
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Autowired
    public TodoRepositoryImpl(List<Todo> todoList) {
        this.todoList = todoList;
    }

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
        return todoList.stream()
                .filter(todo -> todo.getTask().equals(task) && todo.isDone() == isDone)
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> searchTodosByTask(String task) {
        return todoList.stream()
                .filter(todo -> todo.getTask().contains(task))
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> searchTodosByIsDone(Boolean isDone) {
        return todoList.stream()
                .filter(todo -> todo.isDone() == isDone)
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> searchTodosByTaskContaining(String task) {
        return todoList.stream()
                .filter(todo -> todo.getTask().contains(task))
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> searchTodosByTaskContainingAndIsDone(String task, Boolean isDone) {
        return todoList.stream()
                .filter(todo -> todo.getTask().contains(task) && todo.isDone() == isDone)
                .collect(Collectors.toList());
    }
}

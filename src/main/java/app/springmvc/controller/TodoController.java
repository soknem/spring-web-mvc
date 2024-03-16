package app.springmvc.controller;
import app.springmvc.model.Todo;
import app.springmvc.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("")
    public String getAllTodos(Model model) {
        List<Todo> todos = todoService.getAllTodos();
        model.addAttribute("todos", todos);
        return "index";
    }

    @GetMapping("/new")
    public String addTodoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "add";
    }

    @PostMapping("/new")
    public String addTodoSubmit(@ModelAttribute Todo todo) {
        todoService.addTodo(todo);
        return "redirect:/todo";
    }

    @GetMapping("/edit/{id}")
    public String editTodoForm(@PathVariable Long id, Model model) {
        Todo todo = todoService.getTodoById(id);
        model.addAttribute("todo", todo);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editTodoSubmit(@ModelAttribute Todo todo) {
        todoService.updateTodo(todo);
        return "redirect:/todo";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodoById(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return "redirect:/todo";
    }

    @GetMapping("/search")
    public String searchTodos(@RequestParam(required = false) String task,
                              @RequestParam(required = false) Boolean isDone,
                              Model model) {
        List<Todo> todos;
        if (task == null || task.isEmpty()) {
            if (isDone != null) {
                todos = todoService.searchTodosByIsDone(isDone);
            } else {
                todos = todoService.getAllTodos();
            }
        } else {
            if (isDone != null) {
                todos = todoService.searchTodosByTaskContainingAndIsDone(task, isDone);
            } else {
                todos = todoService.searchTodosByTaskContaining(task);
            }
        }
        model.addAttribute("todos", todos);
        return "index"; // Return to the main todo page after searching
    }
}

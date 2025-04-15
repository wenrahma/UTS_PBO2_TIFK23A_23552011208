package com.example.demo.controller;

import com.example.demo.model.ToDo;
import com.example.demo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @GetMapping
    public String home(Model model, @RequestParam(defaultValue = "all") String filter) {
        List<ToDo> todos;

        // Filter data sesuai permintaan pengguna
        switch (filter) {
            case "completed":
                todos = toDoService.getAllCompletedTodos();
                break;
            case "notCompleted":
                todos = toDoService.getAllNotCompletedTodos();
                break;
            default:
                todos = toDoService.getAllTodos();
                break;
        }

        model.addAttribute("todos", todos);
        model.addAttribute("newTodo", new ToDo());
        model.addAttribute("filter", filter); // untuk highlight tombol filter aktif
        return "index";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute ToDo todo, @RequestParam(defaultValue = "all") String filter) {
        toDoService.saveTodo(todo);
        return "redirect:/?filter=" + filter;
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id, @RequestParam(defaultValue = "all") String filter) {
        toDoService.deleteTodo(id);
        return "redirect:/?filter=" + filter;
    }

    @PostMapping("/update/{id}")
    public String updateTodo(@PathVariable Long id, @RequestParam(defaultValue = "all") String filter) {
        Optional<ToDo> todo = toDoService.getTodoById(id);
        todo.ifPresent(t -> {
            t.setCompleted(!t.isCompleted());
            toDoService.saveTodo(t);
        });
        return "redirect:/?filter=" + filter;
    }
}

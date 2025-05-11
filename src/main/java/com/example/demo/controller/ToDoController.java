package com.example.demo.controller;

import com.example.demo.model.ToDo;
import com.example.demo.model.User;
import com.example.demo.repository.ToDoRepository;
import com.example.demo.service.ToDoService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @Autowired
    private UserService userService;

    @Autowired
private ToDoRepository toDoRepository;


    /**
     * Menampilkan halaman utama dengan daftar ToDo berdasarkan filter.
     */
    @GetMapping
    public String home(Model model, 
                       @RequestParam(defaultValue = "all") String filter, 
                       Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        List<ToDo> todos;

        switch (filter) {
            case "completed":
                todos = toDoService.getTodosByUserAndCompleted(user, true);
                break;
            case "notCompleted":
                todos = toDoService.getTodosByUserAndCompleted(user, false);
                break;
            default:
                todos = toDoService.getTodosByUser(user);
                break;
        }

        long total = todos.size();
        long completedCount = todos.stream().filter(ToDo::isCompleted).count();
        long notCompletedCount = total - completedCount;

        model.addAttribute("todos", todos);
        model.addAttribute("filter", filter);
        model.addAttribute("total", total);
        model.addAttribute("completed", completedCount);
        model.addAttribute("notCompleted", notCompletedCount);
        model.addAttribute("username", user.getUsername());

        return "todos";
    }

@GetMapping("/todos")
public String todosPage(Model model,
                        @RequestParam(defaultValue = "all") String filter,
                        Principal principal) {
    return home(model, filter, principal);
}


    /**
     * Menambahkan ToDo baru untuk user yang sedang login.
     */
    @PostMapping("/create")
    public String addTodo(@ModelAttribute ToDo todo, 
                          @RequestParam(defaultValue = "all") String filter, 
                          Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        todo.setUser(user);
        toDoService.saveTodo(todo);
        return "redirect:/?filter=" + filter;
    }

    @GetMapping("/todos/create")
public String showCreateForm(Model model) {
    model.addAttribute("todo", new ToDo());
    return "create";
}


    /**
     * Menghapus ToDo berdasarkan ID.
     */
    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id, 
                             @RequestParam(defaultValue = "all") String filter) {
        toDoService.deleteTodo(id);
        return "redirect:/?filter=" + filter;
    }

    /**
     * Mengubah status completed ToDo (toggle).
     */
    @PostMapping("/update/{id}")
    public String updateTodo(@PathVariable Long id, 
                             @RequestParam(defaultValue = "all") String filter) {
        Optional<ToDo> todoOpt = toDoService.getTodoById(id);
        todoOpt.ifPresent(todo -> {
            todo.setCompleted(!todo.isCompleted());
            toDoService.saveTodo(todo);
        });
        return "redirect:/?filter=" + filter;
    }

    @GetMapping("/edit/{id}")
public String showEditForm(@PathVariable("id") Long id,
                           Principal principal,
                           Model model) {
    ToDo todo = toDoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID tugas tidak ditemukan: " + id));

    if (!todo.getUser().getUsername().equals(principal.getName())) {
        return "redirect:/?unauthorized";
    }

    model.addAttribute("todo", todo);
    return "edit";
}


@PostMapping("/edit/{id}")
public String updateTask(@PathVariable("id") Long id,
                         @RequestParam("task") String task,
                         Principal principal) {
    ToDo todo = toDoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID tugas tidak ditemukan: " + id));

    if (!todo.getUser().getUsername().equals(principal.getName())) {
        return "redirect:/?unauthorized";
    }

    todo.setTask(task);
    toDoRepository.save(todo);
    return "redirect:/";
}

}


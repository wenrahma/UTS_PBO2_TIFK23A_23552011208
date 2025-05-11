package com.example.demo.service;

import com.example.demo.model.ToDo;
import com.example.demo.model.User;
import com.example.demo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    /**
     * Mengambil semua ToDo milik semua user.
     */
    public List<ToDo> getAllTodos() {
        return toDoRepository.findAll();
    }

    /**
     * Menyimpan atau memperbarui ToDo.
     */
    public void saveTodo(ToDo todo) {
        toDoRepository.save(todo);
    }

    /**
     * Menghapus ToDo berdasarkan ID.
     */
    public void deleteTodo(Long id) {
        toDoRepository.deleteById(id);
    }

    /**
     * Mengambil ToDo berdasarkan ID.
     */
    public Optional<ToDo> getTodoById(Long id) {
        return toDoRepository.findById(id);
    }

    /**
     * Mengambil semua ToDo yang telah selesai (semua user).
     */
    public List<ToDo> getAllCompletedTodos() {
        return toDoRepository.findByCompletedTrue();
    }

    /**
     * Mengambil semua ToDo yang belum selesai (semua user).
     */
    public List<ToDo> getAllNotCompletedTodos() {
        return toDoRepository.findByCompletedFalse();
    }

    /**
     * Mengambil semua ToDo milik user tertentu.
     */
    public List<ToDo> getTodosByUser(User user) {
        return toDoRepository.findByUser(user);
    }

    /**
     * Mengambil ToDo milik user tertentu berdasarkan status selesai.
     */
    public List<ToDo> getTodosByUserAndCompleted(User user, boolean completed) {
        return toDoRepository.findByUserAndCompleted(user, completed);
    }

    /**
     * Menghitung total ToDo dari semua user.
     */
    public long countAllTodos() {
        return toDoRepository.count();
    }

    /**
     * Menghitung jumlah ToDo selesai dari semua user.
     */
    public long countCompletedTodos() {
        return toDoRepository.countByCompletedTrue();
    }

    /**
     * Menghitung jumlah ToDo belum selesai dari semua user.
     */
    public long countNotCompletedTodos() {
        return toDoRepository.countByCompletedFalse();
    }
}

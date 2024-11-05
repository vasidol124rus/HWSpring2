package com.example.demo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

/**
 * Контролер пользователей
 */

@Controller

@RestController
public class UserController {

    /**
     * Объект сервиса для работы с пользователями
     */
    private final UserService userService;

    /**
     * Конструктор класса
     * @param userService сервис пользователей
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }



    /**
     * Получение всех пользователей
     * @param model модель для передачи данных в представление
     * @return представление со списком пользователей
     */
    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    /**
     * Создание нового пользователя
     * @param user объект пользователя
     * @return представление для создания пользователя
     */
    @GetMapping("/user-create")
    public String createUserForm(User user){return "user-create";}

    /**
     * Получение данных о новом пользователе с формы представления
     * @param user объект пользователя с заполненными полями без валидации
     * @return перенаправление на страницу со списком пользователей
     */
    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    /**
     * Удаление пользователя
     * @param id уникальный идентификатор пользователя
     * @return перенаправление на страницу со списком пользователей
     */
    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userService.deleteById(id);
        return "redirect:/users";
    }
    @GetMapping
    public String updateUserForm(@PathVariable("id") Integer id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

}
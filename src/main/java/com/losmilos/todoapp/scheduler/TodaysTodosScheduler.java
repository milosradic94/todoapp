package com.losmilos.todoapp.scheduler;

import com.losmilos.todoapp.entity.Todo;
import com.losmilos.todoapp.entity.User;
import com.losmilos.todoapp.notification.email.Email;
import com.losmilos.todoapp.notification.email.service.EmailServiceImpl;
import com.losmilos.todoapp.services.TodoService;
import com.losmilos.todoapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TodaysTodosScheduler {

    private final UserService userService;
    private final TodoService todoService;
    private final EmailServiceImpl emailService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void sendTodaysTodosEmail() {
        List<User> users = userService.getAll();
        users.forEach((user) -> {
            if(user.getEmail() != null) {
                List<Todo> todos = todoService.getAllForTodayFinished(user.getId());

                if(!todos.isEmpty()) {
                    String body = todos.stream().map(Todo::getName).collect(Collectors.joining(", "));
                    Email email = new Email(user.getEmail(), body, "TODOs for today");
                    emailService.sendMail(email);
                }
            }
        });
    }

}

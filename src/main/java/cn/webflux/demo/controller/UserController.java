package cn.webflux.demo.controller;

import cn.webflux.demo.entity.User;
import cn.webflux.demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Pi
 * @date 2021-05-04 16:35
 */
@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //根据id查询用户
    @GetMapping("/user/{id}")
    public Mono<User> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    //查询所有用户
    @GetMapping("/users")
    public Flux<User> getUsers() {
        return userService.getAllUser();
    }

    //添加用户
    @PostMapping("/user/add")
    public Mono<Void> addUser(@RequestBody User user) {
        Mono<User> userMono = Mono.just(user);
        return userService.saveUserInfo(userMono);
    }
}

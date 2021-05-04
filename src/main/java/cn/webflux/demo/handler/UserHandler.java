package cn.webflux.demo.handler;

import cn.webflux.demo.entity.User;
import cn.webflux.demo.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * @author Pi
 * @date 2021-05-04 16:52
 */
public class UserHandler {

    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    //处理数据为空的情况
    Mono<ServerResponse> notFount = ServerResponse.notFound().build();

    //根据id查询用户
    public Mono<ServerResponse> getUserId(ServerRequest serverRequest) {
        int userId = Integer.valueOf(serverRequest.pathVariable("id"));
        Mono<User> userMono = userService.getUserById(userId);
        return userMono
                .flatMap(person -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(fromObject(person)))
                .switchIfEmpty(notFount);
    }

    //查询所有用户
    public Mono<ServerResponse> getUsers(ServerRequest serverRequest) {
        Flux<User> users = this.userService.getAllUser();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(users, User.class);
    }

    //添加用户
    public Mono<ServerResponse> saveUser(ServerRequest serverRequest) {
        Mono<User> userMono = serverRequest.bodyToMono(User.class);
        return ServerResponse.ok().build(this.userService.saveUserInfo(userMono));
    }
}

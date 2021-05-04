package cn.webflux.demo.service.impl;

import cn.webflux.demo.entity.User;
import cn.webflux.demo.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Pi
 * @date 2021-05-04 15:49
 */
@Service
public class UserServiceImpl implements UserService {
    //使用内存数据库，map
    private static Map<Integer, User> users = new HashMap<>();

    public UserServiceImpl() {
        users.put(1, new User("lily", "男", 11));
        users.put(2, new User("jack", "女", 20));
        users.put(3, new User("tom", "女", 30));
    }

    //根据用户id查询
    @Override
    public Mono<User> getUserById(int id) {
        return Mono.justOrEmpty(this.users.get(id));
    }

    //查询所有用户
    @Override
    public Flux<User> getAllUser() {
        return Flux.fromIterable(this.users.values());
    }

    //添加用户
    @Override
    public Mono<Void> saveUserInfo(Mono<User> user) {
        return user.doOnNext(person -> {
            //向Map中放置值
            int id = users.size() + 1;
            users.put(id, person);
        }).thenEmpty(Mono.empty());//终止信号
    }
}

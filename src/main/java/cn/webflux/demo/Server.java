package cn.webflux.demo;

import cn.webflux.demo.handler.UserHandler;
import cn.webflux.demo.service.UserService;
import cn.webflux.demo.service.impl.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

/**
 * @author Pi
 * @date 2021-05-04 17:07
 */
public class Server {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.createReactorServer();
        System.out.println("enter to exit");
        System.in.read();
    }

    //1.创建路由
    public RouterFunction<ServerResponse> createRouter() {
        UserService userService = new UserServiceImpl();
        UserHandler userHandler = new UserHandler(userService);

        //设置路由：配置访问路径对应的服务
        return RouterFunctions.route(
                GET("/user/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::getUserId)
                .andRoute(GET("/users").and(accept(MediaType.APPLICATION_JSON)), userHandler::getUsers);

    }

    //创建服务器，完成适配
    public void createReactorServer() {
        //路由和Handler适配
        RouterFunction<ServerResponse> route = createRouter();

        HttpHandler handler = toHttpHandler(route);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);

        //创建服务器
        HttpServer httpServer = HttpServer.create();
        httpServer.handle(adapter).bindNow();
    }

}

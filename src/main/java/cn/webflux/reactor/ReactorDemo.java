package cn.webflux.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Pi
 * @date 2021-05-04 14:47
 */
public class ReactorDemo {

    public static void main(String[] args) {
        //使用just
        Flux.just(1, 2, 3).subscribe(System.out::println);
        Mono.just(1).subscribe(System.out::print);

//        //其他方法
//        Integer[] array = {1, 2, 3, 4};
//        Flux.fromArray(array);
//
//        List<Integer> list = Arrays.asList(array);
//        Flux.fromIterable(list);
//
//        Stream<Integer> stream = list.stream();
//        Flux.fromStream(stream);
    }
}

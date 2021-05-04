package cn.webflux.reactor;

import java.util.Observable;

/**
 * @author Pi
 * @date 2021-05-04 11:40
 */
public class ObserverDemo extends Observable {

    public static void main(String[] args) {
        ObserverDemo observerDemo = new ObserverDemo();
        //添加观察者
        observerDemo.addObserver((o, arg) -> {
            System.out.println("发生变化....");
        });

        observerDemo.addObserver((o, arg) -> {
            System.out.println("收到被观察者通知，准备改变！");
        });

        observerDemo.setChanged();//数据变化
        observerDemo.notifyObservers();//通知

    }
}

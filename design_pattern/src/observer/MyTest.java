package observer;

import java.util.ArrayList;
import java.util.List;

public class MyTest {
    public static void main(String[] args) {
        Subject subject = new Subject();
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        subject.add(task1);
        subject.add(task2);

        subject.notifyObservers("xxx");
        System.out.println("-----------------------");
        subject.remove(task1);
        subject.notifyObservers("yyy");

    }
}

/**
 * Task1 received: xxx
 * Task2 received: xxx
 * -----------------------
 * Task2 received: yyy
 */

class Subject{
    List<Observer> observers = new ArrayList<>();
    public void add(Observer observer){
        observers.add(observer);
    }
    public void remove(Observer observer){
        observers.remove(observer);
    }
    public void notifyObservers(Object data){
        for (Observer observer: observers) {
            observer.update(data);
        }
    }
}

interface Observer{
    void update(Object obj);
}

class Task1 implements Observer{
    @Override
    public void update(Object obj) {
        System.out.println("Task1 received: "+obj);
    }
}

class Task2 implements Observer{
    @Override
    public void update(Object obj) {
        System.out.println("Task2 received: "+obj);
    }
}
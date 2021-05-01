package org.hzz.test;

public abstract class T1 {
    protected String name = "name1";
    public void hello(){
        helloName();
        System.out.println(name);
    }

    public void helloName(){
        System.out.println("Hello T1");
    }
}

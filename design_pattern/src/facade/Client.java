package facade;

public class Client {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.doSomethingFacade();
    }
}

/**
 * 子系统1
 * 子系统2
 * 子系统3
 */

class Facade{
    private SubSystem1 sub1 = new SubSystem1();
    private SubSystem2 sub2 = new SubSystem2();
    private SubSystem3 sub3 = new SubSystem3();

    public void doSomethingFacade(){
        sub1.method1();
        sub2.method2();
        sub3.method3();
    }
}

class SubSystem1{
    public void method1(){
        System.out.println("子系统1");
    }
}
class SubSystem2{
    public void method2(){
        System.out.println("子系统2");
    }
}
class SubSystem3{
    public void method3(){
        System.out.println("子系统3");
    }
}




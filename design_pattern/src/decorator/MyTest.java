package decorator;



public class MyTest {
    public static void main(String[] args) {
        Component component = new ConcreteComponent2(
                new ConcreteComponent1(
                        new ConcreteComponent()
                )
        );

        component.operation();
    }
}

interface Component{
    void operation();
}

class ConcreteComponent implements Component{
    @Override
    public void operation() {
        System.out.println("拍照");
    }
}

//  并没有实现该接口，延迟具体实现
abstract class Decorator implements Component{
    protected Component component;
    public Decorator(Component component){
        this.component = component;
    }
}


class ConcreteComponent1 extends Decorator{
    public ConcreteComponent1(Component component){
        super(component);
    }

    @Override
    public void operation() {
        System.out.println("添加美颜");
        component.operation();
    }
}

class ConcreteComponent2 extends Decorator{
    public ConcreteComponent2(Component component){
        super(component);
    }

    @Override
    public void operation() {
        System.out.println("添加滤镜");
        component.operation();
    }
}




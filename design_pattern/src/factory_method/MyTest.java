package factory_method;

public class MyTest {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreatorProductA();
        creator.createObject().operation();
    }
}


interface Product{
    void operation();
}

abstract class Creator{
    abstract Product createObject();

    public Product getProduct(){
        //  ...其他业务 ...
        return createObject();
    }
}


class ProductA implements Product{
    @Override
    public void operation() {
        System.out.println("productA");
    }
}
class ProductB implements Product{
    @Override
    public void operation() {
        System.out.println("productB");
    }
}


class ConcreteCreatorProductA extends Creator{
    @Override
    Product createObject() {
        return new ProductA();
    }
}

class ConcreteCreatorProductB extends Creator{

    @Override
    Product createObject() {
        return new ProductB();
    }
}













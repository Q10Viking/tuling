package strategy;

public class MyTest {
    public static void main(String[] args) {
        Zombie zombie = new NormalZombie();
        zombie.display();
        zombie.move();
        zombie.attack();
        //  更换策略
        System.out.println("----------升级---------------");
        zombie.setAttackable(new HiteAttack());
        zombie.attack();
    }
}

/**
 * 我是普通僵尸
 * 一步一步前进
 * 咬
 * ----------升级---------------
 * 打
 */

interface Moveable{
    void move();
}

interface Attackable{
    void attack();
}
//  提取共性
abstract class Zombie{
    protected Moveable moveable;
    protected Attackable attackable;

    public Zombie(Moveable moveable, Attackable attackable) {
        this.moveable = moveable;
        this.attackable = attackable;
    }

    abstract void display();
    abstract void move();
    abstract void attack();

    public Moveable getMoveable() {
        return moveable;
    }

    public void setMoveable(Moveable moveable) {
        this.moveable = moveable;
    }

    public Attackable getAttackable() {
        return attackable;
    }

    public void setAttackable(Attackable attackable) {
        this.attackable = attackable;
    }
}
//  具体策略实现
class StepByStepMove implements Moveable{
    @Override
    public void move() {
        System.out.println("一步一步前进");
    }
}

class BiteAttack implements Attackable{
    @Override
    public void attack() {
        System.out.println("咬");
    }
}

class HiteAttack implements Attackable{
    @Override
    public void attack() {
        System.out.println("打");
    }
}
//  -----------具体僵尸------------------
class NormalZombie extends Zombie{
    public NormalZombie(){
        super(new StepByStepMove(),new BiteAttack());
    }

    public NormalZombie(Moveable moveable, Attackable attackable) {
        super(moveable, attackable);
    }

    @Override
    void display() {
        System.out.println("我是普通僵尸");
    }

    @Override
    void move() {
        moveable.move();
    }

    @Override
    void attack() {
        attackable.attack();
    }
}


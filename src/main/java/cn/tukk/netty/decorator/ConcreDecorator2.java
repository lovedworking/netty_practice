package cn.tukk.netty.decorator;

/**
 * @program: netty_practice
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-07-11 11:33
 **/
public class ConcreDecorator2 extends Decorator {


    public ConcreDecorator2(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherThing();
    }

    private void doAnotherThing() {
        System.out.println("装饰者2  增加C");
    }
}

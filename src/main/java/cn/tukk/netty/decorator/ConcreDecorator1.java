package cn.tukk.netty.decorator;

/**
 * @program: netty_practice
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-07-11 11:30
 **/
public class ConcreDecorator1 extends Decorator {


    public ConcreDecorator1(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherSomeThing();
    }

    private void doAnotherSomeThing() {
        System.out.println("装饰者1 增加功能B");
    }
}

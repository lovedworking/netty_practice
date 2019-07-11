package cn.tukk.netty.decorator;

/**
 * @program: netty_practice
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-07-11 11:34
 **/
public class Client {
    public static void main(String[] args) {
        Component component = new ConcreDecorator1(new ConcreDecorator2(new ConcreteComponet()));
        Component component2 = new ConcreDecorator2(new ConcreDecorator1(new ConcreteComponet()));
        component.doSomething();
        component2.doSomething();
    }
}

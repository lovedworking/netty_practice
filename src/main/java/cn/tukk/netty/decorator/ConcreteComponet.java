package cn.tukk.netty.decorator;

/**
 * @program: netty_practice
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-07-11 11:06
 **/
public class ConcreteComponet implements Component {
    @Override
    public void doSomething() {
        System.out.println("原始 做A");
    }
}

package cn.tukk.netty.reactor;

/**
 * @program: netty_practice
 * @description  Reactor 相关介绍
 *
 *           论文地址：http://www.yeolar.com/note/2012/12/09/reactor/#topic-list
 *           nio reactor 相关书籍地址：https://www.cnblogs.com/luxiaoxun/archive/2015/03/11/4331110.html
 *           http://gee.cs.oswego.edu/dl/cpjslides/nio.pdf
 * @author: tkk fendoukaoziji
 * @create: 2019-07-19 11:33
 **/
public interface ReactorRole {

    /*****
     *
     *
     Reactor模式
     1、Handler  句柄或描述符
             本质上是一种资源，由操作系统提供的，该资源表示一个个的事件，比如文件描述符，或是针对网络编程中的
             Socket 描述符。事件可以来自内部，也可以来自外部；外部事件如客户端的请求，客户端发送过来的数据；
             内部事件如操作系统产生的定时器事件等。
             Handler是事件发生的发源地。
     2、Synchronous Event Demultiplexer  同步事件分离器
            本身是一个系统调用，用于等待事件的发生，（事件可能是一个，也可能是多个），调用方调用它的时候会阻塞，一直
            阻塞到同步分离器上有事件发生
            对于Linux来说 就是常用I/O多路复用机制，比如Selector，poll,epoll等，
            Java NIO中对应就是Selector,对应阻塞方法是select()
     3、Event Handler （事件处理器）
            本身由多个回调方法组成，这些回调方法构成了与应用相关的对于某个事件的反馈机制，Netty相比于java nio来说，在
            事件处理器这个角色进行了一个升级，它为我们开发者提供大量的回调方法，供我们在特定事件产生时实现相应的回调方法
            进行业务逻辑的处理       SimpleChannelInboundHandler
     4、Concrete Event Handler （具体事件处理器)
            事件处理器的一个实现，本身实现了事件处理器所提供的各个回调方法，从而实现特定业务的逻辑
            本质上就是我们编写的一个个处理器实现       MyServerHandler
     5、Initiation Dispatcher(初始分发器）
            实际上就是Reactor角色，本身定义了一些规范，这些规范用于控制事件的调度方式，同时又提供了应用进行事件处理器的
            注册、删除等设施。它本身是事件处理器的核心所在，Initiation Dispatcher 会通过同步事件分离器来等待事件的发生，
            一旦事件发生，Initiation Dispatcher首先会分离出每一个事件，最后调用相关回调方法来处理这些事件

     Reactor模式流程
     1、当应用向Initiation Dispatcher 注册具体的事件处理器时，应用会标识出该事件处理器希望Initiation Dispatcher 在某个事件
     发生时向其通知该事件，该事件与Handle关联
     2、Initiation Dispathcer 会要求每个事件处理器向其传递内部的Handle,该Handle向操作系统标识了事件处理器。
     3、当所有的事件处理器注册完毕后，应用会调用handle_events方法来启动Initiation Dispathcer的事件循环。这时，Initiation
     Dispathcer 会将每个注册事件管理器的Handle 合并起来，并使用同步事件分离器等待这些事件的发生，比如说TCP协议层会使用Select
     同步事件分离器来等待客户端发送数据到达连接的socket handle上
     4、当与某个事件源对应的Handle变为ready状态时（比如说，socket变为等待读转态时），同步事件分离器就会通知Initiation Dispathcer
     5、Initiation Dispatcher 会触发事件处理器的回调方法，从而响应这个处于ready状态的Handle,当事件发生时，Initiation Dispatcher
     会将事件源激活的Handler作为[key]来寻找并分发恰当的事件处理器回调方法
     6、Initiation Dispatcher 会回调事件处理器的handler_events 回调方法来执行特定于应用的功能（开发者自己编写的功能），从而响应这个
     事件，所发生的事件类型可以作为该方法参数并被该方法内部使用来执行额外的特定于服务的分离与分发。












     *
     *
     *
     *
     *
     *
     *
     *
     */














}

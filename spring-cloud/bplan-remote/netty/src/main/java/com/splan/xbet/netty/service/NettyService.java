package com.splan.xbet.netty.service;

import com.splan.xbet.netty.initializer.MyChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyService {

    public NettyService(int port) {
        /*new Thread(() -> {*/
        //Integer port = (Integer)SpringUtil.getBean("netty.port");
        System.out.println("启动Netty!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(port)
                    .childHandler(new MyChannelInitializer());
            //Channel channel = serverBootstrap.bind().sync();
            ChannelFuture cf = serverBootstrap.bind().sync(); // 服务器异步创建绑定
            System.out.println(NettyService.class + " 启动正在监听： " + cf.channel().localAddress());

            cf.channel().closeFuture().sync();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        /*}).start();*/
    }


}

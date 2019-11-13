package com.lw.springcloud.eurekaclient.netty.firstunit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {

    public static void main(String[] args) throws Exception {
        //事件循环组，NioEventLoopGroup本质是一个死循环，不停的工作
        //负责接收客户端的连接，接收到后转发给workerGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //负责处理实际的工作
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //用于启动服务端
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //channel：管道; childHandler:请求到达后实际处理的handler
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(9635).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            //优雅关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}

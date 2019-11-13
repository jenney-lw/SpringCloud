package com.lw.springcloud.eurekaclient.netty.firstunit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //一个管道上面可以有多个handler
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        pipeline.addLast("testServerHttpHandler", new TestServerHttpHandler());

    }
}

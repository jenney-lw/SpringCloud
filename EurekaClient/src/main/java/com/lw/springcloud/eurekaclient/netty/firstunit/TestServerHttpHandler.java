package com.lw.springcloud.eurekaclient.netty.firstunit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class TestServerHttpHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        System.out.println(httpObject.getClass());
        System.out.println(channelHandlerContext.channel().remoteAddress());

        if (httpObject instanceof HttpRequest) {
            System.out.println("进入channelRead0");

            HttpRequest httpRequest = (HttpRequest) httpObject;
            System.out.println("请求方法名:" + httpRequest.method().name());

            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("请求favicon.ico");
                return;
            }

            System.out.println("uri:" + httpRequest.method().name());
            //读取客户端发送的请求，并向客户端返回响应
            //构造返回内容
            ByteBuf content = Unpooled.copiedBuffer("netty", CharsetUtil.UTF_8);
            FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            channelHandlerContext.writeAndFlush(fullHttpResponse);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered");
        super.channelUnregistered(ctx);
    }
}

package org.amqueue;

import static org.amqueue.config.Config.CONNECTOR_THREADS;
import static org.amqueue.config.Config.SERVER_HOST;
import static org.amqueue.config.Config.SERVER_PORT;
import static org.amqueue.config.Config.WORKER_THREADS;

import org.amqueue.config.ConfigReader;
import org.amqueue.io.InputHandler;
import org.amqueue.io.OutputHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

public class ClientMain {
    
    public void start() throws Exception {
        EventLoopGroup loop = null;
        try {
            ConfigReader cr = ConfigReader.getInstance();
            loop = new NioEventLoopGroup(20);
            Bootstrap boot = new Bootstrap();
            boot.group(loop)
                .channel(OioServerSocketChannel.class)
                .remoteAddress(cr.getPropertyAsString(SERVER_HOST), cr.getPropertyAsInt(SERVER_PORT))
                .handler(new ChannelInitializer<SocketChannel>(){
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                        .addLast(new InputHandler())
                        .addLast(new OutputHandler());
                    }});
            ChannelFuture f = boot.connect().sync();
            f.channel().closeFuture().sync();
        }
        finally {
            if (loop != null) {
                loop.shutdownGracefully().sync();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new ClientMain().start();
    }

}

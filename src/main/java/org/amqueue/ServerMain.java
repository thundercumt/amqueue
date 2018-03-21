package org.amqueue;

import org.amqueue.config.ConfigReader;
import org.amqueue.io.InputHandler;
import org.amqueue.io.OutputHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import static org.amqueue.config.Config.*;

public class ServerMain {

    public ServerMain() {
    }

    public void start() throws Exception {
        EventLoopGroup connector = null;
        EventLoopGroup worker = null;
        try {
            ConfigReader cr = ConfigReader.getInstance();
            connector = new NioEventLoopGroup(cr.getPropertyAsInt(CONNECTOR_THREADS));
            worker = new NioEventLoopGroup(cr.getPropertyAsInt(WORKER_THREADS));
            ServerBootstrap boot = new ServerBootstrap();
            boot.group(connector, worker)
                .channel(NioServerSocketChannel.class)
                .localAddress(cr.getPropertyAsString(SERVER_HOST), cr.getPropertyAsInt(SERVER_PORT))
                .childHandler(new ChannelInitializer<SocketChannel>(){
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                        .addLast(new InputHandler())
                        .addLast(new OutputHandler());
                    }});
            ChannelFuture f = boot.bind().sync();
            f.channel().closeFuture().sync();
        }
        finally {
            if (connector != null) {
                connector.shutdownGracefully().sync();
            }
            if (worker != null) {
                worker.shutdownGracefully().sync();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ServerMain server = new ServerMain();
        server.start();
    }

}

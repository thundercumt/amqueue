package org.amqueue;

import org.amqueue.config.ConfigReader;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import static org.amqueue.config.Config.*;

public class ServerMain {

    public ServerMain() {
    }

    public void start() throws Exception {
        ConfigReader cr = ConfigReader.getInstance();
        EventLoopGroup connector = new NioEventLoopGroup(cr.getPropertyAsInt(CONNECTOR_THREADS));
        EventLoopGroup worker = new NioEventLoopGroup(cr.getPropertyAsInt(WORKER_THREADS));
        ServerBootstrap boot = new ServerBootstrap();
        boot.group(connector, worker)
            .channel(NioServerSocketChannel.class)
            .localAddress(cr.getPropertyAsString(SERVER_HOST), cr.getPropertyAsInt(SERVER_PORT));

    }

    public static void main(String[] args) throws Exception {
        ServerMain server = new ServerMain();
        server.start();
    }

}

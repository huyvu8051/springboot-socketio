/**
 * @Author HuyVu
 * @CreatedDate 2/24/2023 1:41 PM
 */
package io.huyvu.springbootsocketio.config.socketio;

import io.socket.engineio.server.EngineIoServer;
import io.socket.engineio.server.EngineIoServerOptions;
import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import io.socket.socketio.server.SocketIoSocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BeanConfig {

    @Bean
    EngineIoServer engineIoServer() {
        EngineIoServerOptions opt = EngineIoServerOptions.newFromDefault();
        opt.setCorsHandlingDisabled(true);
        EngineIoServer eioServer = new EngineIoServer(opt);
        return eioServer;
    }

    @Bean
    SocketIoServer socketIoServer(EngineIoServer eioServer) {
        SocketIoServer sioServer = new SocketIoServer(eioServer);

        SocketIoNamespace namespace = sioServer.namespace("/mynamespace");

        namespace.on("connection", args -> {
            SocketIoSocket socket = (SocketIoSocket) args[0];
            System.out.println("Client " + socket.getId() + " (" + socket.getInitialHeaders().get("remote_addr") + ") has connected.");

            socket.on("message", args1 -> {
                System.out.println("[Client " + socket.getId() + "] " + List.of(args1));
                socket.send("hello", "Heo khô đi những kỉ niệm xưa kia");
            });
        });

        return sioServer;
    }

}

package io.huyvu.springbootsocketio.config.socketio;

import io.socket.socketio.server.SocketIoSocket;
import org.json.JSONObject;

public class SocketUtils {
    public static String getUserId(SocketIoSocket socket){
        var connectData1 = socket.getConnectData();
        if (connectData1 instanceof JSONObject jo) {
            return jo.getString("uId");
        }
        return null;
    }
}

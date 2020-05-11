package mayton.network;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.impl.AMQConnection;
import com.rabbitmq.client.impl.ConnectionParams;
import com.rabbitmq.client.impl.FrameHandler;
import com.rabbitmq.client.impl.SocketFrameHandler;

import java.io.IOException;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {

        Connection connection = new AMQConnection(
                new ConnectionParams(),
                new SocketFrameHandler(new Socket()));

        connection.close();
    }

}

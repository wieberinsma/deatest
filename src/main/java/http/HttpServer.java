package http;

import java.io.IOException;
import java.net.ServerSocket;

public class HttpServer
{
    private final int tcpPort;

    public HttpServer(int tcpPort)
    {
        this.tcpPort = tcpPort;
    }

    public static void main(String[] args)
    {
        new HttpServer(8383).startServer();
    }

    public void startServer()
    {
        try (
                var serverSocket = new ServerSocket(this.tcpPort);
        )
        {
            System.out.println("Server accepting requests on port " + tcpPort);

            while (!serverSocket.isClosed())
            {
                var acceptedSocket = serverSocket.accept();
                var connectionHandler = new ConnectionHandler(acceptedSocket);
                var connection = new Thread(connectionHandler::handle);
                connection.start();
            }
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}

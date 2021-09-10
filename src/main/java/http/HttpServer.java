package http;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

public class HttpServer
{
    private final int tcpPort;

    private static final List<String> HTTP_METHODS = List.of("GET", "HEAD", "PUT", "POST", "DELETE", "CONNECT",
            "OPTIONS", "TRACE", "PATCH");

    private static final List<String> SUPPORTED_HTTP_METHODS = HTTP_METHODS.subList(0, 2);

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
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getHttpMethods()
    {
        return HTTP_METHODS;
    }

    public static List<String> getSupportedHttpMethods()
    {
        return SUPPORTED_HTTP_METHODS;
    }
}

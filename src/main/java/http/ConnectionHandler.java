package http;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ConnectionHandler
{
    private static final String HTTP_HEADERS = "HTTP/1.1 200 OK\n" +
            "Date: Mon, 27 Aug 2018 14:08:55 +0200\n" +
            "HttpServer: Simple DEA Webserver\n" +
            "Content-Length: 190\n" +
            "Content-Type: text/html\n";

    private final Socket socket;

    public ConnectionHandler(Socket socket)
    {
        this.socket = socket;
        handle();
    }

    public void handle()
    {
        try
        {
            var inputStreamReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), StandardCharsets.US_ASCII));
            var outputStreamWriter = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.US_ASCII));

            parseRequest(inputStreamReader);
            writeResponse(outputStreamWriter);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void parseRequest(BufferedReader inputStreamReader) throws IOException
    {
        var request = inputStreamReader.readLine();

        while (request != null && !request.isEmpty())
        {
            System.out.println(request);
            request = inputStreamReader.readLine();
        }
    }

    private void writeResponse(BufferedWriter outputStreamWriter)
    {
        try
        {
            outputStreamWriter.write(HTTP_HEADERS);
            outputStreamWriter.newLine();

            var pageReader = new HtmlPageReader();
            outputStreamWriter.write(pageReader.readFile("index.html"));

            outputStreamWriter.newLine();
            outputStreamWriter.flush();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }


}

package httpserver;

import httpserver.exceptions.InvalidRequestException;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ConnectionHandler
{
    private final BufferedReader inputReader;

    private final BufferedWriter outputWriter;

    public ConnectionHandler(Socket clientSocket)
    {
        try
        {
            final var socketReader = new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.US_ASCII);
            final var socketWriter = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.US_ASCII);

            inputReader = new BufferedReader(socketReader);
            outputWriter = new BufferedWriter(socketWriter);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }

        handle();
    }

    public void handle()
    {
        try
        {
            var startLineArgs = handleRequest();

            if (startLineArgs.length == 0)
            {
                throw new InvalidRequestException("Invalid request arguments.");
            }

            handleResponse(startLineArgs);
        }
        catch (IOException | InvalidRequestException ex)
        {
            System.out.println("Invalid Http request: " + ex.getMessage());
        }
    }

    private String[] handleRequest() throws IOException
    {
        String[] startLineArgs = new String[0];

        var requestPart = inputReader.readLine();

        while (inputReader.ready())
        {
            System.out.println(requestPart);

            if (isStartLine(requestPart))
            {
                startLineArgs = requestPart.split(" ");
            }

            requestPart = inputReader.readLine();
        }

        return startLineArgs;
    }

    private boolean isStartLine(String requestPart)
    {
        return HttpServer.getHttpMethods().stream().anyMatch(requestPart::contains);
    }

    private void handleResponse(String[] startLineArgs) throws IOException
    {
        var headers = new HttpResponseHeaders();
        int httpStatus = 200;

        String httpMethod = startLineArgs[0];
        String requestTarget = startLineArgs[1];
        String protocol = startLineArgs[2];

        try
        {
            String payload = "";

            if (!protocol.startsWith("HTTP/1"))
            {
                httpStatus = 505;
                setHeaderValues(headers, protocol, httpStatus, 0);
                writeResponse(headers, payload);
            }
            else if (!HttpServer.getSupportedHttpMethods().contains(httpMethod))
            {
                httpStatus = 501;
                setHeaderValues(headers, protocol, httpStatus, 0);
                writeResponse(headers, payload);
            }
            else if (httpMethod.equals("HEAD"))
            {
                setHeaderValues(headers, protocol, httpStatus, 0);
                writeResponse(headers, payload);
            }
            else
            {
                try
                {
                    payload = getPayload(requestTarget);
                }
                catch (IOException e)
                {
                    httpStatus = 404;
                    String error = "404: Page does not exist.";
                    setHeaderValues(headers, protocol, httpStatus, error.length());
                    writeResponse(headers, error);

                    return;
                }

                setHeaderValues(headers, protocol, httpStatus, payload.length());
                writeResponse(headers, payload);
            }
        }
        catch (Exception ex)
        {
            httpStatus = 500;
            setHeaderValues(headers, protocol, httpStatus, 0);
            writeResponse(headers, "");
        }
    }

    private void setHeaderValues(HttpResponseHeaders headers, String protocol, int httpStatus, int contentLength)
    {
        headers.setHttpProtocol(protocol);
        headers.setHttpStatus(httpStatus);
        headers.setContentLengthValue(contentLength);
    }

    private String getPayload(String requestTarget) throws IOException
    {
        String payload;

        var uriPath = requestTarget.substring(requestTarget.lastIndexOf("/"));
        var pageReader = new HtmlPageReader();

        payload = pageReader.readFile(uriPath);

        return payload;
    }

    private void writeResponse(HttpResponseHeaders headers, String payload) throws IOException
    {
        outputWriter.write(headers.getHeaders());
        outputWriter.newLine();
        outputWriter.write(payload);
        outputWriter.newLine();
        outputWriter.flush();
    }
}

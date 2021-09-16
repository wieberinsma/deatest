package patterns.factorybuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpInvokerApplication
{
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static void main(String[] args)
    {
        RemoteInvoker invoker = HttpInvoker.with(httpClient).build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://github.com"))
                .build();

        invoker.invoke(request, HttpResponse.class).thenAccept(response -> System.out.println(response.body()));

        System.out.println("Waiting for response...");

        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            //ignored
        }
    }
}

package patterns.factorybuilder;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HttpInvokerImpl implements HttpInvoker
{
    private final HttpClient client;

    public HttpInvokerImpl(HttpClient client)
    {
        this.client = client;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <RQ extends HttpRequest, RS extends HttpResponse<?>> CompletableFuture<RS> invoke(RQ request,
            Class<RS> responseClass)
    {
        return (CompletableFuture<RS>) client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }
}

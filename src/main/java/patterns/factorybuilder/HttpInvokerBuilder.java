package patterns.factorybuilder;

import java.net.http.HttpClient;

public class HttpInvokerBuilder
{
    private HttpClient client;

    public HttpInvokerBuilder client(HttpClient client)
    {
        this.client = client;
        return this;
    }

    public HttpInvoker build()
    {
        return new HttpInvokerImpl(client);
    }
}

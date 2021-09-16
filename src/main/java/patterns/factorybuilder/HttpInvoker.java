package patterns.factorybuilder;

import java.net.http.HttpClient;

public interface HttpInvoker extends RemoteInvoker
{
    static HttpInvokerBuilder with(HttpClient client)
    {
        return new HttpInvokerBuilder().client(client);
    }
}

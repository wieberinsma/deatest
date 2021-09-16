package patterns.factorybuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public interface RemoteInvoker
{
    <RQ extends HttpRequest, RS extends HttpResponse<?>> CompletableFuture<RS> invoke(RQ request,
            Class<RS> responseClass);
}

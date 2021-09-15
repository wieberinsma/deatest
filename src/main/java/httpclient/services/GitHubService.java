package httpclient.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GitHubService
{
    private final HttpClient gitHubClient = HttpClient.newHttpClient();

    public String getIndexHtml()
    {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://github.com"))
                .build();

        try
        {
            return gitHubClient.send(getRequest, HttpResponse.BodyHandlers.ofString()).body();
        }
        catch (IOException | InterruptedException e)
        {
            return "";
        }
    }

    public String getReadme()
    {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://raw.githubusercontent.com/HANICA-DEA/exercise-http-client/master/README.md"))
                .build();

        try
        {
            return gitHubClient.send(getRequest, HttpResponse.BodyHandlers.ofString()).body();
        }
        catch (IOException | InterruptedException e)
        {
            return "";
        }
    }
}

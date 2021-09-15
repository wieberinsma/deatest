package httpserver;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class HttpResponseHeaders
{
    private String httpProtocol = "";

    private int httpStatus = 200;

    private int contentLengthValue = 0;

    public String getHttpProtocol()
    {
        return httpProtocol;
    }

    public void setHttpProtocol(String httpProtocol)
    {
        this.httpProtocol = httpProtocol;
    }

    public int getHttpStatus()
    {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus)
    {
        this.httpStatus = httpStatus;
    }

    public int getContentLengthValue()
    {
        return contentLengthValue;
    }

    public void setContentLengthValue(int contentLengthValue)
    {
        this.contentLengthValue = contentLengthValue;
    }

    public String getHeaders()
    {
        return getHttpProtocol() + " " + getHttpStatus() + "\n" +
                "Date: " + getDateValue() + "\n" +
                "HttpServer: Simple DEA Webserver\n" +
                "Content-Length: " + getContentLengthValue() + "\n" +
                "Content-Type: text/html\n";
    }

    public String getDateValue()
    {
        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME.withZone(ZoneId.of("GMT"));
        return OffsetDateTime.now().format(formatter);
    }
}

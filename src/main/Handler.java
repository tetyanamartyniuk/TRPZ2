package src.main;

import src.main.HttpRequest;
import src.main.HttpResponse;

public interface Handler {
    public HttpResponse handle(HttpRequest request);
}

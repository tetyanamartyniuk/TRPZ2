package src.main;


public interface HttpRoute {
    HttpResponse execute(HttpRequest request);
}

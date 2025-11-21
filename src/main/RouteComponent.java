package src.main;

public interface RouteComponent {
    public HttpResponse handle (HttpRequest request);
    public String getPath();
}

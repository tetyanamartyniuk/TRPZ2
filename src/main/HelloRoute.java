package src.main;

import java.util.HashMap;
import java.util.Map;

public class HelloRoute implements HttpRoute{
    @Override
    public HttpResponse execute(HttpRequest request){

        return HttpResponseDirector.Ok("Hello, world", null);
    }
}

package src.main;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class ThymeleafRenderer {

    private final TemplateEngine engine;

    public ThymeleafRenderer() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode("HTML");

        engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
    }

    public String render(String viewName, Context ctx) {
        return engine.process(viewName, ctx);
    }
}


package mayton.probe.spring.probespringsecurity;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {SecSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // TODO:
        return new Class[0];
    }


    @Override
    protected String[] getServletMappings() {
        // TODO:
        return new String[0];
    }
}

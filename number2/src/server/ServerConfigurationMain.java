package server;

import org.eclipse.jetty.server.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author jcastillo
 *
 */
public class ServerConfigurationMain {

    @Configuration
    public static class Config {
        @Bean
        public ServerService serverService() throws Exception {
            ServerService serverService = new ServerServiceImpl();
            Server server = serverService.createWebServer();
            serverService.start(server);
			return serverService;
        } 
    }

    public static void main(String[] args) {
     
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        ctx.start();
        ctx.registerShutdownHook();
        ctx.close();
    }


}

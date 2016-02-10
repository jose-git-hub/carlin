package server;

import org.eclipse.jetty.server.Server;

public interface ServerService {

	Server createWebServer() throws Exception;
	
	void start(Server server) throws Exception;

}

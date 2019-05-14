package presenter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import static java.lang.System.exit;

/**
 * Connection handler for the communication with the Webservice
 */
public class WebserviceConnector implements Runnable {
	IWebserviceHandler webserviceHandler;
	int port;
	static Charset charset = Charset.forName("UTF-8");
	ServerSocket ss;
	public String STATUS = "No client connected";

	public WebserviceConnector(IWebserviceHandler webserviceHandler, int port) {
		this.webserviceHandler = webserviceHandler;
		this.port = port;
	}

	/**
	 * Calls {@link #listen(int)}
	 */
	@Override
	public void run() {
		try {
			listen(port);
		} catch (IOException e) {
			e.printStackTrace();
			exit(1);
		}
	}

	/**
	 * Listens constantly for socket requests. When a new socket is established,
	 * it kills the old one, assuming it is no longer used by the client.
	 * @param port The port for listening.
	 * @throws IOException On socket errors
	 */
	private void listen(int port) throws IOException {
		ss = TlsSocketFactory.getInstance().getServerSocket(port);

		Socket socket;
		SocketReader sr = null;
		while (true) {
			System.out.println("Listening for new connection");
			socket = ss.accept();
			STATUS = "Client is connected";

			if(sr != null) {
				sr.kill();
			}

			sr = new SocketReader(socket, webserviceHandler);
			Server.executorService.submit(sr);
		}
	}
}
package presenter;

import javax.net.ServerSocketFactory;
import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.security.SecureRandom;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.exit;

/**
 * Factory class for loading keystore and making TLS ServerSocket
 */
public class TlsSocketFactory {
	private static TlsSocketFactory me = null;
	private SSLServerSocketFactory ssf;
	private SSLContext sslContext;

	/**
	 * Reads keystore data and creates an SSLContext and a ServerSocketFactory
	 */
	private TlsSocketFactory() {
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.home") + "/bstore.jks");
			char[] pass = "bridge".toCharArray();
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStore.load(fis, pass);

			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(keyStore, pass);

			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(keyStore);

			sslContext = SSLContext.getInstance("TLSv1.2");
			sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());

			ssf = sslContext.getServerSocketFactory();
		} catch(Exception e) {
			e.printStackTrace();
			exit(1);
		}
	}

	/**
	 * Singleton static constructor
	 * @return A new instance
	 */
	public static TlsSocketFactory getInstance() {
		if(me == null) {
			me = new TlsSocketFactory();
		}
		return me;
	}

	/**
	 * Creates a new ServerSocket
	 * @param port Port for socket connection
	 * @return The generated ServerSocket
	 */
	public ServerSocket getServerSocket(int port) {
		try {
			return ssf.createServerSocket(port);
		} catch(IOException e) {
			e.printStackTrace();
			exit(1);
		}
		return null;
	}

	/**
	 * Creates a client-side socket for use in testing
	 * @param port The localhost port to connect to
	 * @return A socket
	 */
	public Socket getTestClientSocket(int port) {
		try {
			return sslContext.getSocketFactory().createSocket("localhost", port);
		} catch(IOException e) {
			e.printStackTrace();
			exit(1);
		}
		return null;
	}
}

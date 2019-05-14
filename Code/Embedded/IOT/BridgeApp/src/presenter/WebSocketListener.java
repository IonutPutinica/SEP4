package presenter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.concurrent.CompletionStage;

/**
 * Listens to the websocket provided by the loriot server
 */
public class WebSocketListener implements WebSocket.Listener, IEmbeddedConnector {
	public static String STATUS = "WebSocket is closed";

	private ArrayDeque<String> queue;

	/**
	 * Creates a WebSocket and parses itself is listener to the WebSocket's constructor
	 * @param url The url to which the connection should be made
	 */
	public WebSocketListener(String url) {
		queue = new ArrayDeque<>();
		HttpClient.newHttpClient().newWebSocketBuilder().buildAsync(URI.create(url), this);
	}

	/**
	 * Returns a string from it's internal buffer, if one exists;
	 * @return String from websocket, or null if non in buffer
	 */
	@Override
	public String getMessage() {
		return queue.poll();
	}

	@Override
	public void onOpen(WebSocket webSocket) {
		webSocket.request(1);
		STATUS = "WebSocket is open";
	}

	@Override
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
		webSocket.request(1);
		queue.add(data.toString());
		return null;
	}

	@Override
	public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
		webSocket.request(1);
		System.out.println("Ping");
		System.out.println(message.asCharBuffer().toString());
		return null;
	}

	@Override
	public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
		webSocket.request(1);
		System.out.println("Pong");
		System.out.println(message.asCharBuffer().toString());
		return null;
	}

	@Override
	public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
		STATUS = "WebSocket closed";
		return null;
	}

	@Override
	public void onError(WebSocket webSocket, Throwable error) {
		STATUS = "WebSocket aborted";
		System.err.println("Embedded WebSocket crashed");
		webSocket.abort();
	}
}

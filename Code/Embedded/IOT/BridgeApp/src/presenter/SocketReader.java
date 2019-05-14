package presenter;

import java.io.*;
import java.net.Socket;
import model.*;

/**
 * Handles input from socket connection
 */
public class SocketReader implements Runnable {
	Socket socket;
	IWebserviceHandler webserviceHandler;
	Thread t;
	boolean auth = false;
	String authToken = "1010";

	public SocketReader(Socket socket, IWebserviceHandler webserviceHandler) {
		this.socket = socket;
		this.webserviceHandler = webserviceHandler;
	}

	/**
	 * Kill this instance, so the thread dies.
	 */
	public void kill() {
		System.out.println("Connection terminated");
		try {
			socket.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		t.interrupt();
	}

	/**
	 * Reads until encountering a \0 character and then passes
	 * the buffered bytes, as a String, to {@link #handle(String, BufferedWriter)}.
	 */
	@Override
	public void run() {
		t = Thread.currentThread();
		System.out.println("Connected");
		InputStreamReader ir;
		BufferedWriter bw;
		try {
			ir = new InputStreamReader(socket.getInputStream());
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			while(!socket.isClosed()) {
				int c;
				while((c = ir.read()) != 0) {
					baos.write(c);
				}

				String str = new String(baos.toByteArray(), "UTF-8");
				baos.reset();
				handle(str, bw);
			}
		} catch(IOException e) {
			System.out.println("SocketReader Interrupted");
		}
	}

	/**
	 * Handles the individual commands and responds on the BufferedWriter
	 * @param input The String read in the {@link #run()}
	 * @param bw BufferedWriter for the socket.
	 * @throws IOException If writing to socket fails
	 */
	private void handle(String input, BufferedWriter bw) throws IOException {
		if(!auth) {
			if(input.equals(authToken)) {
				auth = true;
				respond("accept", bw);
				System.out.println("Client authenticated");
			}
			else {
				respond("reject", bw);
			}
			return;
		}

		int c = input.indexOf(':');
		String cmd = "";
		String param = "";
		if(c != -1) {
			cmd = input.substring(0, c);
			param = input.substring(c + 1, input.length());
		}

		String res;
		switch(cmd) {
			case "getplantprofile":
				int id;
				try {
					id = Integer.parseInt(param);
				} catch(NumberFormatException e) {
					id = -1;
				}
				PlantProfile p = webserviceHandler.getPlantProfile(id);

				if(p == null) {
					res = "null";
				}
				else {
					res = p.toJson();
				}

				respond(res, bw);
				break;

			case "getaccount":
				Account a = webserviceHandler.getAccount(param);

				if(a == null) {
					res = "null";
				}
				else {
					res = a.toJson();
				}

				respond(res, bw);
				break;

			case "removeaccount":
				if(webserviceHandler.removeAccount(param)) {
					res = "accept";
				}
				else {
					res = "reject";
				}

				respond(res, bw);
				break;

			case "removeplantprofile":
				int idP;
				try {
					idP = Integer.parseInt(param);
				} catch(NumberFormatException e) {
					idP = -1;
				}

				if(webserviceHandler.removePlantProfile(idP)) {
					res = "accept";
				}
				else {
					res = "reject";
				}

				respond(res, bw);
				break;

			case "addaccount":
				Account addAccount = Account.fromJson(param);
				if(addAccount == null) {
					res = "reject";
					break;
				}

				if(webserviceHandler.addAccount(addAccount)) {
					res = "accept";
				}
				else {
					res = "reject";
				}

				respond(res, bw);
				break;

			case "modifyaccount":
				Account modAccount = Account.fromJson(param);
				if(modAccount == null) {
					res = "reject";
					break;
				}

				if(webserviceHandler.modifyAccount(modAccount)) {
					res = "accept";
				}
				else {
					res = "reject";
				}

				respond(res, bw);
				break;

			default:
				respond("Error", bw);
				System.err.println("Invalid command from webservice");
		}
	}

	/**
	 * Respond helper method
	 * @param response String to write to socket.
	 * @param bw The bufferedWriter passed on from {@link #handle(String, BufferedWriter)}
	 * @throws IOException if writing to socket fails
	 */
	private void respond(String response, BufferedWriter bw) throws IOException {
		bw.write(response);
		bw.write(0);
		bw.flush();
	}
}

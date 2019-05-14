package presenter;

import model.PlantProfile;
import org.bson.Document;

import static java.lang.System.exit;

/**
 * Perpetually checks the queue in the internal WebSocketListener
 * for updates and processes the received data into a PlantProfile object
 * which is submitted to storage.
 */
public class EmbeddedListener implements Runnable{
	IEmbeddedConnector wsl;
	IDatabaseHandler databaseHandler;
	String name;
	int id;

	/**
	 * Listener for a single plant
	 * @param databaseHandler Instance for storage handling
	 * @param name Name of the plant used in the database
	 * @param id Database id of the plant
	 * @param url Webservice connection URI for plant
	 */
	public EmbeddedListener(IDatabaseHandler databaseHandler, String name, int id, String url) {
		wsl = new WebSocketListener(url);
		this.databaseHandler = databaseHandler;
		this.name = name;
		this.id = id;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
				exit(1);
			}
			String update = wsl.getMessage();
			if(update != null) {
				char[] hex = Document.parse(update).getString("data").toCharArray();
				int hum = Integer.parseInt("" + hex[0] + hex[1], 16);
				int temp = Integer.parseInt("" + hex[2] + hex[3], 16);
				int co2 = Integer.parseInt("" + hex[4] + hex[5] + hex[6] + hex[7], 16);
				int light = Integer.parseInt("" + hex[8] + hex[9] + hex[10] + hex[11], 16);
				int water = Integer.parseInt("" + hex[12] + hex[13], 16);

				PlantProfile pp = new PlantProfile();
				pp.AmountOfWater = 0;
				pp.CO2 = co2;
				pp.Humidity = hum;
				pp.HoursSinceWatering = water;
				pp.Light = light;
				pp.Temperature = temp;
				pp.PlantName = name;
				pp.PlantID = id;
				System.out.println(pp.toJson());

				databaseHandler.setPlantProfile(pp);
			}
		}
	}
}

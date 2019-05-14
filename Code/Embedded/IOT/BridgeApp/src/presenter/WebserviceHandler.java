package presenter;

import model.Account;
import model.PlantProfile;

import java.util.HashMap;
import java.util.Map;

public class WebserviceHandler implements IWebserviceHandler {
	IDatabaseHandler databaseHandler;

	public WebserviceHandler(IDatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

	@Override
	public Account getAccount(String username) {
		return databaseHandler.getAccount(username);
	}

	@Override
	public boolean addAccount(Account user) {
		return databaseHandler.addAccount(user);
	}

	@Override
	public boolean modifyAccount(Account user) {
		return databaseHandler.setAccount(user);
	}

	@Override
	public boolean removeAccount(String username) {
		return databaseHandler.removeAccount(username);
	}

	@Override
	public boolean removePlantProfile(int id) {
		return databaseHandler.removePlantProfile(id);
	}

	@Override
	public PlantProfile getPlantProfile(int id) {
		return databaseHandler.getPlantProfile(id);
	}
}

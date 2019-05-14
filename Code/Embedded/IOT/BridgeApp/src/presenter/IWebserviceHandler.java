package presenter;

import model.Account;
import model.PlantProfile;

/**
 * Handles request read from the webservice socket connection.
 */
public interface IWebserviceHandler {
	
	public Account getAccount(String username);
	public boolean addAccount(Account account);
	public boolean modifyAccount(Account user);
	public boolean removeAccount(String username);
	public boolean removePlantProfile(int id);
	public PlantProfile getPlantProfile(int id);

}

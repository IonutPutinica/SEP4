package presenter;

import model.Account;
import model.PlantProfile;
/**
 * Handles storage of data
 * Methods in this interface return a boolean indicating
 * success or failure of action.
 */
public interface IDatabaseHandler {
	public Account getAccount(String user);
	public boolean setAccount(Account user);
	public boolean addAccount(Account user);
	public boolean removeAccount(String username);
	public boolean setPlantProfile(PlantProfile monitor);
	public PlantProfile getPlantProfile(int id);
	public boolean removePlantProfile(int id);
	public String getStatus();
}

package test;


import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;

import model.Account;
import model.PlantProfile;
import org.junit.Before;
import org.junit.Test;
import presenter.DatabaseHandler;

import static junit.framework.TestCase.assertEquals;


public class DatabaseHandler_Test {

	String hostString = "mongodb+srv://Llamaxy:865feeBA@test-i10mg.mongodb.net/test?retryWrites=true";
	DatabaseHandler dbh;

	@Before
	public void setUp() throws Exception {
		dbh = new DatabaseHandler(hostString);
	}

	@Test
	public void getStatus() {
		String status = "Connected to mongoDB";
		assertEquals(dbh.getStatus(), status);
	}

	@Test
	public void addAccount() {
		Account account = new Account();
		account.Username = "Test_name";
		account.Password = "Test_password";
		assertEquals(dbh.addAccount(account), true);
	}

	@Test
	public void getAccount() {
		Account a = dbh.getAccount("Test_name");
		assertEquals(a.Username, "Test_name");
	}

	@Test
	public void setAccount() {
		Account a = new Account();
		a.Username = "Test_name";
		a.Password = "new_password";
		assertEquals(dbh.setAccount(a), true);
	}

	@Test
	public void removeAccount() {
		assertEquals(dbh.removeAccount("Test_name"), true);
	}

	@Test
	public void setPlantProfile() {
		PlantProfile pp = new PlantProfile();
		pp.PlantID = 1;
		pp.Temperature = 12;
		pp.Light = 10;
		pp.HoursSinceWatering = 0;
		pp.PlantName = "Flower";
		pp.Humidity = 1;
		pp.CO2 = 100;
		pp.AmountOfWater = 0;
		assertEquals(dbh.setPlantProfile(pp), true);
	}

	@Test
	public void getPlantProfile() {
		PlantProfile pp = dbh.getPlantProfile(1);
		assertEquals(pp.PlantID, 1);
	}

	@Test
	public void removePlantProfile() {
		assertEquals(dbh.removePlantProfile(2), true);
	}

}

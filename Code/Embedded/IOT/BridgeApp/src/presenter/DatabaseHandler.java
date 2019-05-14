package presenter;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import model.Account;
import model.PlantProfile;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.HashMap;
import java.util.Map;


public class DatabaseHandler implements IDatabaseHandler {
	MongoClient mongo;
	MongoDatabase database;
	MongoCollection<Document> accountCollection;
	MongoCollection<Document> plantCollection;
	public String STATUS = "Not connected";
	Map<Integer, PlantProfile> plantProfiles;

	/**
	 * Constructs a database handler internally using com.mongodb.MongoClient
	 * @param hostString MongoClientURI string
	 */
	public DatabaseHandler(String hostString) {
		plantProfiles = new HashMap<>();
		mongo = MongoClients.create(hostString);
		database = mongo.getDatabase("Project");
		if(database != null) {
			STATUS = "Connected to mongoDB";
		}

		plantCollection = database.getCollection("PlantProfiles");
		accountCollection = database.getCollection("Accounts");
	}

	/**
	 * Used by CLI to get status of database connection
	 * @return Status String
	 */
	@Override
	public String getStatus() {
		return STATUS;
	}

	@Override
	public Account getAccount(String user) {
		Bson filter = new BasicDBObject("Username", user);
		Document document = accountCollection.find(filter).first();
		document.remove("_id");
		return Account.fromJson(document.toJson());
	}

	@Override
	public boolean setAccount(Account account) {
		Bson filter = new BasicDBObject("Username", account.Username);
		Document rep = Document.parse(account.toJson());
		Document res = accountCollection.findOneAndReplace(filter, rep);
		if(res == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean addAccount(Account user) {
		try {
			Document doc = Document.parse(user.toJson());
			accountCollection.insertOne(doc);
		} catch(Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public boolean removeAccount(String username) {
		Bson filter = new BasicDBObject("Username", username);
		if(accountCollection.findOneAndDelete(filter) == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean setPlantProfile(PlantProfile monitor) {
		synchronized(plantProfiles) {
			if(plantProfiles.containsKey(monitor.PlantID)) {
				plantProfiles.replace(monitor.PlantID, monitor);
			}
			else {
				plantProfiles.put(monitor.PlantID, monitor);
			}
		}

		Bson filter = new BasicDBObject("PlantID", monitor.PlantID);
		Document rep = Document.parse(monitor.toJson());
		Document res = plantCollection.findOneAndReplace(filter, rep);
		if(res == null) {
			return false;
		}

		return true;
	}

	@Override
	public PlantProfile getPlantProfile(int id) {
		synchronized(plantProfiles) {
			if(plantProfiles.containsKey(id)) {
				return plantProfiles.get(id);
			}
		}

		Bson filter = new BasicDBObject("PlantID", id);
		Document doc = plantCollection.find(filter).first();
		if(doc == null) {
			System.out.println("null--");
			return null;
		}
		doc.remove("_id");
		doc.remove("DateTime");
		return PlantProfile.fromJson(doc.toJson());
	}

	@Override
	public boolean removePlantProfile(int id) {
		Bson filter = new BasicDBObject("PlantID", id);
		if(plantCollection.findOneAndDelete(filter) == null) {
			return false;
		}
		return true;
	}
}

using MongoDB.Bson;
using MongoDB.Driver;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
namespace SqlTest
{
    class Program
    {
        static void Main(string[] args)
        {


            var mongoClient = new MongoClient(@"mongodb+srv://Llamaxy:865feeBA@test-i10mg.mongodb.net/test?retryWrites=true");

            var mongoDatabase = mongoClient.GetDatabase("Project");
            var PlantsCollection = mongoDatabase.GetCollection<PlantProfile>("PlantProfiles");
            var allPlants = PlantsCollection.AsQueryable<PlantProfile>().ToList();

            var plants = new List<PlantProfile>();

            allPlants.ForEach(plant => plants.Add(plant));

            Console.WriteLine(plants[0].CO2);

            SqlConnection SqlConnection;
            SqlCommand SqlCommand;


            string connection = @"Data Source = .\SQLEXPRESS ; Integrated Security = True;";
            SqlConnection = new SqlConnection(connection);

            try
            {
                
                for (int i = 0; i < plants.Count; i++)
                {

                    
                    string sql = "Insert into [SEP].[dbo].[Readings] (PlantID, PlantName,Temperature,Light,CO2,Humidity,AmountOfWater,HoursSinceWatering,ReadingDate)" +
                        " values (@PlantID,@PlantName,@Temperature,@Light,@CO2,@Humidity,@AmountOfWater,@HoursSinceWatering,@ReadingDate);";
                    SqlConnection.Open();
                    SqlCommand = new SqlCommand(sql, SqlConnection);
                    SqlCommand.Parameters.Add("@PlantID", SqlDbType.Int).Value = plants[i].PlantID;
                    SqlCommand.Parameters.Add("@PlantName", SqlDbType.NChar).Value = plants[i].PlantName;
                    SqlCommand.Parameters.Add("@Temperature", SqlDbType.Float).Value = plants[i].Temperature;
                    SqlCommand.Parameters.Add("@Light", SqlDbType.Float).Value = plants[i].Light;
                    SqlCommand.Parameters.Add("@CO2", SqlDbType.Float).Value = plants[i].CO2;
                    SqlCommand.Parameters.Add("@Humidity", SqlDbType.Float).Value = plants[i].Humidity;
                    SqlCommand.Parameters.Add("@AmountOfWater", SqlDbType.Float).Value = plants[i].AmountOfWater;
                    SqlCommand.Parameters.Add("@HoursSinceWatering", SqlDbType.Float).Value = plants[i].HoursSinceWatering;
                    SqlCommand.Parameters.Add("@ReadingDate", SqlDbType.DateTime).Value = plants[i].DateTime;
                    SqlCommand.ExecuteNonQuery();
                    SqlCommand.Dispose();
                    SqlConnection.Close();

                    Console.WriteLine("Data added succesfully");
                    }



            }
            catch (System.Exception)
            {

                throw;
            }


        }
    }
}
    
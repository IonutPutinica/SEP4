using FinalWebApi.Models;
using ReadingAccess;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;


namespace FinalWebApi.Controllers
{
    public class ValuesController : ApiController
    {

       List<PlantProfile> plants = new List<PlantProfile>();
        public String result_time = "";
        public String result_co2 = "";
        public String result_humidity = "";
        public String result_light = "";
        public String result_temperature = "";
        public String result_watering = "";

       public ValuesController()
        {
            SEPDimensionEntities entities = new SEPDimensionEntities();

            

            /*
            foreach (D_Time f in entities.D_Time.ToList())
            {
                result_time = result_time + f.ToString();
            }

            */

            foreach (F_CO2 f in entities.F_CO2.ToList())
            {
                result_co2 = result_co2 + f.ToString();
            }

            foreach (F_Humidity f in entities.F_Humidity.ToList())
            {
                result_humidity = result_humidity + f.ToString();
            }

            foreach (F_Light f in entities.F_Light.ToList())
            {
                result_light = result_light + f.ToString();
            }

            foreach (F_Temperature f in entities.F_Temperature.ToList())
            {
                result_temperature = result_temperature + f.ToString();
            }

            foreach (F_Watering f in entities.F_Watering.ToList())
            {
                result_watering = result_watering + f.ToString();
            }

            int counter = 0;
            int x = 0;
            


            var c_co2 = result_co2.Split(',');
            var c_humidty = result_humidity.Split(',');
            var c_light = result_light.Split(',');
            var c_temperature = result_temperature.Split(',');
            var c_watering = result_watering.Split(',');

            for (int i = 0;i< c_co2.Length;i++)
            {
                PlantProfile plant = new PlantProfile();

                int.TryParse(c_co2[i], out x);
                plant.setCo2(x);

                int.TryParse(c_humidty[i], out x);
                plant.setHumidity(x);

                int.TryParse(c_light[i], out x);
                plant.setLight(x);

                int.TryParse(c_temperature[i], out x);
                plant.setTemperature(x);

                plant.setWatering(c_watering[i]);

                plants.Add(plant);

            }

            

        }



        // GET api/values
        public List<PlantProfile> Get()
        {

            return plants;
    
    }

        // GET api/values/5
        public PlantProfile Get(int id)
        {
            return plants[id];
        }

        // POST api/values
        public void Post([FromBody]string value)
        {

        }

        // PUT api/values/5
        public void Put(int id, [FromBody]string value)
        {

        }

        // DELETE api/values/5
        public void Delete(int id)
        {

        }
    }
}

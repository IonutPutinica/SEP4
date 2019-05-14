using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace FinalWebApi.Models
{
    public class PlantProfile
    {

        private DateTime date;
        private float co2;
        private float humidity;
        private float light;
        private float temperature;
        private String watering;



        public void setCo2(float co2) { this.co2 = co2; }

        public void setHumidity(float humidity) { this.humidity = humidity; }

        public void setLight(float Light) { this.light = Light; }

        public void setTemperature(float temperature) { this.temperature = temperature; }

        public void setWatering(String value) { this.watering = watering; }


        public float getCo2() { return co2; }

        public float getHumidity() {return humidity; }

        public float getLight() { return light; }

        public float getTemperature() { return temperature; }

        public String getWatering() { return watering; }

    }
}
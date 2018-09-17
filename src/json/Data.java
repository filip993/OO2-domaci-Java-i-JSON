package json;

import java.text.*;
import java.util.*;

public class Data {

    class Weather {
        String description;
        String main;
    }

    class Main {
        float temp;
        float humidity;
        float temp_min;
        float temp_max;
    }

    class System {
        long sunrise;
        long sunset;
    }

    List<Weather> weather;
    Main main;
    System sys;

    public String getTemperatureInCelsius() {
        float temp1 = main.temp - 273.15f;
        return String.format("%.2f", temp1);
    }

    public String getMaxTemperatureInCelsius() {
        float temp1 = main.temp_max - 273.15f;
        return String.format("%.2f", temp1);
    }

    public String getMinTemperatureInCelsius() {
        float temp1 = main.temp_min - 273.15f;
        return String.format("%.2f", temp1);
    }

    public float getHumidity() {
        return main.humidity;
    }

    public String getDescription() {
        if (weather != null && weather.size() > 0)
            return weather.get(0).description;
        return null;
    }

//    public String getMain() {
//        if (weather != null && weather.size() > 0)
//            return weather.get(0).main;
//        return null;
//    }

    public String getSunrise() {
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        return sdf.format(new Date(sys.sunrise));
    }

    public String getSunset() {
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        return sdf.format(new Date(sys.sunset));
    }
}

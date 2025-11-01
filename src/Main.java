import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main (String[] args) throws Exception {
        System.out.println("Hello");

        float lat = 50.5f;
        float lon = 107.37f;

        String json = getWeather.get(lat, lon);
        System.out.println("Ответ API: " + json);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);

        int currentTemp = rootNode.path("fact").path("temp").asInt();
        System.out.println("\nТекущая температура: " + currentTemp + "°C");

    }
}
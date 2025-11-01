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

        if (!rootNode.isMissingNode()) {
            int currentTemp = rootNode.path("fact").path("temp").asInt();
            System.out.println("\nТекущая температура: " + currentTemp + "°C");
        } else {
            System.out.println("Данные о текущей погоде отсутствуют");
        }

        JsonNode forecasts = rootNode.path("forecasts");
        if (forecasts.isArray() && forecasts.size() > 0) {
            double sumTemp = 0;
            int count = 0;
            for (JsonNode forecast : forecasts) {
                JsonNode parts = forecast.path("parts");
                JsonNode day = parts.path("day");
                if (!day.isMissingNode() && day.has("temp_avg")) {
                    sumTemp += day.get("temp_avg").asDouble();
                    count++;
                }
            }
            if (count > 0) {
                double avgTemp = sumTemp / count;
                System.out.printf("Средняя температура за %d дней: %.2f°C\n", count, avgTemp);
            } else {
                System.out.println("Нет данных о дневных температурах для расчета");
            }
        } else {
            System.out.println("Отсутствуют прогнозы для вычисления средней температуры");
        }


    }
}
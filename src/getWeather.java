import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class getWeather {
    public static String get(float lat, float lon) {
        try {

            // Формируем URL API с нужными параметрами lat и lon
            String key = "15124495-501a-4600-8f73-6fd57872d292";
            String apiUrl = "https://api.weather.yandex.ru/v2/forecast?lat=" + lat + "&lon=" + lon;

            // Создаём экземпляр HttpClient — объекта, отправляющего HTTP-запросы
            HttpClient client = HttpClient.newHttpClient();

            // Строим HTTP-запрос
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl)) // Указываем URI запроса
                    .header("X-Yandex-Weather-Key", key) // добавляем заголовок с API-ключом
                    .GET() // указываем, что это GET-запрос
                    .build(); // собираем объект запроса

            // Отправляем запрос и получаем ответ асинхронно или синхронно
            // В данном случае используем синхронный вызов send()
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Выводим статус-код ответа
                System.out.println("Код ответа: " + response.statusCode());
                // Возвращаем тело ответа
                return response.body();
            }  else {
            System.out.println("Ошибка запроса: " + response.statusCode());
            System.out.println("Текст ответа: " + response.body());

            return "";
        }
        } catch (Exception e) {
            // Обработка возможных исключений, например, ошибок сети или некорректных URL
            e.printStackTrace();
            return "";
        }

    }
}
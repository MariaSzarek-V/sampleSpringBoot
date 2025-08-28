package pl.coderslab.samplespringboot;


import org.springframework.web.client.RestTemplate;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

//public class Main {
//
//    private static final String API_KEY
//            = "eee5028bd4f1a9645f0de3b18aa4c17c11a0eedd815aeaacf2cae4d5801e8969";
//    private static final String API_URL
//            = "https://apiv3.apifootball.com/api/?action=get_countries&APIkey={apiKey}";
//
//    public static void main(String[] args) {
//        RestTemplate restTemplate = new RestTemplate();
//        CountryDTO[] forObject = restTemplate.getForObject(API_URL,
//                CountryDTO[].class, API_KEY);
//        Arrays.stream(forObject).forEach(System.out::println);
//    }
//}

//public class Main {
//    public static void main(String[] args) {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/v1/taborets/{id}";
//        Taboret user = restTemplate.getForObject(url, Taboret.class, 2);
//        System.out.println(user);
//
////        String url2 = "https://dummyjson.com/products/{id}";
////        Product forObject = restTemplate.getForObject(url2, Product.class, 1);
////        System.out.println(forObject);
//
//    }
//}

// WAŻNE: Upewnij się, że importy są z org.apache.http, a NIE z org.apache.hc.client5
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.ssl.SSLContexts; // Poprawny import dla v4
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;
import java.util.Arrays;

public class Main {

    private static final String API_KEY
            = "eee5028bd4f1a9645f0de3b18aa4c17c11a0eedd815aeaacf2cae4d5801e8969";
    private static final String API_URL
            = "https://apiv3.apifootball.com/api/?action=get_countries&APIkey={apiKey}";

    public static void main(String[] args) throws Exception {
        // Kod tworzący "ufający wszystkim" RestTemplate dla HttpClient 4.x
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient); // Teraz to zadziała!

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        // Reszta kodu bez zmian
        try {
            CountryDTO[] countries = restTemplate.getForObject(API_URL,
                    CountryDTO[].class, API_KEY);

            if (countries != null) {
                Arrays.stream(countries).forEach(System.out::println);
            } else {
                System.out.println("Otrzymano pustą odpowiedź (null).");
            }
        } catch (Exception e) {
            System.err.println("Wystąpił błąd podczas wywoływania API:");
            e.printStackTrace();
        }
    }
}
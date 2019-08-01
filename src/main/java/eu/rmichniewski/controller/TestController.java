package eu.rmichniewski.controller;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/test")
public class TestController {


    @CrossOrigin(origins = "*")
    @GetMapping("")
    public String getAllInvoices() {
        System.out.println("TEST");

        final String uri = "https://google.com";

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("accept", "*/*"));
        interceptors.add(new HeaderRequestInterceptor("accept-encoding", "gzip, deflate, br"));
        interceptors.add(new HeaderRequestInterceptor("accept-language", "pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7"));
        interceptors.add(new HeaderRequestInterceptor("cookie", "__cfduid=deceee061321c6f1cda84c3ea3be09db51559597552; qtrans_front_language=pl; _ga=GA1.2.1222493111.1559597556; _gid=GA1.2.555918153.1559597556; cookies-info=1; _gat_gtag_UA_126458898_1=1; pml=98b646013cda69699398c21df3ed2aaafdef2bb5"));
        interceptors.add(new HeaderRequestInterceptor("referer", "https://rowermevo.pl/mapa-stacji/"));
        interceptors.add(new HeaderRequestInterceptor("user-agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36"));

        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setInterceptors(interceptors);

        return restTemplate.getForObject(uri, String.class);
    }

}


//    private static void getEmployees()
//    {
//        final String uri = "http://localhost:8080/springrestexample/employees.xml";
//
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class);
//
//        System.out.println(result);
//    }
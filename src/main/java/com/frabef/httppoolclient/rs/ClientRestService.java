package com.frabef.httppoolclient.rs;

import static com.frabef.httppoolclient.rs.ClientRestService.Counter.increase;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;


@RestController
public class ClientRestService {

    Logger log = LoggerFactory.getLogger("customfrabef");


    private RestTemplate restTemplate;

    @Autowired
    public ClientRestService(RestTemplate restTemplate) {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/client")
    public ResponseEntity client() {
        restTemplate.getForObject("https://floating-sierra-20140.herokuapp.com", DelayResponse.class);
        log.info("llamado");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<MyResponse> get() {

        //log.info("llamado " + increase());

        try {
            URI uri = URI.create("https://floating-sierra-20140.herokuapp.com");
            RequestEntity request = RequestEntity.get(uri).build();
            ResponseEntity<MyResponse> response = restTemplate.exchange(request, MyResponse.class);
            //log.info(response.getStatusCodeValue() +" " +response.getStatusCode().toString() + "" + response.getBody());
            log.info(response.getStatusCodeValue()+"");
            return ResponseEntity.ok(response.getBody());
        } catch (HttpStatusCodeException e) {
            //log.info(e.getRawStatusCode() +" " +e.getStatusText() + "" + e.getResponseBodyAsString());
            log.info(e.getRawStatusCode()+"");
            throw e;
        }


    }

    public static class Counter {

        private static int COUNT = 0;

        public static int increase() {
            return COUNT++;
        }

    }

}


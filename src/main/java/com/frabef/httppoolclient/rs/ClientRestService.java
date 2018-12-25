package com.frabef.httppoolclient.rs;

import java.net.URI;
import java.util.Date;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.java.Log;


@Log
@RestController
public class ClientRestService {

    private final static Gson GSON = new Gson();


    private RestTemplate restTemplate;

    @Autowired
    public ClientRestService(RestTemplate restTemplate) {
        this.restTemplate = new RestTemplate();
        //this.restTemplate = restTemplate;
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

    @GetMapping("/log")
    public ResponseEntity<CustomResponse> log() {

        //log.info("llamado " + increase());

        try {
            URI uri = URI.create("https://floating-sierra-20140.herokuapp.com");
            RequestEntity request = RequestEntity.get(uri).build();

            Date startTime = new Date();
            ResponseEntity<MyResponse> response = restTemplate.exchange(request, MyResponse.class);
            long duration = new Date().getTime() - startTime.getTime();
            CustomResponse customResponse = new CustomResponse(duration, response.getBody());
            log.info(GSON.toJson(customResponse));
            return ResponseEntity.ok(customResponse);
        } catch (HttpStatusCodeException e) {
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


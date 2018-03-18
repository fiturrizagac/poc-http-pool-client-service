package com.frabef.httppoolclient.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class ClientRestService {

    private RestTemplate restTemplate;

    @Autowired
    public ClientRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/client")
    public ResponseEntity client() {
        restTemplate.getForObject("https://sleepy-escarpment-39046.herokuapp.com/delay/resttemplate", DelayResponse.class);
        log.info("llamado");
        return ResponseEntity.ok().build();
    }

}

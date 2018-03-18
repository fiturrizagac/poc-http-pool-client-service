package com.frabef.delayapp.rs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class DelayRestService {

    @GetMapping("delay/{thread}")
    public ResponseEntity<DelayResponse> delay(@PathVariable final String thread) {

        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("Thread usado " + thread);

        return ResponseEntity.ok(new DelayResponse(thread));
    }

}

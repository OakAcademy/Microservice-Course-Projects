package uk.oakacademy.oauthServer.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oak")
public class OakController {

    @GetMapping
    public ResponseEntity<String> getOak()
    {
        return ResponseEntity.ok("Hello Oak Academy");
    }
}

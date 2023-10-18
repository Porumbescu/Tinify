package com.porumb.tinify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/")
public class TinifyController {
    @Autowired
    TinifyService tinifyService;

    @PostMapping("/api/tinify/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl){
        String shortCode = tinifyService.shortenUrl(originalUrl);
        String shortenedUrl = "http://localhost:8080/" + shortCode;
        return ResponseEntity.ok(shortenedUrl);
    }
    @GetMapping("/{shortCode}")
    public ResponseEntity<String> redirectToOriginal (@PathVariable String shortCode){
        String originalUrl = tinifyService.resolveUrl(shortCode);
        if(originalUrl == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
    }
}

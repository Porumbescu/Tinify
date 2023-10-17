package com.porumb.tinify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tinify")
public class TinifyController {
    @Autowired
    TinifyService tinifyService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl){
        String shortCode = tinifyService.shortenUrl(originalUrl);
        return ResponseEntity.ok(shortCode);
    }
    @GetMapping("/resolve/{shortCode}")
    public ResponseEntity<String> resolveUrl(@PathVariable String shortCode){
        String originalUrl = tinifyService.resolveUrl(shortCode);
        if(originalUrl == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(originalUrl);
    }
}

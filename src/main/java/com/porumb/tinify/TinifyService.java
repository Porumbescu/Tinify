package com.porumb.tinify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;

@Service
public class TinifyService {
    @Autowired
    private TinifyRepository tinifyRepository;

    public String shortenUrl(String originalUrl){
        if(!isValidFormat(originalUrl) || !isReachable(originalUrl)){
            throw new InvalidUrlException("Provided URL is invalid or not reachable");
        }

        Optional<Link> existingLink = tinifyRepository.findByOriginalUrl(originalUrl);
        if(existingLink.isPresent()){
            return existingLink.get().getShortCode();
        }

        String shortCode = generateShortCode();
        while(tinifyRepository.findByShortCode(shortCode).isPresent()){
            shortCode = generateShortCode(); // in case of collision
        }

        Link link = new Link();
        link.setOriginalUrl(originalUrl);
        link.setShortCode(shortCode);
        tinifyRepository.save(link);

        return shortCode;
    }

    public String resolveUrl(String shortCode){
        return tinifyRepository.findByShortCode(shortCode)
                .map(Link::getOriginalUrl)
                .orElse(null);
    }

    private String generateShortCode(){
        return UUID.randomUUID().toString().substring(0,8);
    }

    private boolean isValidFormat(String url){
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    private boolean isReachable(String url){
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (200 <= responseCode && responseCode <= 399);
        } catch (IOException e) {
            return false;
        }
    }
}

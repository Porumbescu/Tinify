package com.porumb.tinify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TinifyService {
    @Autowired
    private TinifyRepository tinifyRepository;

    public String shortenUrl(String originalUrl){
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
}

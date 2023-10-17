package com.porumb.tinify;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TinifyRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByShortCode(String shortCode);
    Optional<Link> findByOriginalUrl(String OriginalUrl);
}

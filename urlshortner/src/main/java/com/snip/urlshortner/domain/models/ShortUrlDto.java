package com.snip.urlshortner.domain.models;

import java.io.Serializable;
import java.time.Instant;

/**
 * Data Transfer Object for Short URL.
 * Represents a shortened URL with its details.
 */
public record ShortUrlDto(Long id, String shortKey, String originalUrl,
                          Boolean isPrivate, Instant expiresAt,
                          UserDto createdBy, Long clickCount,
                          Instant createdAt) implements Serializable {
}
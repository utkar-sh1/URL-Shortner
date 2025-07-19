package com.snip.urlshortner.domain.service;

import com.snip.urlshortner.domain.models.ShortUrlDto;
import com.snip.urlshortner.domain.models.UserDto;
import com.snip.urlshortner.domain.pojo.ShortUrl;
import com.snip.urlshortner.domain.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    public ShortUrlDto toShortUrlDto(ShortUrl shortUrl) {
        UserDto userDto = null;
        if(shortUrl.getCreatedBy() != null) {
            userDto = toUserDto(shortUrl.getCreatedBy());
        }

        return new ShortUrlDto(
                shortUrl.getId(),
                shortUrl.getShortKey(),
                shortUrl.getOriginalUrl(),
                shortUrl.getIsPrivate(),
                shortUrl.getExpiresAt(),
                userDto,
                shortUrl.getClickCount(),
                shortUrl.getCreatedAt()
        );
    }

    public UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getName());
    }
}

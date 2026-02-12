package kz.kbtu.tsis2.service;

import kz.kbtu.tsis2.dto.HeroesResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalSstatsService {
    @Autowired
    private WebClient mlbbWebClient;

    public HeroesResponseDto getAllHeroes() {
        return mlbbWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/heroes/public")
                        .build())
                .retrieve()
                .bodyToMono(HeroesResponseDto.class)
                .block();
    }
}

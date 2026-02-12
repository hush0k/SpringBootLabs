package kz.kbtu.tsis2.controller;

import kz.kbtu.tsis2.dto.HeroesResponseDto;
import kz.kbtu.tsis2.service.ExternalSstatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
    @Autowired
    private ExternalSstatsService externalSstatsService;


    @GetMapping("/heroes")
    public HeroesResponseDto getLeagues() {
        return externalSstatsService.getAllHeroes();
    }


}

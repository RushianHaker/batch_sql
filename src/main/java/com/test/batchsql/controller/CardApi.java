package com.test.batchsql.controller;

import com.test.batchsql.model.ShortCard;
import com.test.batchsql.service.CardService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CardApi {
    public final CardService cardService;

    public CardApi(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/card/get")
    public List<ShortCard> getCardsList(@NotNull @RequestParam(name = "names") List<String> names) {
        List<String> decodedNames = names.stream().map(s -> URLDecoder.decode(s, StandardCharsets.UTF_8)).toList();
        return cardService.allCardsListByNames(decodedNames);
    }

    @PostMapping("/card/insert")
    public void saveCardsList(@NotNull @RequestBody List<ShortCard> shortCards) {
        cardService.saveCardsList(shortCards);
    }
}

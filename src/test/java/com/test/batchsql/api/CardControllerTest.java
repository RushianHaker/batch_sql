package com.test.batchsql.api;

import com.google.gson.Gson;
import com.test.batchsql.model.ShortCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getCardsList() {
        String name1 = "Mark";
        String name2 = "Anne";

        URI forCheck = UriComponentsBuilder
                .fromUriString("/api/card/get")
                .queryParam("names", name1, name2)
                .build()
                .encode()
                .toUri();

        ResponseEntity<String> rsp = restTemplate.exchange(forCheck.toString(), HttpMethod.GET,
                new HttpEntity<>(apiHeaders()), String.class);
        assertEquals(HttpStatus.OK, rsp.getStatusCode());

        ShortCard[] cardsList = new Gson().fromJson(rsp.getBody(), ShortCard[].class);

        assertNotNull(cardsList);
        assertNotNull(cardsList[0].getName());
        assertEquals(1, cardsList.length);

        assertEquals(1, cardsList[0].getId());
        assertEquals("a", cardsList[0].getName());
        assertEquals("a", cardsList[0].getFullName());
    }

    @Test
    void insertCardsList() {
        List<ShortCard> cards = new ArrayList<>();
        cards.add(new ShortCard(0, "Andrea", "Jonatoniano"));
        cards.add(new ShortCard(1, "Max", "Ibragioml"));
        cards.add(new ShortCard(2, "Alexander", "Isakov"));

        ResponseEntity<String> rsp = restTemplate.exchange("/api/card/insert", HttpMethod.POST,
                new HttpEntity<>(cards, apiHeaders()), String.class);
        assertEquals(HttpStatus.OK, rsp.getStatusCode());
    }

    @NonNull
    public static MultiValueMap<String, String> apiHeaders() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("Content-Type", List.of("application/json"));
        return headers;
    }
}
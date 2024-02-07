package dev.anar.jokes.service;

import dev.anar.jokes.model.Joke;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class JokeService {

    private final String JOKE_API_URL = "https://official-joke-api.appspot.com/random_joke";

    private final RestTemplate restTemplate;


    public Joke getJoke() {
        return restTemplate.getForObject(JOKE_API_URL, Joke.class);
    }
}

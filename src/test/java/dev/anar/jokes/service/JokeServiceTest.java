package dev.anar.jokes.service;

import dev.anar.jokes.model.Joke;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JokeServiceTest {

    private JokeService jokeService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        jokeService = new JokeService(restTemplate);
    }

    @Test
    public void testGetJoke() {
        Joke expectedJoke = new Joke("type", "setup", "punchline", 123);

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(Joke.class)))
                .thenReturn(expectedJoke);

        Joke actualJoke = jokeService.getJoke();

        assertEquals(expectedJoke, actualJoke);
    }
}
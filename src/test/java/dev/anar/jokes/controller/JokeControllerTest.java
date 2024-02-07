package dev.anar.jokes.controller;

import dev.anar.jokes.model.Joke;
import dev.anar.jokes.service.JokeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class JokeControllerTest {

    @Test
    void testGetJokes() throws ExecutionException, InterruptedException {
        JokeService jokeService = Mockito.mock(JokeService.class);
        Joke joke1 = new Joke("type1", "setup1", "punchline1", 1);
        Joke joke2 = new Joke("type2", "setup2", "punchline2", 2);
        Joke joke3 = new Joke("type3", "setup3", "punchline3", 3);
        when(jokeService.getJoke())
                .thenReturn(joke1)
                .thenReturn(joke2)
                .thenReturn(joke3);

        JokeController jokeController = new JokeController(jokeService);

        ResponseEntity<List<Joke>> responseEntity = jokeController.getJokes(3);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<Joke> jokes = responseEntity.getBody();
        assertEquals(3, jokes.size());
        assertEquals(joke1, jokes.get(0));
        assertEquals(joke2, jokes.get(1));
        assertEquals(joke3, jokes.get(2));
    }
}
package dev.anar.jokes.controller;

import dev.anar.jokes.model.Joke;
import dev.anar.jokes.service.JokeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class JokeController {
    private final JokeService jokeService;

    @GetMapping("/jokes")
    public ResponseEntity<List<Joke>> getJokes(@RequestParam(defaultValue = "5") int count) throws InterruptedException, ExecutionException {
        if (count > 100) {
            return ResponseEntity.badRequest().body(null);
        }

        List<CompletableFuture<Joke>> jokeFutures = new ArrayList<>();

        //запрашиваем асинхронно
        for (int i = 0; i < count; i++) {
            CompletableFuture<Joke> jokeFuture = CompletableFuture.supplyAsync(jokeService::getJoke);
            jokeFutures.add(jokeFuture);
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(jokeFutures.toArray(new CompletableFuture[0]));

        //ждем завершения всех асинхронных задач
        allOf.get();

        //извлекаем результаты
        List<Joke> jokes = jokeFutures.stream()
                .map(CompletableFuture::join)
                .toList();

        return ResponseEntity.ok(jokes);
    }
}

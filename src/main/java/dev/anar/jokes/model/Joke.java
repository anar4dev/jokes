package dev.anar.jokes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Joke {
    @JsonProperty
    private String type;
    @JsonProperty
    private String setup;
    @JsonProperty
    private String punchline;
    @JsonProperty
    private int id;
}

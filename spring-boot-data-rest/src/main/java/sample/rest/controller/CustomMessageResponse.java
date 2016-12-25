package sample.rest.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.model.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yamashiro-r on 2016/12/25.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
class CustomMessageResponse {
    @JsonProperty("msg")
    private String message = "ok";
    @JsonProperty("messages")
    private List<Message> values = new ArrayList<>();

    CustomMessageResponse(final List<Message> messages) {
        this.values = messages;
    }
}

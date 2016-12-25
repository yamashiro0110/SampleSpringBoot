package sample.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.model.Message;
import sample.rest.repository.MessageRepository;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * HAL Browserの一覧に出てこない...
 * <p>
 * Created by yamashiro-r on 2016/12/25.
 */
@RestController
@RequestMapping("/custom/message")
public class CustomMessageController {
    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(value = "list", method = GET)
    public Page<Message> list(final Pageable pageable) {
        return this.messageRepository.findAll(pageable);
    }

    @RequestMapping(method = GET)
    public CustomMessageResponse responseEntity() {
        final List<Message> messages = this.messageRepository.findAll();
        return new CustomMessageResponse(messages);
    }
}

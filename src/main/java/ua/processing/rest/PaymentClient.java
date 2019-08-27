package ua.processing.rest;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.processing.converter.TicketJsonConverter;
import ua.processing.json.TicketJson;
import ua.processing.model.Ticket;
import ua.processing.model.TicketStatus;
import ua.processing.service.TicketService;

@Component
public class PaymentClient {

    private static final String PATCH = "Http://localhost:3000/entity/{id}";

    private final TicketJsonConverter converter;
    private final TicketService service;
    private final RestTemplate restTemplate;

    @Autowired
    public PaymentClient(TicketJsonConverter converter, TicketService service,
        RestTemplate restTemplate) {
        this.converter = converter;
        this.service = service;
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedDelay = 60000, initialDelay = 30000)
    public void result() {
        TicketJson object = restTemplate.getForObject(PATCH, TicketJson.class, queue());
        Ticket convert = converter.convert(object);
        Ticket update = service.update(convert);
    }

    private Long queue() {
        return service.all().stream()
            .filter(ticket -> ticket.getState() == TicketStatus.PROCESSING)
            .findFirst().map(Ticket::getId)
            .orElseThrow(() -> new IllegalArgumentException("id not found "));
    }


}


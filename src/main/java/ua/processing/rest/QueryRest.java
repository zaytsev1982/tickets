package ua.processing.rest;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.processing.converter.TicketConverter;
import ua.processing.converter.TicketDto;
import ua.processing.model.Ticket;
import ua.processing.model.TicketStatus;
import ua.processing.service.TicketService;

@RestController
public class QueryRest {

    private final TicketService service;
    private final TicketConverter converter;

    @Autowired
    public QueryRest(TicketService service, TicketConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String create(@Valid @RequestBody TicketDto ticket) {
        Ticket convert = service.create(converter.convert(ticket));
        return String.valueOf(convert.getId());
    }


    @GetMapping(value = "/{id}")
    public String status(@PathVariable("id") Long id) {
        Ticket one = service.findOne(id);
        return String.valueOf(one.getState());
    }

    @GetMapping(value = "/entity/{id}")
    public Ticket entity(@PathVariable("id") Long id) {
        return service.findOne(id);
    }

    @GetMapping(value = "/entities")
    public List<Ticket> entity() {
        return service.all().stream()
            .filter(ticket -> ticket.getState() == TicketStatus.PROCESSING)
            .collect(Collectors.toList());
    }
}

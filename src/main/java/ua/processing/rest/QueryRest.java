package ua.processing.rest;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.processing.converter.TicketConverter;
import ua.processing.converter.TicketDto;
import ua.processing.exception.NotFoundException;
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

    @ExceptionHandler(NotFoundException.class)
    @GetMapping(value = "/{id}")
    public String status(@PathVariable("id") Long id) {
        Ticket one = service.findOne(id);
        TicketStatus status = one.getStatus();
        return String.valueOf(status);
    }

    @GetMapping(value = "/entity/{id}")
    public Ticket entity(@PathVariable("id") Long id) {
        return service.findOne(id);
    }
}

package ua.processing.converter;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.processing.json.TicketJson;
import ua.processing.model.Ticket;
import ua.processing.model.TicketStatus;
import ua.processing.service.TicketService;

@Component
public class TicketJsonConverter implements Converter<TicketJson, Ticket> {

    private final TicketService service;

    @Autowired
    public TicketJsonConverter(TicketService service) {
        this.service = service;
    }

    @Override
    public Ticket convert(TicketJson ticketJson) {
        Ticket ticket = new Ticket();
        ticket.setId(Long.valueOf(ticketJson.getId()));
        ticket.setRouteNumber(Integer.valueOf(ticketJson.getRouteNumber()));
        ticket.setDateTime(LocalDateTime.parse(ticketJson.getDateTime()));

        Ticket one = service.findOne(ticket.getId());
        service.update(one);
        return one;
    }
}

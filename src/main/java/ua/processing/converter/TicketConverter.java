package ua.processing.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.processing.model.Ticket;
import ua.processing.model.TicketStatus;
import ua.processing.service.TicketService;

@Component
public class TicketConverter implements Converter<TicketDto, Ticket> {

    private final TicketService service;

    @Autowired
    public TicketConverter(TicketService service) {
        this.service = service;
    }


    @Override
    public Ticket convert(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setRouteNumber(Integer.valueOf(ticketDto.getRouteNumber()));
        ticket.setDateTime(time(ticketDto));
        ticket.setStatus(TicketStatus.INPROCESSING);
        return service.create(ticket);

    }

    private LocalDateTime time(TicketDto ticketDto) {
        String dateTime = ticketDto.getDateTime();
        String PATTERN = "yyyy-MM-dd HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
        return LocalDateTime.parse(dateTime, formatter);
    }
}

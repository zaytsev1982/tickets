package ua.processing.service;

import java.util.List;
import ua.processing.model.Ticket;

public interface TicketService {

    Ticket create(Ticket ticket);

    Ticket update(Ticket ticket);

    Ticket findOne(Long id);

    List<Ticket> all();
}

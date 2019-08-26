package ua.processing.service;

import ua.processing.model.Ticket;

public interface TicketService {

    Ticket create(Ticket ticket);

    Ticket update(Long id);

    Ticket findOne(Long id);
}

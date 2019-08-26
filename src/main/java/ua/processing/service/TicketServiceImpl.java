package ua.processing.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.processing.model.Ticket;
import ua.processing.model.TicketStatus;
import ua.processing.repository.TicketRepository;

@Service
@Transactional
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket create(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket update(Long id) {
        Ticket one = findOne(id);
        one.setStatus(TicketStatus.PAYMENTCOMPLETED);
        return ticketRepository.save(one);
    }

    @Override
    public Ticket findOne(Long id) {
        return ticketRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("ticket which id: " + id + " not found"));
    }
}

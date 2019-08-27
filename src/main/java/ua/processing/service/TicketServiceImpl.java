package ua.processing.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;
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
    public Ticket update(Ticket ticket) {
        Ticket one = findOne(ticket.getId());
        one.setState(TicketStatus.COMPLETED);
        return ticketRepository.save(one);
    }

    @Override
    public Ticket findOne(Long id) {
        return ticketRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("ticket which id: " + id + " not found"));
    }

    @Override
    public List<Ticket> all() {
        return ticketRepository.findAll();
    }
}

package ua.processing.converter;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TicketDto {

    private String routeNumber;
    private String dateTime;
}

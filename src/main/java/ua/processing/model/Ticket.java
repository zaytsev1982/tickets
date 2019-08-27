package ua.processing.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tickets")
@Data
@ToString
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "route_number")
    @NotNull(message = "number cannot be null or empty")
    private Integer routeNumber;
    @Column(name = "date_time", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;
    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus state;
}

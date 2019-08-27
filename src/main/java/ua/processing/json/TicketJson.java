package ua.processing.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TicketJson {

    private String id;
    private String routeNumber;
    private String dateTime;
    private String state;


}

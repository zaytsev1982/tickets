CREATE TABLE if not exists tickets
(
    id           LONG AUTO_INCREMENT not null,
    route_number INTEGER             not null,
    date_time    DATETIME            not null,
    state        VARCHAR(20)         not null,
    PRIMARY KEY (id)
);
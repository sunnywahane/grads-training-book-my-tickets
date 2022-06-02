CREATE TABLE seats(
    id SERIAL PRIMARY KEY,
    show_id int NOT NULL,
    seat_no INT NOT NULL,
    status VARCHAR DEFAULT('NOT BOOKED'),
    FOREIGN KEY(show_id) REFERENCES shows(id)
);

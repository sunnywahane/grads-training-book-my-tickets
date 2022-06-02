UPDATE seats
SET status = 'BOOKED'
WHERE show_id = :show_id AND seat_no = :seat_no;

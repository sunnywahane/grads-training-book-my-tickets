INSERT INTO shows(start_time, movie_id, price)
VALUES (:start_time, :movie_id, :price)
returning *;

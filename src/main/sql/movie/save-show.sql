INSERT INTO shows(start_time, movie_id)
VALUES (:start_time, :movie_id)
returning *;

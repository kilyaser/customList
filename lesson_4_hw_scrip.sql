DROP TABLE if EXISTS films CASCADE;
CREATE TABLE films (
                       id_film    	bigserial PRIMARY KEY NOT NULL,
                       title 		VARCHAR(255) NOT NULL,
                       duration	    TIME NOT NULL,
                       price		    BIGINT NOT NULL
);

DROP TABLE if EXISTS schedules CASCADE;
CREATE TABLE schedules (
                           id_shed	    bigserial PRIMARY KEY NOT NULL,
                           film_id	    int NOT NULL,
                           time	    TIMESTAMP NOT NULL,
                           CONSTRAINT fk_film FOREIGN KEY (film_id) REFERENCES films(id_film)
);

DROP TABLE IF EXISTS tickets CASCADE;
CREATE TABLE tickets (
                         id_ticket		bigserial PRIMARY KEY NOT NULL,
                         ticket_number	int NOT NULL,
                         film_id		int NOT NULL REFERENCES films(id_film),
                         schedule_id	int	NOT NULL REFERENCES schedules(id_shed)
);

INSERT INTO films(title, duration, price) VALUES
                                              ('Старикам тут не место', '2:00', 700),
                                              ('Криминальное чтиво', '2:30', 600),
                                              ('Блеф', '1:00', 500),
                                              ('Пятый элемент', '1:30', 600);

INSERT INTO schedules(film_id, time) VALUES
                                         (1, '2023-03-24 8:00'), (2, '2023-03-24 13:00'), (3, '2023-03-24 14:00'), (4, '2023-03-24 15:00'),
                                         (1, '2023-03-24 19:00'), (2, '2023-03-24 20:00');

INSERT INTO tickets(ticket_number, film_id, schedule_id) VALUES
                                                             (1, 1, 1), (2, 1, 6), (3, 2, 2), (4, 3, 3), (5, 2, 6), (6, 4, 4);

--создаем удобный вид данных для осуществления выборки.
DROP VIEW IF EXISTS films_start_end_time;
CREATE VIEW films_start_end_time AS SELECT f.id_film, f.title, sh.time, f.duration, sh.time + f.duration AS end_time
                                    FROM schedules AS sh
                                             LEFT JOIN films f ON f.id_film = sh.film_id;



SELECT * FROM films_start_end_time;
SELECT films_start_end_time.time from films_start_end_time;

-- проверка наличия временных перекрытий (ошибки в расписании)
select title, time, duration,
    exists (select *
    from films_start_end_time fset2
    where fset.id_film <> fset2.id_film
    and (fset.time, fset.end_time) overlaps (fset2.time, fset2.end_time)
    ) as overlaps
from films_start_end_time fset
order by time;



SELECT sh.time, f.title FROM schedules AS sh LEFT JOIN films f ON f.id_film = sh.film_id
UNION
SELECT sh.time + f.duration AS end_time, concat(f.title, ' end')  FROM schedules AS sh LEFT JOIN films f ON f.id_film = sh.film_id;

--  schedules mistakes
SELECT films.* (prev_ed >= start_date OR next_sd <= end_date) AS has_overlap
FROM(SELECT schedules.*, max(schedules.time + flims.duration) over (order by schedules.time ROWS BETWEEN UNBOUNDED PRECEDING AND 1 PRECEDING) AS prev_ed,
             min(schedules.time) over (ORDER BY start_date ROWS BETWEEN 1 following and unbounded following) as next_sd from schedules) films;

SELECT f.title, datetime AS start_show, datetime + f.duration AS end_show FROM schedules AS sh
                                                                                   JOIN films f ON f.id_film = sh.film_id;
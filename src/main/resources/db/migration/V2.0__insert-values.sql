INSERT INTO city (id, name)
VALUES
(1, 'Lajeado'),
(2, 'Porto Alegre'),
(3, 'Teutonia');

INSERT INTO forecast (id, forecast_date, air_speed, humidity, max_temperature, min_temperature, precipitation, shift, weather, weather_status)
VALUES
(1, '2023-06-05', 10, 20, 35, 25, 20, 'DAY', 'CLEAR', 'Sol com nuvens'),
(2, '2023-06-06', 15, 20, 35, 25, 20, 'DAY', 'STORM', 'Tempestade'),
(3, '2023-06-07', 15, 20, 35, 25, 20, 'NIGHT', 'CLEAR', 'Noite nublada'),
(4, '2023-06-08', 10, 20, 35, 25, 20, 'DAY', 'CLEAR', 'Sol com nuvens'),
(5, '2023-06-09', 15, 20, 35, 25, 20, 'DAY', 'STORM', 'Tempestade'),
(6, '2023-06-10', 15, 20, 35, 25, 20, 'NIGHT', 'CLEAR', 'Noite nublada'),
(7, '2023-06-11', 10, 20, 35, 25, 20, 'DAY', 'CLEAR', 'Sol com nuvens'),
(8, '2023-06-12', 15, 20, 35, 25, 20, 'DAY', 'STORM', 'Tempestade'),
(9, '2023-06-13', 15, 20, 35, 25, 20, 'NIGHT', 'CLEAR', 'Noite nublada');


INSERT INTO city_forecast (id_city, id_forecast)
VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9);
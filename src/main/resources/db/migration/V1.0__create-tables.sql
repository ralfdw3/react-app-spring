CREATE TABLE city (
    id bigint generated by default as identity,
    name VARCHAR(255) NOT NULL,
    primary key (id)
);

CREATE TABLE forecast (
    id bigint generated by default as identity,
    weather VARCHAR(255) NOT NULL,
    shift VARCHAR(255) NOT NULL,
    max_temperature DECIMAL(10, 2) NOT NULL,
    min_temperature DECIMAL(10, 2) NOT NULL,
    precipitation INT NOT NULL,
    humidity INT NOT NULL,
    air_speed INT NOT NULL,
    forecast_date DATE NOT NULL,
    weather_status VARCHAR(255),
    primary key (id)
);

CREATE TABLE city_forecast (
    id_forecast BIGINT NOT NULL,
    id_city BIGINT NOT NULL,
    PRIMARY KEY (id_forecast),
    FOREIGN KEY (id_forecast) REFERENCES forecast (id),
    FOREIGN KEY (id_city) REFERENCES city (id)
);
delete from city_forecast;

delete from city;
ALTER TABLE city ALTER COLUMN ID RESTART WITH 1;

delete from forecast;
ALTER TABLE forecast ALTER COLUMN ID RESTART WITH 1;
create TABLE  IF NOT EXISTS meteorological_info (
  id BIGSERIAL NOT NULL,
  city VARCHAR(35) NOT NULL,
  weather_date DATE NOT NULL,
  morning_weather VARCHAR(12) NOT NULL,
  night_weather VARCHAR(12) NOT NULL,
  max_temperature SMALLINT NOT NULL,
  min_temperature SMALLINT NOT NULL,
  humidity SMALLINT NOT NULL,
  precipitation SMALLINT NOT NULL,
  wind_speed SMALLINT NOT NULL,
  PRIMARY KEY (id)
)
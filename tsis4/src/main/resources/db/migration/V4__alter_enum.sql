ALTER TABLE events
ALTER COLUMN theme TYPE varchar(20)
  USING theme::text;
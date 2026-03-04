CREATE TYPE theme_type AS ENUM (
    'SPORT',
    'CINEMA',
    'ART',
    'POLICY',
    'CHARITY',
    'GAME',
    'ANIME'
);

CREATE TABLE events (
    id UUID PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    date_of_start DATE NOT NULL,
    duration INTEGER NOT NULL CHECK (duration > 0),
    description TEXT,
    theme theme_type NOT NULL
);

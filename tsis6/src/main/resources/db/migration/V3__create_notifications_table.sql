CREATE TABLE notifications (
       id          UUID PRIMARY KEY,
       post_id     UUID NOT NULL,
       producer_id UUID NOT NULL,
       message     TEXT NOT NULL,
       created_at  TIMESTAMP NOT NULL
);
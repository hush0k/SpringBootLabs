CREATE TABLE feed_items (
        id          UUID PRIMARY KEY,
        post_id     UUID NOT NULL,
        producer_id UUID NOT NULL,
        content     TEXT NOT NULL,
        hashtags    TEXT,
        received_at TIMESTAMP NOT NULL
);
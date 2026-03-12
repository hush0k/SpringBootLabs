CREATE TABLE producer (
                          id UUID PRIMARY KEY,
                          first_name VARCHAR(255),
                          last_name VARCHAR(255),
                          created_at TIMESTAMP,
                          updated_at TIMESTAMP
);

CREATE TABLE post (
                      id UUID PRIMARY KEY,
                      producer_id UUID UNIQUE,
                      content TEXT,
                      status VARCHAR(50),
                      created_at TIMESTAMP,
                      updated_at TIMESTAMP,
                      CONSTRAINT fk_post_producer
                          FOREIGN KEY (producer_id)
                              REFERENCES producer(id)
);

CREATE TABLE post_hashtags (
                               post_id UUID NOT NULL,
                               hashtag VARCHAR(255),
                               CONSTRAINT fk_post_hashtags_post
                                   FOREIGN KEY (post_id)
                                       REFERENCES post(id)
                                       ON DELETE CASCADE
);
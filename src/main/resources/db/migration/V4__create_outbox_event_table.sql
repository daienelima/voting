CREATE TABLE outbox_event (
                              id UUID PRIMARY KEY,
                              event_type VARCHAR(100) NOT NULL,
                              payload JSONB NOT NULL,
                              created_at TIMESTAMP NOT NULL,
                              processed BOOLEAN DEFAULT FALSE
);

CREATE INDEX idx_outbox_processed
    ON outbox_event(processed);
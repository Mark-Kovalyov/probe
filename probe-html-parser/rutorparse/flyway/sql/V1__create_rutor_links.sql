CREATE TABLE rutor_links(
    id               INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    last_update_time TIMESTAMP,
    name             VARCHAR(255),
    symbolic_size    VARCHAR(30),
    seeders          INT,
    leechers         INT,
    magnet_link      VARCHAR(255)
);

CREATE INDEX name_textidx ON rutor_links USING GIN (to_tsvector('english', name));


-- SELECT *
-- FROM rutor_links
-- WHERE to_tsvector('english', body) @@ to_tsquery('english', 'friend');

-- SELECT title
-- FROM pgweb
-- WHERE to_tsvector(body) @@ to_tsquery('friend');

-- SELECT * FROM ts_stat('SELECT vector FROM apod')
-- ORDER BY nentry DESC, ndoc DESC, word
-- LIMIT 10;

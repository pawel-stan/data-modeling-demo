UPDATE person
SET last_name = 'NA'
WHERE last_name IS NULL;

ALTER TABLE person
    ALTER COLUMN last_name SET NOT NULL;
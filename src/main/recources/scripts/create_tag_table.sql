DROP TABLE IF EXISTS tag;

CREATE TABLE tag(
id SERIAL NOT NULL,
tag VARCHAR(50) NOT NULL,
PRIMARY KEY(id)
);
CREATE TABLE todo
(
    id            INT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    datetime      datetime NULL,
    finished      BIT(1) NULL,
    CONSTRAINT pk_todo PRIMARY KEY (id)
);
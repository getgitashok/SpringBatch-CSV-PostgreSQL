DROP TABLE IF EXISTS customer;

CREATE TABLE customer  (
    proj_id Bigserial PRIMARY KEY NOT NULL,
    opprtunity_name VARCHAR(20),
    asso_proj VARCHAR(20),
    descri VARCHAR(20),
    owner VARCHAR(20)
);

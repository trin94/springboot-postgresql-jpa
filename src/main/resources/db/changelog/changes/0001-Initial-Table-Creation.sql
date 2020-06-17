create table employees
(
    id         bigserial not null
        constraint employees_pk
            primary key,
    first_name varchar not null,
    last_name  varchar not null,
    email      varchar not null
);

alter table employees
    owner to postgres;


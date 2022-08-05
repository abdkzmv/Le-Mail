create table mailservice.mail
(
    id        int auto_increment
        primary key,
    from_user text null,
    to_user   text null,
    title     text null,
    message   text null
);

create table mailservice.user
(
    id            int auto_increment
        primary key,
    full_name     text null,
    username      text null,
    email         text null,
    user_password text null
);



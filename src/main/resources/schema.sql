create table if not exists users(
                      username text not null primary key,
                      password text not null,
                      enabled boolean not null
                  );

create table if not exists  authorities (
                             username text not null,
                             authority text not null,
                             constraint fk_authorities_users foreign key(username) references users(username)
                         );

--create unique index if not exists ix_auth_username on authorities (username,authority);

create table if not exists  groups (
                        id bigint not null primary key,
                        group_name text not null
                    );

create table if not exists  group_authorities (
                                   group_id bigint not null,
                                   authority text not null,
                                   constraint fk_group_authorities_group foreign key(group_id) references groups(id)
                               );

create table if not exists  group_members (
                               id bigint primary key,
                               username text not null,
                               group_id bigint not null,
                               constraint fk_group_members_group foreign key(group_id) references groups(id)
                           );











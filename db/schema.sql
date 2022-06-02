create table todo (
    id uuid primary key,
    "name" text not null,
    done boolean not null default false
)
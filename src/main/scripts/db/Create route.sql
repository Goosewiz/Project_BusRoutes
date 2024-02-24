create schema "route";

create table "route".route(
	"id" bigserial primary key,
	"number" text not null,
	"type" text not null,
	"time_interval" int not null,
	"time_start" time not null,
	"time_end" time not null
);
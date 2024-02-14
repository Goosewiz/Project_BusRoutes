create schema "route";

create table "route".route(
	"id" bigserial primary key,
	"number" text not null,
	"type" text not null,
	"time_interval" int not null,
	"time_start" time not null,
	"time_end" time not null
);

create table "route".route_with_stops(
	"id" bigserial primary key,
	"route_id" bigint,
	foreign key ("route_id")
	references "route"."route"("id")
	on delete restrict
);
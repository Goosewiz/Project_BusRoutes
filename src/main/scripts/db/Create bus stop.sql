create schema "bus_stop";

create table "bus_stop".bus_stop(
	"id" bigserial primary key,
	"title" text not null,
	"latitude" double precision not null,
	"longtitude" double precision not null
);

create table "bus_stop".bus_at_stop(
	"bus_stop_id" bigint,
	"bus_at_stop_id" bigserial primary key,
	"time" int not null,
	"route_with_stops_id" bigint,
	foreign key ("bus_stop_id")
	references "bus_stop"."bus_stop"("id")
	on delete restrict,
	foreign key ("route_with_stops_id")
	references "route"."route_with_stops"("id")
	on delete restrict
);
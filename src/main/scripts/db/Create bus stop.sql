create schema "bus_stop";
drop table "bus_stop".bus_stop;
drop table "bus_stop".bus_at_stop;

create table "bus_stop".bus_stop(
	"id" bigserial primary key,
	"title" text not null,
	"latitude" double precision not null,
	"longitude" double precision not null
);

create table "bus_stop".bus_at_stop(
	"bus_stop_id" bigint,
	"time" int not null,
	"route_id" bigint,
	foreign key ("bus_stop_id")
	references "bus_stop"."bus_stop"("id")
	on delete restrict,
	foreign key ("route_id")
	references "route"."route"("id")
	on delete restrict,
	primary key("bus_stop_id", "route_id")
);
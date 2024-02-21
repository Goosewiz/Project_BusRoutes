truncate table "bus_stop"."bus_stop" cascade;
truncate table "bus_stop"."bus_at_stop" cascade;
truncate table "route"."route" cascade;

insert into "bus_stop"."bus_stop"(id, title, latitude, longitude) values
(1,	'Автостанция Аврора',	53.19167048719369,	50.188571236918406),
(2,	'Волгина',	53.191580276308606,	50.18455865223211),
(3,	'Дом молодёжи',	53.191509396212965,	50.18041732151309),
(4,	'Мориса Тореза',	53.19584528875434,	50.178904531896904),
(5,	'Универсам',	53.19908626628693,	50.178599696151075),
(6,	'Метро Гагаринская',	53.200389857141644,	50.175170446023245),
(7,	'Клиники Медуниверситета',	53.2001444779296,	50.16644668513761),
(8,	'Метро Московская',	53.20288917765889,	50.160709800783636),
(9,	'Киевская',	53.20348919424873,	50.14965476730723),
(10,	'пл. Памяти',	53.20236776087554,	50.13666838396195),
(11,	'Арцыбушевская',	53.201053046637355,	50.129100781831426),
(12,	'пл. Сельского хозяйства',	53.20576922687987,	50.12309829004391),
(13,	'Дворец спорта',	53.20330734482743,	50.11609210122169),
(14,	'Самарсая пл.',	53.201271280914995,	50.11130351715964),
(15,	'Ульяновская',	53.19988020635001,	50.10833528141822),
(16,	'пл. Куйбышева',	53.19571676486584,	50.0997004129408),
(17,	'Дом промышленности',	53.1941563981668,	50.09313483426775),
(18,	'Некрасовская',	53.18989151699976,	50.09032300310216),
(19,	'пл. Революции',	53.185077717734,	50.08680123944492),
(20,	'Степана Разина',	53.18170637067523,	50.082021545493944),
(21,	'Хлебная пл.',	53.17967278997085,	50.079584950598),
(22,	'Клуб Дзержинского',	53.18367832965261,	50.083475661356054),
(23,	'Сквер Мичурина',	53.19994747889607,	50.13001142178425),
(24,	'Пролетарская',	53.20104381569362,	50.15141451066963),
(25,	'Тухачевского',	53.19757776562491,	50.15824877923959),
(26,	'Мехзавод №1',	53.20469619839272,	50.17832928081557),
(27,	'Гаражная',	53.20750490298622,	50.17642567582173),
(28,	'Революционная',	53.20887364338554,	50.17325530476615),
(29,	'Клиники Медуниверситета',	53.20532429627104,	50.165053292361),
(30,	'Мичурина',	53.21145854944697,	50.15558237790279),
(31,	'Самарский филиал Третьяковской галереи',	53.21589926449405,	50.14670033783502),
(32,	'Челюскинцев',	53.210589539380265,	50.140837386422646),
(33,	'пл. Героев 21-й армии',	53.208103330168775,	50.13609524088431),
(34,	'Первомайская',	53.20529351409535,	50.129982250823396),
(35,	'Красноармейская',	53.193673912977054,	50.10568878747489),
(36,	'Льва Толстого',	53.18902470205148,	50.10293212640545),
(37,	'Ленинградская',	53.18566078810112,	50.10020700204636);



insert into "route"."route"(id, "number", "type", time_interval, time_start, time_end) values
(1,	'24',	'прямой',	20,	'06:00:00',	'13:00:00'),
(2,	'24',	'прямой',	20,	'13:00:00',	'18:00:00'),
(3,	'24',	'обратный',	18,	'06:00:00',	'13:00:00'),
(4,	'24',	'обратный',	18,	'13:00:00',	'18:00:00'),
(5,	'2',	'прямой',	20,	'06:00:00',	'13:00:00'),
(6,	'2',	'прямой',	20,	'13:00:00',	'18:00:00'),
(7,	'2',	'обратный',	20,	'06:00:00',	'13:00:00'),
(8,	'2',	'обратный',	20,	'13:00:00',	'18:00:00');


insert into "bus_stop"."bus_at_stop"(bus_stop_id, "time", route_id) values
(	1	,	20	,	1	),
(	2	,	40	,	1	),
(	3	,	60	,	1	),
(	4	,	80	,	1	),
(	5	,	100	,	1	),
(	6	,	120	,	1	),
(	7	,	140	,	1	),
(	8	,	160	,	1	),
(	9	,	180	,	1	),
(	10	,	200	,	1	),
(	11	,	220	,	1	),
(	12	,	240	,	1	),
(	13	,	260	,	1	),
(	14	,	280	,	1	),
(	15	,	300	,	1	),
(	16	,	320	,	1	),
(	17	,	340	,	1	),
(	18	,	360	,	1	),
(	19	,	380	,	1	),
(	20	,	400	,	1	),
(	21	,	420	,	1	),
(	1	,	20	,	2	),
(	2	,	40	,	2	),
(	3	,	60	,	2	),
(	4	,	80	,	2	),
(	5	,	100	,	2	),
(	6	,	120	,	2	),
(	7	,	140	,	2	),
(	8	,	160	,	2	),
(	9	,	180	,	2	),
(	10	,	200	,	2	),
(	11	,	220	,	2	),
(	12	,	240	,	2	),
(	13	,	260	,	2	),
(	14	,	280	,	2	),
(	15	,	300	,	2	),
(	16	,	320	,	2	),
(	17	,	340	,	2	),
(	18	,	360	,	2	),
(	19	,	380	,	2	),
(	20	,	400	,	2	),
(	21	,	420	,	2	),
(	21	,	18	,	3	),
(	22	,	36	,	3	),
(	19	,	54	,	3	),
(	18	,	72	,	3	),
(	17	,	90	,	3	),
(	16	,	108	,	3	),
(	15	,	126	,	3	),
(	14	,	144	,	3	),
(	13	,	162	,	3	),
(	12	,	180	,	3	),
(	11	,	198	,	3	),
(	23	,	216	,	3	),
(	10	,	234	,	3	),
(	9	,	252	,	3	),
(	24	,	270	,	3	),
(	25	,	288	,	3	),
(	7	,	306	,	3	),
(	6	,	324	,	3	),
(	5	,	342	,	3	),
(	4	,	360	,	3	),
(	3	,	378	,	3	),
(	2	,	396	,	3	),
(	1	,	414	,	3	),
(	21	,	18	,	4	),
(	22	,	36	,	4	),
(	19	,	54	,	4	),
(	18	,	72	,	4	),
(	17	,	90	,	4	),
(	16	,	108	,	4	),
(	15	,	126	,	4	),
(	14	,	144	,	4	),
(	13	,	162	,	4	),
(	12	,	180	,	4	),
(	11	,	198	,	4	),
(	23	,	216	,	4	),
(	10	,	234	,	4	),
(	9	,	252	,	4	),
(	24	,	270	,	4	),
(	25	,	288	,	4	),
(	7	,	306	,	4	),
(	6	,	324	,	4	),
(	5	,	342	,	4	),
(	4	,	360	,	4	),
(	3	,	378	,	4	),
(	2	,	396	,	4	),
(	1	,	414	,	4	),
(	1	,	20	,	5	),
(	2	,	40	,	5	),
(	3	,	60	,	5	),
(	4	,	80	,	5	),
(	5	,	100	,	5	),
(	26	,	120	,	5	),
(	27	,	140	,	5	),
(	28	,	160	,	5	),
(	29	,	180	,	5	),
(	30	,	200	,	5	),
(	31	,	220	,	5	),
(	32	,	240	,	5	),
(	33	,	260	,	5	),
(	34	,	280	,	5	),
(	12	,	300	,	5	),
(	13	,	320	,	5	),
(	14	,	340	,	5	),
(	15	,	360	,	5	),
(	35	,	380	,	5	),
(	36	,	400	,	5	),
(	37	,	420	,	5	),
(	1	,	20	,	6	),
(	2	,	40	,	6	),
(	3	,	60	,	6	),
(	4	,	80	,	6	),
(	5	,	100	,	6	),
(	26	,	120	,	6	),
(	27	,	140	,	6	),
(	28	,	160	,	6	),
(	29	,	180	,	6	),
(	30	,	200	,	6	),
(	31	,	220	,	6	),
(	32	,	240	,	6	),
(	33	,	260	,	6	),
(	34	,	280	,	6	),
(	12	,	300	,	6	),
(	13	,	320	,	6	),
(	14	,	340	,	6	),
(	15	,	360	,	6	),
(	35	,	380	,	6	),
(	36	,	400	,	6	),
(	37	,	420	,	6	),
(	37	,	20	,	7	),
(	36	,	40	,	7	),
(	35	,	60	,	7	),
(	15	,	80	,	7	),
(	14	,	100	,	7	),
(	13	,	120	,	7	),
(	12	,	140	,	7	),
(	34	,	160	,	7	),
(	33	,	180	,	7	),
(	32	,	200	,	7	),
(	31	,	220	,	7	),
(	30	,	240	,	7	),
(	29	,	260	,	7	),
(	28	,	280	,	7	),
(	27	,	300	,	7	),
(	26	,	320	,	7	),
(	5	,	340	,	7	),
(	4	,	360	,	7	),
(	3	,	380	,	7	),
(	2	,	400	,	7	),
(	1	,	420	,	7	),
(	37	,	20	,	8	),
(	36	,	40	,	8	),
(	35	,	60	,	8	),
(	15	,	80	,	8	),
(	14	,	100	,	8	),
(	13	,	120	,	8	),
(	12	,	140	,	8	),
(	34	,	160	,	8	),
(	33	,	180	,	8	),
(	32	,	200	,	8	),
(	31	,	220	,	8	),
(	30	,	240	,	8	),
(	29	,	260	,	8	),
(	28	,	280	,	8	),
(	27	,	300	,	8	),
(	26	,	320	,	8	),
(	5	,	340	,	8	),
(	4	,	360	,	8	),
(	3	,	380	,	8	),
(	2	,	400	,	8	),
(	1	,	420	,	8	);

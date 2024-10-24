INSERT INTO library.lib_entity (nameEntity, comment) VALUES ('ООО ЦИТРБ', 'ранее ООО ГАЗ ИТ-Сервис');
INSERT INTO library.lib_entity ("nameentity", comment, active) VALUES ('СВА/КРД', 'НижКомАвто', true);
INSERT INTO library.lib_entity ("nameentity", comment, active) VALUES ('Юридическое подразделение', 'НижКомАвто', true);
INSERT INTO library.lib_entity ("nameentity", comment, active) VALUES ('Архивное дело', 'НижКомАвто', true);
INSERT INTO library.lib_entity ("nameentity", comment, active) VALUES ('Оснастка', 'НижКомАвто', true);
INSERT INTO library.lib_entity ("nameentity", comment, active) VALUES ('Бухгалтерия', 'НижКомАвто', true);

INSERT INTO library."lib_job_employee" (fio, position, coments, active) VALUES ('Зайцев М.А.', '2-й этаж', 'уточнить новое место', true);
INSERT INTO library."lib_job_employee" (fio, position, coments, active) VALUES ('Костюк А.В.', '52', ' ', true);
INSERT INTO library."lib_job_employee" (fio, position, coments, active) VALUES ('Каманина Л.А.', '64', ' ', true);
INSERT INTO library."lib_job_employee" (fio, position, coments, active) VALUES ('Григорьев Д.М.', '65', ' ', true);
INSERT INTO library."lib_job_employee" (fio, position, coments, active) VALUES ('Жирнов С.А.', '58', ' ', true);
INSERT INTO library."lib_job_employee" (fio, position, coments, active) VALUES ('Квочкин А.Ю.', '66', ' ', true);
INSERT INTO library."lib_job_employee" (fio, position, coments, active) VALUES ('Квочкин А.Ю.', 'новый', 'Не подключен,  возле Торопова М.В.', true);
INSERT INTO library."lib_job_employee" (fio, position, coments, active) VALUES ('Мергазымова О.В.', 'Шкаф', ' ', true);
INSERT INTO library."lib_job_employee" (fio, position, coments, active) VALUES ('Мергазымова О.В.', '59', ' ', true);
INSERT INTO library."lib_job_employee" (fio, position, coments, active) VALUES ('Торопов М.В.', '51', ' ', true);

INSERT INTO library.lib_job_place (coments, number_place, place_employee) VALUES ('10 этаж, 11 кабинет',66,7);
INSERT INTO library.lib_job_place (coments, number_place, place_employee) VALUES ('10 этаж, 11 кабинет',51,10);
INSERT INTO library.lib_job_place (coments, number_place, place_employee) VALUES ('10 этаж, 11 кабинет',59,9);
INSERT INTO library.lib_job_place (coments, number_place, place_employee) VALUES ('10 этаж, 11 кабинет',58,5);
INSERT INTO library.lib_job_place (coments, number_place, place_employee) VALUES ('10 этаж, 11 кабинет',64,3);
INSERT INTO library.lib_job_place (coments, number_place, place_employee) VALUES ('10 этаж, 11 кабинет',52,2);
INSERT INTO library.lib_job_place (coments, number_place, place_employee) VALUES ('10 этаж, 11 кабинет','новый',7);
INSERT INTO library.lib_job_place (coments, number_place, place_employee) VALUES ('10 этаж, 11 кабинет','шкаф',8);
INSERT INTO library.lib_job_place (coments, number_place, place_employee) VALUES ('10 этаж, 11 кабинет','2-й этаж',1);

insert into library.lib_division (name_division, organisation, active) values ('DocsVision - Документооборот','ООО \"ЦИТРБ\"',true);
insert into library.lib_division (name_division, organisation, active) values ('Юридический отдел','АО \"НижКомАвто\"',true);
insert into library.lib_division (name_division, organisation, active) values ('СВА/КРД','АО \"НижКомАвто\"',true);
insert into library.lib_division (name_division, organisation, active) values ('Бухгалтерия НКА','АО \"НижКомАвто\"',true);
insert into library.lib_division (name_division, organisation, active) values ('Персонал ЦИТРБ','ООО \"ЦИТРБ\"',true);

INSERT INTO library."doc_schema_doc" (name, "date_create", id_division, current) VALUES ('Акт по тестированию', '26.09.2024', 1, true);
INSERT INTO library."doc_schema_doc" (name,"date_create", id_division, current) VALUES ('Акт ввода в промышленную эксплуатацию', '26.09.2024', 1, true);


INSERT INTO library."lib_step_project" ("name_step_project", comment) VALUES ('Протоколы', 'Приказы, решения');
INSERT INTO library."lib_step_project" ("name_step_project", comment) VALUES ('Проектые документы/Устав', 'Устав, Договора, документы Ю.Л.');
INSERT INTO library."lib_step_project" ("name_step_project", comment) VALUES ('Техническое описание', 'ЧТЗ, документация');
INSERT INTO library."lib_step_project" ("name_step_project", comment) VALUES ('Сдача проекта', 'Руководства пользователя/администратора, тех.документация');

INSERT INTO library."projects_list" (name, "id_division", "head_manager", "responsible_from_business", curator, date_create, closed, date_end, date_start, owner)
VALUES ('СВА', 1, 'Иванец', 'Маньков М., Шегуров', 'Квочкин А.Ю.', '01.01.2024', false, '01.01.2024', '01.01.2024', 1);
INSERT INTO library."projects_list" (name, "id_division", "head_manager", "responsible_from_business", curator, date_create, closed, date_end, date_start, owner)
VALUES ('Экосистема юристов', 2, 'Костин', 'Чернышев С.В.', 'Квочкин А.Ю.', '01.01.2024', false, '01.01.2024', '01.01.2024', 1);
INSERT INTO library."projects_list" (name, "id_division", "head_manager", "responsible_from_business", curator, date_create, closed, date_end, date_start, owner)
VALUES ('Архивное делопроизводство', 3, 'Сухарева', '', 'Квочкин А.Ю.', '01.01.2024', false, '01.01.2024', '01.01.2024', 1);
INSERT INTO library."projects_list" (name, "id_division", "head_manager", "responsible_from_business", curator, date_create, closed, date_end, date_start, owner)
VALUES ('Оснастка', 4, '', '', 'Мергазымова О.В.', '01.01.2024', false, '01.01.2024', '01.01.2024', 1);
INSERT INTO library."projects_list" (name, "id_division", "head_manager", "responsible_from_business", curator, date_create, closed, date_end, date_start, owner)
VALUES ('Постановка и исполнение целей KPI сотрудников', 5, '', 'Куликова, Маргун Елена Валерьевна', 'Мергазымова О.В.', '01.01.2024', false, '01.01.2024', '01.01.2024', 1);

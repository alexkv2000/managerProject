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

insert into library.lib_division (name_division, organisation, active) values ('DocsVision - Документооборот','ООО "ЦИТРБ"',true);
insert into library.lib_division (name_division, organisation, active) values ('Юридический отдел','АО "НижКомАвто"',true);
insert into library.lib_division (name_division, organisation, active) values ('СВА/КРД','АО "НижКомАвто"',true);
insert into library.lib_division (name_division, organisation, active) values ('Бухгалтерия НКА','АО "НижКомАвто"',true);
insert into library.lib_division (name_division, organisation, active) values ('Персонал ЦИТРБ','ООО "ЦИТРБ"',true);
insert into library.lib_division (name_division, organisation, active) values ('Оснастка','---',false);
insert into library.lib_division (name_division, organisation, active) values ('Архивное делопроизводство','АО "НижКомАвто"',true);
insert into library.lib_division (name_division, organisation, active) values ('Приказ/KPI','АО "НижКомАвто"',true);


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



insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2,	'24.10.2023',	560000.00,0,0,0,'Блок 1 - 1. Технический проект по функциональным требованиям учета судебных дел, интеграции и миграции. 2. Акт сдачи -приёмки оказанных услу',
           'Этап 1. Анализ и проектирование функциональности Блока 1. Анализ интеграции, миграции из сторонних сервисов.', 4, 'Неделя', true, true, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2, '25.12.2023',	1538000.00,0,0,0,'Блок 1 - 1. Протокол сдачи-приемки ЦИТРБ 2. Акт сдачи-приёмки оказанных услуэ',
           'Этап 2. Адаптация функциональности Блока 1 на основании Техпроекта', 8,'Неделя',true, true, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2, '26.02.2024',	1566000.00,0,0,0,'Блок 1 - 1. Протокол сдачи-приемки ЦИТРБ 2. Акт сдачи-приёмки оказанных услуг',
           'Этап 3. Настройка Интеграции и миграция данных', 8,'Неделя',true, true, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2, '07.05.2024',	542000.00,0,0,0,'Блок 1 - 1. Протокол проведения тестирования. 2. Реестр требований, выявленных по результатам тестирования 3. Протокол проведения обучения 4. Акт сдачи-приёмки оказанных услуг.',
           'Этап 4. Тестовая эксплуатация и исправление замечаний Блок 1',	6,	'Неделя',false,false, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2, '20.05.2024',	30000.00,0,0,0,'Блок 1 - 1. Протокол ввода в опытно-промышленную эксплуатацию 2. Журнал проведения ОПЭ 3. Акт сдачи-приёмки оказанных услуг',
           'Этап 5. Опытно-промышленная эксплуатация',	2,	'Неделя', false,false, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2, '23.11.2024',	300000.00,0,0,0,'Блок 1 - 1. Отчет по сопровождению 2. Акт сдачи-приёмки оказанных услу',
           'Этап 6. Промышленная эксплуатация',28, 'Неделя',	false,false, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2, '26.12.2023',	1060000.00,0,0,0,'Блок 1 - 1. Передано решение RKIT: управление претензионно-исковой работой и актами госорганов в качестве прототипа для создания экосистемы для юристов Группы ГАЗ 2. Акт сдачи приёмки оказанных услу',
           'Этап 1. Подготовка к адаптации и выполнение предварительных настроек продукта RKIT: Управление претензионно-исковой работой и актами госорганов» для создания прикладного решения Экосистема для юристов',	3,	'Неделя', true,true, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2, '13.06.2024',	180000.00,0,0,0,'Блок 2 - 1. Дополнение Технического проекта по функциональным требованиям учета судебных дел 2. Акт сдачи-приёмки оказанных услуг',
           'Этап 1. Анализ и проектирование функциональности Блока 2',	3,	'Неделя',	false,false, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2, '13.08.2024',	759000.00,0,0,0,'Блок 2 - 1. Протокол сдачи-приемки ЦИТРБ 2. Акт сдачи-приёмки оказанных услуг',
           'Этап 2. Адаптация функциональности Блока 2 на основании Техпроекта',	8,	'Неделя',false,false, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2, '13.10.2024',	906000.00,0,0,0,'Блок 2 - 1. Протокол проведения тестирования. 2. Реестр требований, выявленных по результатам тестирования 3. Протокол проведения обучения 4. Акта сдачи-приёмки оказанных ус',
           'Этап 3. Настройка Интеграции',	8,	'Неделя',false,false, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2, '14.01.2025',	470000.00, 0,0,0,'Блок 2 -1. Протокол проведения тестирования. 2. Реестр требований, выявленных по результатам тестирования 3. Протокол проведения обучения 4. Акта сдачи-приёмки оказанных усэб',
           'Этап 4. Тестовая эксплуатация и исправление замечаний Блок 2',	10,	'Неделя',false,false, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2, '01.02.2025',	30000.00,0,0,0,'Блок 2 - 1. Протокол ввода в промышленную эксплуатацию 2. Журнал проведения ОПЭ 3. Акта сдачи-приёмки оказанных усл',
           'Этап 5. Опытно-промышленная эксплуатация',	2,	'Неделя',false,false, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2, '01.08.2025',	300000.00,0,0,0,'Блок 2 - 1. Отчет по сопровождению 2. Акта сдачи-приёмки оказанных услуг',
           'Этап 6. Промышленная эксплуатация',	24,	'Неделя',false,false, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2, '14.04.2025',	210000.00,0,0,0,'Блок 3 - 1. Дополнение Технического проекта по функциональным требованиям учета судебных дел 2. Акта сдачи-приёмки оказанных услуг',
           'Этап 1. Анализ и проектирование функциональности Блок 3',	12,	'Неделя',false,false, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2, '14.06.2025',	800000.00,0,0,0,'Блок 3 - 1. Протокол сдачи-приемки ЦИТРБ 2. Акта сдачи-приёмки оказанных услуг',
           'Этап 2. Адаптация функциональности блока 3 на основании Техпроекта',	8,	'Неделя',false,false, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2, '12.07.2025',	201000.00,0,0,0,'Блок 3 - 1. Протокол сдачи-приемки ЦИТРБ 2. Акта сдачи-приёмки оказанных услуг',
           'Этап 3. Настройка Интеграции',	4,	'Неделя',false,false, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2, '24.09.2025',	440000.00,0,0,0,'Блок 3 - 1. Протокол проведения тестирования. 2. Реестр требований, выявленных по результатам тестирования 3. Протокол проведения обучения 4. Акта сдачи-приёмки оказанных ус',
           'Этап 4. Тестовая эксплуатация и исправление замечаний Блок 3',	10,	'Неделя',false,false, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2, '14.10.2025',	60000.00,0,0,0,'Блок 3 - 1. Протокол ввода в промышленную эксплуатацию 2. Журнал проведения ОПЭ 3. Акта сдачи-приёмки оказанных усл',
           'Этап 5. Опытно-промышленная эксплуатация',	2,	'Неделя',false,false, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	2, '14.04.2026',	300000.00,0,0,0,'Блок 3 - 1. Отчет по сопровождению 2. Акта сдачи-приёмки оказанных услуг',
           'Этап 6. Промышленная эксплуатация',	24,	'Неделя',false,false, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	6, '09.09.2023',	862500.00,0,0,0,'Блок 1 - Технический проект по функциональным требованиям учета судебных дел, интеграции и миграции. 2. Акт сдачи -приёмки оказанных услу',
           'Этап 1. Анализ и проектирование функциональности',	4,	'Неделя', false,false, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	6, '15.01.2024',	120000.00,0,0,0,'Блок 2 - 1. Протокол проведения тестирования. 2. Реестр требований, выявленных по результатам тестирования 3. Протокол проведения обучения 4. Акт сдачи-приёмки оказанных у',
           'Этап 2. Настройка Интеграции и миграция данных',	8,	'Неделя', true,true, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	6, '17.06.2024',	100000.00,0,0,0,'Блок 2 - 1. Отчет по сопровождению 2. Акт сдачи-приёмки оказанных услу',
           'Этап 3. Промышленная эксплуатация',	28,	'Неделя', true,true, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	3, '22.10.2024',	225000.00,0,0,0,'Блок 4 - Тех.поддержка',	'Этап 1. тех.поддержка', 90, 'Часов',false,false, 2);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	7, '11.11.2024',	0.00,0,0,0,'Внедрение силами ЦИТРБ.	Разработан процесс формирования списка документов для уничтожение, перевод в Архив.',
           'Формирование Актов, Описи дел.',	28,	'Неделя',false,false, 3);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	8, '18.11.2024',	0.00,0,0,0,'Блок 1 - Технический проект по функциональным требованиям KPI сотрудников.',
           'Этап 1. Разработка формы KPI сотрудников',	7,	'Неделя',false,false, 5);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	8, '09.12.2024',	0.00,0,0,0,'Блок 1 - Тестирование карточки KPI',
           'Этап 2. Разработка ролевой модели и представлений карточки',	3,	'Неделя',false,false, 5);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	8, '24.03.2025',	0.00,0,0,0,'Блок 2 - Формирование поисковых запросов, отчетов.',
           'Этап 3. Разработка шаблонов отчета',	3,	'Неделя',false,false, 5);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	8, '27.01.2025',	228000.00,0,0,0,'Блок 3. - web разработка 1. Протокол проведения тестирования. 2. Реестр требований, выявленных по результатам тестирования 3. Протокол проведения обучения 4. Акт сдачи-приёмки оказанных у',
           'Этап 1. Анализ и проектирование функциональности Блока 1',	4,	'Неделя',false,false, 5);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	3, '20.05.2024',	2155000.00,0,0,0,'Блок - Акт ввода в промышленную эксплуатацию.',
           'Эпат ввода в пром.эксплуатацию',	44,	'Неделя', true,true, 5);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	8, '24.03.2025',	1845000.00,0,0,0,'Блок 3 -  web разработка Процесс целеполагания',
           'Этап 2. Реализация Блока 1',	8,	'Неделя',false,false, 5);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	8, '26.05.2025',	786000.00,0,0,0,'Блок 3 -  web разработка Процесс целеполагания',
           'Этап 3 Тестовая эксплуатация и исправление замечаний по Блоку 1',	8,	'Неделя',false,false, 5);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	8, '28.07.2025',	135000.00,0,0,0,'Блок 3 - web разработка Процесс целеполагания',
           'Этап 4. Опытно-промышленная эксплуатация по Блоку1',	8,	'Неделя',false,false, 5);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	8, '26.05.2025',	150000.00,0,0,0,'Блок 4 - web разработка Учет результатов целей',
           'Этап 1. Анализ и проектирование функциональности Блока 2',	4,	'Неделя',false,false, 5);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	8, '28.07.2025',	774000.00,0,0,0,'Блок 4 - web разработка Учет результатов целей',
           'Этап 2. Реализация Блока 2',	8,	'Неделя',false,false, 5);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	8, '29.09.2025',	738000.00,0,0,0,'Блок 4 - web разработка Учет результатов целей',
           'Этап 3 Тестовая эксплуатация и исправление замечаний по Блоку 2',	8,	'Неделя',false,false, 5);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	8, '27.10.2025',	120000.00,0,0,0,'Блок 4 - web разработка  Учет результатов целей',
           'Этап 4. Опытно-промышленная эксплуатация по Блоку 2',	4,	'Неделя',false,false, 5);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	8, '30.03.2026',	2553000.00,0,0,0,'Блок 5 - web разработка Учет результатов целей тех.поддержка, интеграция…',
           'Этап 5. Доработка в рамках функциональных блоков проекта',	24,	'Неделя',false,false, 1);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	1, '01.11.2024',	247500.00,0,0,0,'Тех.поддержка ноябрь-январь 2025г',	'тех.поддержка квартал РКИТ',	12,	'Неделя',true,true, 1);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	1, '25.11.2024',	3241328.00,0,0,0,'Лицензия 2025г DV=(2534439,6 + 200000МЧД) + 20% для ИК, СВА/ДВА',	'требуется 3-х сторонее соглашение для скидки 30%',	48,	'Неделя',false,false, 1);
insert into library.plan_pay_project
(plan_year, division_id, data_planing, opex, opex_nds, capex, capex_nds, comment, step_project, duration, "interval", completed, payment_on_time, plan_project)
values (
           '01.01.2024',	1, ' 21.10.2024',	1210000.00,0,0,0,'Тех поддержка 2025г Договор с РКИТ 2025 - РКИТ (1210т.р.) ДС №1 № Р-ТП-187-2022 / № ДУ02/0027/017/23 от 16.10.2023 : https://sdo.gaz.ru/docsvision/?CardID={86B0540E-DDA4-4047-BB15-6538432D8C72}&ShowPanels=2048&',
           'Создан договор Ид договора 1847387 ,№ договора ""."" от 16.10.2024 ,статус - проект .Контрагент ИНН - 3812087917   РКИТ(Иркутск) Ид контрагента 628253 Предприятие ООО ЦИТРБ',	12,	'Неделя', false,false, 2);


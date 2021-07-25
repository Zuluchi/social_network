--liquibase formatted sql
--changeset nikolai:data-person-post

ALTER TABLE person DROP COLUMN reg_date, DROP COLUMN birth_date, DROP COLUMN last_online_time;

ALTER TABLE person ADD reg_date BIGINT NOT NULL, ADD birth_date BIGINT, ADD last_online_time BIGINT;

INSERT INTO person (first_name,last_name,e_mail,phone,"password",about,town,is_approved,reg_date,birth_date,last_online_time)
VALUES ('Вася', 'Васичкин','vasy@yandex.ru','89998887744','12345','Я Вася','Россия Москва',true,1625127990000,964513590000,1627200965049),
       ('Хома', 'Хомяков', 'homa@yandex.ru','89998887744','12345','Я Хомяков','Россия Москва',true,1625127990000,806660790000,1627200965049),
       ('Иван','Иванов','ivan@yandex.ru','89998887744','12345','Немного обо мне','Россия Москва',true,1625127990000,901355190000,1627200965049),
       ('Пётр','Петров','petr@yandex.ru','89998887744','12345','Немного обо мне','Россия Омск',true,1625127990000,901355190000,1627200965049),
       ('Сергей','Семёнов','cergei@yandex.ru','89998887744','12345','Немного обо мне','Россия Уфа',true,1625127990000,901355190000,1627200965049),
       ('Николай','Аксёнов','nik@yandex.ru','89998887744','12345','Немного обо мне','Россия Ногинск',true,1625127990000,207131190000,1627200965049),
       ('Дминрий','Скороход','dmitriy@yandex.ru','89998887744','12345','Немного обо мне','Россия Тагил',true,1625127990000,901355190000,1627200965049),
       ('Илья','Карапузов','ilia@yandex.ru','89998887744','12345','Немного обо мне','Россия Керчь',true,1625127990000,901355190000,1627200965049),
       ('Данила','Крюков','danila@yandex.ru','89998887744','12345','Немного обо мне','Россия Воронеж',true,1625127990000,901355190000,1627200965049),
       ('Роберт','Карасян','robert@yandex.ru','89998887744','12345','Немного обо мне','Россия Талдом',false,1625127990000,901355190000,1627200965049);

ALTER TABLE post DROP COLUMN time;

ALTER TABLE post ADD time BIGINT;

INSERT INTO public.post ("time",author_id,tittle,post_text,is_blocked)
	VALUES ('1625735590000',4,'Спор о первом языке програмирования','<p>Много текста про разные языки програмирования<br>Но однозначного ответа нет</p>',false),
	       ('1625765590000',2,'Язык програмирования "Java"','<p>Много текста про java<br>Надо учить</p>',false),
	       ('1625795590000',6,'Язык програмирования "HTML"','<p>Много текста про HTML<br>Стань верстальщиком</p>',false),
	       ('1625815590000',7,'Язык програмирования "C#"','<p>Много текста про C# <br>Ещё ного текста про C# <br>Много текста про C# <br>Тоже можно учить</p>',false),
	       ('1625835590000',3,'Бэк или Фронт, вот в чём вопрос','<p>Много текста про разные языки програмирования<br>Но однозначного ответа нет</p>',false),
	       ('1625855590000',4,'Git. С чем его едят','<p>Много текста, много команд<br>Но пока не попробуешь не поймёшь</p>',false),
	       ('1625875590000',4,'Базы данных, какие бывают','<p>Много текста про разные БД<br>Выбирай на вкус</p>',false),
	       ('1625885590000',6,'SQL. Неограниченные возможности','<p>Много текста про SQL<br>Это на много больше чем SELECT * FROM</p>',false),
	       ('1625905590000',8,'Hibernate','<p>Длинная история <br>Надо знать</p>',false),
	       ('1625935590000',4,'Алгоритмы в програмировании','<p>Немного об алгоритмах с примерами<br>Всем ли они нужны?</p>',false),
	       ('1625955590000',4,'Патерны проектирования','<p>Много разных патернов<br>Хорошо бы их все знать, но так сразу не запомнишь</p>',false),
	       ('1625975590000',6,'Big data','<p>Что такое, как с ними работать<br>Инструменты для работы</p>',false),
	       ('1625985590000',3,'Среда разработки','<p>Какую выбрать<br>Однозначного ответа нет, но Intellij Idea надо брать</p>',false),
	       ('1625991990000',4,'Тестирование','<p>Какое бывает<br>Очень важно и нужно</p>',false),
	       ('1627200965000',4,'Логирование','<p>Надо знать и уметь</p>',false);
INSERT INTO "USERS" (id, email, password, name, lastname, position, sector, macrosector, organization, registration_date) VALUES ('4f4c1918-64d9-4913-a703-69de09073ba3','jhon.doe1@email.com', 'Password123','Jhon', 'Doe', 'Student', 'Education', 'Services', 'Icesi','2022-02-02');
INSERT INTO "USERS" (id, email, password, name, lastname, position, sector, macrosector, organization, registration_date) VALUES ('fced7eb8-28c6-4f25-a6c5-76a485b1c74a','nicolas@email.com', 'Password123', 'Nicolas', 'Penagos', 'Student', 'Education', 'Services', 'Icesi','2022-02-02');
INSERT INTO "USERS" (id, email, password, name, lastname, position, sector, macrosector, organization, registration_date) VALUES ('05e16595-98c5-46c3-80cb-209915e52588','nicolas.penagos@email.com', 'Password123', 'Nicolas', 'Penagos', 'Student', 'Education', 'Services', 'Icesi','2022-02-02');
INSERT INTO "USERS" (id, email, password, name, lastname, position, sector, macrosector, organization, registration_date) VALUES ('423bba42-3b09-4e70-9c7d-7ee48289a8f6','valentina@email.com', 'DummyPassword', 'Valentina', 'Zapata', 'Designer', 'TICs', 'Services', 'Carvajal','2021-02-02');

INSERT INTO "TERMS_AND_CONDITIONS" (terms_and_conditions_id, link, acceptance_date, user_id) VALUES ('f7289f4d-6151-4763-947c-44767091558c','www.link.com', '2022-02-02', '4f4c1918-64d9-4913-a703-69de09073ba3');
INSERT INTO "TERMS_AND_CONDITIONS" (terms_and_conditions_id, link, acceptance_date, user_id) VALUES ('8416a208-56cb-4d2b-b5c6-c8dadca2bf68','www.link.com', '2022-02-02', 'fced7eb8-28c6-4f25-a6c5-76a485b1c74a');
INSERT INTO "TERMS_AND_CONDITIONS" (terms_and_conditions_id, link, acceptance_date, user_id) VALUES ('df0e6910-d0db-47a2-af01-ed102cac03b8','www.link3.com', '2022-02-02', '423bba42-3b09-4e70-9c7d-7ee48289a8f6');

INSERT INTO "QUESTIONS" (id, question_order, question_text,mandatory, justify, type, activity_id ) VALUES ('1539215e-879a-4d04-aaff-b99ffd770d20', 1, 'First question statement', true,true, 'INCREMENTAL_SINGLE_ANSWER', '1ac711f2-682c-46e3-83aa-7fecf28f1082');
INSERT INTO "QUESTIONS" (id, question_order, question_text,mandatory, justify,type, activity_id ) VALUES ('200176e0-2600-40e4-87ee-1b832a425caf', 1, 'Second question statement', true,true, 'SINGLE_ANSWER', '200176e0-2600-40e4-87ee-1b832a425caf');
INSERT INTO "QUESTIONS" (id, question_order, question_text,mandatory, justify,type, activity_id ) VALUES ('8090410a-0f48-462a-a1d3-e002a2a5ca1f', 1, 'Question statement', true,true, 'MULTIPLE_CHOICE', '1ac711f2-682c-46e3-83aa-7fecf28f1082');
INSERT INTO "QUESTIONS" (id, question_order, question_text,mandatory, justify,type, activity_id ) VALUES ('b9589cbf-d309-447d-8b34-0b4d3a890c1f', 1, 'Question to delete statement', true,true, 'MULTIPLE_CHOICE', '1ac711f2-682c-46e3-83aa-7fecf28f1082');
INSERT INTO "QUESTIONS" (id, question_order, question_text,mandatory, justify,type, activity_id ) VALUES ('98af9133-409b-41ad-a7f8-3f0f7f3b92f1', 1, 'Last question', true,true, 'MULTIPLE_CHOICE', '1ac711f2-682c-46e3-83aa-7fecf28f1082');

INSERT INTO "QUESTION_OPTIONS" (question_option_id, option_order, option_value, question_id) VALUES ('c43124d1-56cc-476e-be0a-16d4b9ff3e0f',1,'First option.', '1539215e-879a-4d04-aaff-b99ffd770d20');
INSERT INTO "QUESTION_OPTIONS" (question_option_id, option_order, option_value, question_id) VALUES ('e9b64f64-fc87-42a1-9ff8-f70e5287caad',1,'First option.', '200176e0-2600-40e4-87ee-1b832a425caf');
INSERT INTO "QUESTION_OPTIONS" (question_option_id, option_order, option_value, question_id) VALUES ('00cd2ffc-9d57-418e-93e5-bab7da822dc1',1,'First option.', '8090410a-0f48-462a-a1d3-e002a2a5ca1f');
INSERT INTO "QUESTION_OPTIONS" (question_option_id, option_order, option_value, question_id) VALUES ('0e43ef5a-4744-4114-8d81-53eab19a7ebc',1,'First option.', 'b9589cbf-d309-447d-8b34-0b4d3a890c1f');
INSERT INTO "QUESTION_OPTIONS" (question_option_id, option_order, option_value, question_id) VALUES ('482db004-5f3e-45ef-8cb8-fed17e5af69f',1,'First option.', '98af9133-409b-41ad-a7f8-3f0f7f3b92f1');

INSERT INTO  "ACTIVITIES" (activity_id, description, title, name, score) values ('1ac711f2-682c-46e3-83aa-7fecf28f1082', 'Description text', 'A1 Title', 'A1', 1000.0);
INSERT INTO  "ACTIVITIES" (activity_id, description, title, name, score) values ('3c5834fc-fa42-4170-abeb-d7cb43198554', 'Description text 2', 'A2 Title', 'A2', 2000.0);
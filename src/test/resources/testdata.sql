INSERT INTO "USERS" (user_id, email, password, name, lastname, position, sector, macrosector, organization, registration_date) VALUES ('4f4c1918-64d9-4913-a703-69de09073ba3','jhon.doe1@email.com', 'Password123','Jhon', 'Doe', 'Student', 'Education', 'Services', 'Icesi','2022-02-02');
INSERT INTO "USERS" (user_id, email, password, name, lastname, position, sector, macrosector, organization, registration_date) VALUES ('fced7eb8-28c6-4f25-a6c5-76a485b1c74a','nicolas@email.com', 'Password123', 'Nicolas', 'Penagos', 'Student', 'Education', 'Services', 'Icesi','2022-02-02');
INSERT INTO "USERS" (user_id, email, password, name, lastname, position, sector, macrosector, organization, registration_date) VALUES ('05e16595-98c5-46c3-80cb-209915e52588','nicolas.penagos@email.com', 'Password123', 'Nicolas', 'Penagos', 'Student', 'Education', 'Services', 'Icesi','2022-02-02');
INSERT INTO "USERS" (user_id, email, password, name, lastname, position, sector, macrosector, organization, registration_date) VALUES ('423bba42-3b09-4e70-9c7d-7ee48289a8f6','valentina@email.com', 'DummyPassword', 'Valentina', 'Zapata', 'Designer', 'TICs', 'Services', 'Carvajal','2021-02-02');

INSERT INTO "TERMS_AND_CONDITIONS" (terms_and_conditions_id, link, acceptance_date, user_id) VALUES ('f7289f4d-6151-4763-947c-44767091558c','www.link.com', '2022-02-02', '4f4c1918-64d9-4913-a703-69de09073ba3');
INSERT INTO "TERMS_AND_CONDITIONS" (terms_and_conditions_id, link, acceptance_date, user_id) VALUES ('8416a208-56cb-4d2b-b5c6-c8dadca2bf68','www.link.com', '2022-02-02', 'fced7eb8-28c6-4f25-a6c5-76a485b1c74a');
INSERT INTO "TERMS_AND_CONDITIONS" (terms_and_conditions_id, link, acceptance_date, user_id) VALUES ('df0e6910-d0db-47a2-af01-ed102cac03b8','www.link3.com', '2022-02-02', '423bba42-3b09-4e70-9c7d-7ee48289a8f6');

INSERT INTO "QUESTIONS" (question_id, question_order, question_text,mandatory, type, activity ) VALUES ('8090410a-0f48-462a-a1d3-e002a2a5ca1f', 1, 'Question statement', true, 'MULTIPLE_CHOICE', 'A1');

INSERT INTO "QUESTION_OPTIONS" (question_option_id, option_order, option_value, question_id) VALUES ('00cd2ffc-9d57-418e-93e5-bab7da822dc1',1,'First option.', '8090410a-0f48-462a-a1d3-e002a2a5ca1f');

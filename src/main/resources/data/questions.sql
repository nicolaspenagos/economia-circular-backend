
-- ************ ACTIVITY 1 ************

-- Question 1
insert into questions (id, question_order, question_text,mandatory, justify, type, hint, activity_id ) values ('26034723-97cd-44ee-a98f-8908557c21ec', 0, ' ¿En qué nivel se ubica la empresa respecto al desarrollo o implementación de tecnologías que impulsan la economía circular', true, true, 'INCREMENTAL_SINGLE_CHOICE', '', '3b270b5c-15a3-4f2b-a33b-3c25d5b15e7e');


insert into question_options (id, option_order, option_value, hint,question_id, exclusive, not_apply, dependent_question_id) values ('faa0260c-e663-4d4c-a10e-ec6d116ee6e0', 0, 'Ninguno', '','26034723-97cd-44ee-a98f-8908557c21ec', true, false, null), ('8fa46017-1f58-41cd-92b7-f208e6f86fdc', 1, 'Ha identificado oportunidades','', '26034723-97cd-44ee-a98f-8908557c21ec', false, false, 'd2b6185a-dd2e-4dda-bbc5-2cc6341c0644'),  ('dca3e900-6735-4fb9-9c03-d6bc7063d0e9', 2, 'Ha hecho caracterización', '','26034723-97cd-44ee-a98f-8908557c21ec', false, false, 'd2b6185a-dd2e-4dda-bbc5-2cc6341c0644'), ('a35f7c87-c2ba-4cb7-b8b9-6fc1ac378400', 3, 'Tiene un plan de acción', '','26034723-97cd-44ee-a98f-8908557c21ec', false, false, 'd2b6185a-dd2e-4dda-bbc5-2cc6341c0644' ), ('4d0dda9c-af85-4870-917a-1c8071a3e68b', 4, 'Evalúa resultados del plan de acción', '','26034723-97cd-44ee-a98f-8908557c21ec', false, false, 'd2b6185a-dd2e-4dda-bbc5-2cc6341c0644' ), ('e5e594bb-8485-43e5-9610-aa853ad25f35', 5, 'Tiene un plan de mejora continua', '','26034723-97cd-44ee-a98f-8908557c21ec', false, false, 'd2b6185a-dd2e-4dda-bbc5-2cc6341c0644');


-- Question 2
insert into questions (id, question_order, question_text,mandatory, justify, type, hint, activity_id ) values ('d2b6185a-dd2e-4dda-bbc5-2cc6341c0644', 1, '¿Seleccione los ítems relacionados con dichas tecnologías?', false, true, 'MULTIPLE_CHOICE', 'Seleccione todos los ítems que apliquen a la situación actual de la empresa', '3b270b5c-15a3-4f2b-a33b-3c25d5b15e7e');

insert into question_options (id, option_order, option_value, hint,question_id, exclusive, not_apply, dependent_question_id) values ('d01f75f7-3a39-4273-a61c-413c268a96ed', 0, 'Reciclaje/ Recirculación', 'El reciclaje es una alternativa para la gestión de un producto al final de su uso. El reciclaje es el proceso de recuperación del valor material y, a menudo, se lleva a cabo cuando la recuperación del valor funcional (por ejemplo, reutilización, restauración o remanufactura) no es técnica o económicamente viable. https://link.springer.com/search?facet-eisbn=978-3-642-20617-7&facet-content-type=ReferenceWorkEntry&query=ortegon','d2b6185a-dd2e-4dda-bbc5-2cc6341c0644', false, false, null),('236b4c3a-501f-45e0-84b0-c9126d6bc677', 1, 'Remanufactura', 'La remanufactura es una alternativa de fin de uso del producto. La remanufactura es el proceso de devolver un producto usado (no funcional, descartado o intercambiado) al menos a la especificación de rendimiento del producto original. La remanufactura defiere de la reparación, en que esta última solo permite retornar el producto a un estado funcional. https://link.springer.com/referenceworkentry/10.1007/978-3-642-20617-7_6612','d2b6185a-dd2e-4dda-bbc5-2cc6341c0644', false, false, null ), ('556d336a-5c1e-4ddb-9759-cd6b4c0fefbf', 2, 'Minimización de residuosa', '','d2b6185a-dd2e-4dda-bbc5-2cc6341c0644', false, false,  null ), ('29b00949-7974-4299-ab65-a426c20620a5', 3, 'Tratamiento de aguas', '','d2b6185a-dd2e-4dda-bbc5-2cc6341c0644', false, false, null ), ('6d43fafe-1a5d-45a3-a934-ab77fabf15eb', 4, 'Tratamiento o recuperación de suelos', '','d2b6185a-dd2e-4dda-bbc5-2cc6341c0644', false, false, null ), ('4f42aef0-e535-4deb-a274-ae5a7083fd43', 5, 'Minimizacion o captura de gases de efecto invernadero (GEI)', '','d2b6185a-dd2e-4dda-bbc5-2cc6341c0644', false, false, null );

-- ************ ACTIVITY 2 ************

-- Question 1
insert into questions (id, question_order, question_text,mandatory, justify, type, hint, activity_id ) values ('da5bfd7b-41cc-41c7-bcbf-d0ce5a18d018', 0, '¿En qué nivel se ubica la empresa con respecto a la gestión para mantener el equilibrio de los ecosistemas?', true, true, 'INCREMENTAL_SINGLE_CHOICE', '', 'cef26030-08d7-4ce2-a608-afa33ae27d86');

insert into question_options (id, option_order, option_value, hint,question_id, exclusive, not_apply, dependent_question_id) values ('c21460d6-b2a5-4de4-953c-a753ba5cb6c0', 0, 'Ninguno', '','da5bfd7b-41cc-41c7-bcbf-d0ce5a18d018',true, false,  null),  ('658fa448-ae6f-495c-916f-f938ff4f0590', 1, 'Ha identificado oportunidades', '','da5bfd7b-41cc-41c7-bcbf-d0ce5a18d018',false, false, null),  ('6265bca4-f300-4826-9df5-d09969467d48', 2, 'Está en estudio de factibilidad', '','da5bfd7b-41cc-41c7-bcbf-d0ce5a18d018',false, false, null),  ('7677a871-7571-4028-821d-150aef8974e3', 3, 'Tiene un plan de acción', '','da5bfd7b-41cc-41c7-bcbf-d0ce5a18d018',false, false, null),  ('ed9e478c-9583-4a1d-b778-120f7be36e9c', 4, 'Evalúa resultados del plan de acción', '','da5bfd7b-41cc-41c7-bcbf-d0ce5a18d018',false, false, null),  ('46507e74-00ea-442b-b82f-50dc698385ad', 5, 'Tiene un plan de mejora continua', '','da5bfd7b-41cc-41c7-bcbf-d0ce5a18d018',false, false, null);

-- Question 2
insert into questions  (id, question_order, question_text,mandatory, justify, type, hint, activity_id ) values ('e8ad910d-8d83-4ad1-9c3b-e73639f02ce2', 1, 'Selecciona las actividades que realiza tu empresa para mantener el equilibrio ecosistémico', true, true, 'MULTIPLE_CHOICE', '', 'cef26030-08d7-4ce2-a608-afa33ae27d86');

insert into question_options (id, option_order, option_value, hint,question_id, exclusive, not_apply, dependent_question_id) values ('f23bdfdd-59bb-4af1-a7dd-5a1aa331b797', 0, 'Ninguna', '','e8ad910d-8d83-4ad1-9c3b-e73639f02ce2',true, false, null),  ('5ac4c1e9-42de-4761-90c9-2d911be497bd', 1, 'Recirculación de agua', '','e8ad910d-8d83-4ad1-9c3b-e73639f02ce2',false, false, null) ,  ('560356d0-4edf-464f-85b1-2cb7b4bd80e3', 2, 'Reforestación', 'Repoblación forestal, natural o artificial, de una zona anteriormente cubierta de bosques. Fuente: Barreiro, A. (2001). Diccionario de términos ambientales. Antecedentes, propuesta terminográfica y estudio terminológico. Revista de Lexicografía, 7. https://doi.org/10.17979/rlex.2001.7.0.5601','e8ad910d-8d83-4ad1-9c3b-e73639f02ce2',false, false, null),  ('e6209a87-c2f6-4634-951b-67ee45ec0c2a', 3, 'Preservación de un área o ecosistema', '','e8ad910d-8d83-4ad1-9c3b-e73639f02ce2',false, false, null),  ('90ab5a6f-92a2-4384-9c41-96276373f386', 4, 'Recuperación de suelos', 'Uso de métodos que devuelven las condiciones ideales al suelo para funcionar como hábitat. Entre estos encontramos el uso de encalado, estiércol, gallinaza compostada, entre otros. – Basado en https://www.traxco.es/blog/labores-del-campo/recuperacion-suelos-agricolas','e8ad910d-8d83-4ad1-9c3b-e73639f02ce2',false, false, null),   ('53284ed5-a41b-4d61-91e1-aea7409f0e1f', 5, 'Conservación de la biodiversidad', '','e8ad910d-8d83-4ad1-9c3b-e73639f02ce2',false, false, null);

-- ************ ACTIVITY 3 ************

-- Question 1
insert into questions (id, question_order, question_text,mandatory, justify, type, hint, activity_id ) values ('aa308df9-ca77-404a-a3a5-a73264562f88', 0, '¿En qué nivel se ubica la empresa con respecto a la gestión para devolver los recursos biológicos recuperados al agua?', true, true, 'INCREMENTAL_SINGLE_CHOICE', '', 'f610c75f-5547-44cc-a92b-cff80eded136');

insert into question_options (id, option_order, option_value, hint,question_id, exclusive, not_apply, dependent_question_id) values ('fc642430-fd2c-442a-8b7c-d5558e345ffd', 0, 'Ninguno', '','aa308df9-ca77-404a-a3a5-a73264562f88',true, false,  null),  ('b7fa6c6a-c465-4acb-9728-4ecd1f6278f0', 1, 'Ha identificado oportunidades', '','aa308df9-ca77-404a-a3a5-a73264562f88',false, false, null),  ('ff83a863-6b0e-4ffb-b3c3-d1289049354b', 2, 'Está en estudio', '','aa308df9-ca77-404a-a3a5-a73264562f88',false, false, null),  ('366c6073-1e5c-473d-a68d-5f9e785c3fff', 3, 'Tiene un plan de acción', '','aa308df9-ca77-404a-a3a5-a73264562f88',false, false, null),  ('e30721b8-276f-4e52-b01e-e019c7c6c957', 4, 'Evalúa resultados del plan de acción', '','aa308df9-ca77-404a-a3a5-a73264562f88',false, false, null),  ('296e654e-b7cc-4fd3-9b75-3a5d6b3771f1', 5, 'Tiene un plan de mejora continua', '','aa308df9-ca77-404a-a3a5-a73264562f88',false, false, null);

-- Question 2
insert into questions (id, question_order, question_text,mandatory, justify, type, hint, activity_id ) values ('7cde492b-2e64-4a55-ac21-3cac48730fb2', 1, '¿En qué nivel se ubica la empresa con respecto a la gestión para devolver los recursos biológicos recuperados al suelo?', true, true, 'INCREMENTAL_SINGLE_CHOICE', '', 'f610c75f-5547-44cc-a92b-cff80eded136');

insert into question_options (id, option_order, option_value, hint,question_id, exclusive, not_apply, dependent_question_id) values ('b23ef320-01b1-4b25-9380-451aa3f53ca4', 0, 'Ninguno', '','7cde492b-2e64-4a55-ac21-3cac48730fb2',true, false,  null),  ('ba500fcb-5ba1-47e1-9470-f39dff735c16', 1, 'Ha identificado oportunidades', '','7cde492b-2e64-4a55-ac21-3cac48730fb2',false, false, null),  ('4b573e18-eecf-4387-9832-7226a83ce976', 2, 'Está en estudio', '','7cde492b-2e64-4a55-ac21-3cac48730fb2',false, false, null),  ('f3d07f7e-c92f-43c4-99d4-4ad5a59b26c4', 3, 'Tiene un plan de acción', '','7cde492b-2e64-4a55-ac21-3cac48730fb2',false, false, null),  ('8f6b3180-038e-4028-ae12-5f93252213b7', 4, 'Evalúa resultados del plan de acción', '','7cde492b-2e64-4a55-ac21-3cac48730fb2',false, false, null),  ('ebfe3d59-989f-45ec-9bf2-a512aed30e98', 5, 'Tiene un plan de mejora continua', '','7cde492b-2e64-4a55-ac21-3cac48730fb2',false, false, null);

-- Question 3
insert into questions (id, question_order, question_text,mandatory, justify, type, hint, activity_id ) values ('6073e008-74f3-4e93-b3c5-6847e85b9d19', 2, '¿En qué nivel se ubica la empresa con respecto a la gestión para devolver los recursos biológicos recuperados a la atmósfera?', true, true, 'INCREMENTAL_SINGLE_CHOICE', '', 'f610c75f-5547-44cc-a92b-cff80eded136');

insert into question_options (id, option_order, option_value, hint,question_id, exclusive, not_apply, dependent_question_id) values ('05a5ea06-ff68-4a30-8c6f-90cad834c290', 0, 'Ninguno', '','6073e008-74f3-4e93-b3c5-6847e85b9d19',true, false,  null),  ('0ca9bba7-ee11-4b2d-a08f-f2af31b29c8e', 1, 'Ha identificado oportunidades', '','6073e008-74f3-4e93-b3c5-6847e85b9d19',false, false, null),  ('836dad17-7c57-4356-adf6-3fede3faee86', 2, 'Está en estudio', '','6073e008-74f3-4e93-b3c5-6847e85b9d19',false, false, null),  ('31503d22-3e05-4e26-9029-255067c62bcf', 3, 'Tiene un plan de acción', '','6073e008-74f3-4e93-b3c5-6847e85b9d19',false, false, null),  ('32849cbf-0f83-4ac5-b28a-4d9f97d01d7b', 4, 'Evalúa resultados del plan de acción', '','6073e008-74f3-4e93-b3c5-6847e85b9d19',false, false, null),  ('b04f0431-f4b3-4c0e-840f-030a9163a3f5', 5, 'Tiene un plan de mejora continua', '','6073e008-74f3-4e93-b3c5-6847e85b9d19',false, false, null);

-- Question 4
insert into questions (id, question_order, question_text,mandatory, justify, type, hint, activity_id ) values ('2e84248b-0367-41ec-88c2-6e4018ddad55', 3, 'Selecciona las actividades que  realiza tu empresa para devolver los recursos biológicos recuperados a la biósfera.', true, true, 'MULTIPLE_CHOICE', '', 'f610c75f-5547-44cc-a92b-cff80eded136');

insert into question_options (id, option_order, option_value, hint,question_id, exclusive, not_apply, dependent_question_id) values ('9434fa69-32b2-4ec5-97ec-0707720e48f2', 0, 'Ninguna', '','2e84248b-0367-41ec-88c2-6e4018ddad55',true, false,  null),  ('c7a39821-3edd-4134-b088-63455d5934f7', 1, 'Compostaje o lombricompostaje', 'El compostaje es un proceso biológico, que ocurre en condiciones aeróbicas (presencia de oxígeno). Con la adecuada humedad y temperatura, se asegura una transformación higiénica de los restos orgánicos en un material homogéneo y asimilable por las plantas- Manual de compostaje del agricultor http://www.fao.org/3/i3388s/i3388s.pdf','2e84248b-0367-41ec-88c2-6e4018ddad55',false, false, null),  ('56624862-5abf-4e79-af1b-72e735374719', 2, 'Retorno de aguas residuales mejoradas', '','2e84248b-0367-41ec-88c2-6e4018ddad55',false, false, null),  ('6f0e9979-50ae-4e31-a354-c561d95f07ff', 3, 'Digestión anaerobia (Biogas)', 'Digestión anaerobia: proceso en el cual microorganismos descomponen materiales en ausencia de oxigeno que da como resultado una mezcla de gases potencialmente aprovechable como fuente de calor. https://www.redalyc.org/pdf/2231/223120659006.pdf','2e84248b-0367-41ec-88c2-6e4018ddad55',false, false, null),  ('b5bd18b7-2b6e-4d59-9f62-f55a2bd3d7e2', 4, 'Otra', '','2e84248b-0367-41ec-88c2-6e4018ddad55',false, false, null);
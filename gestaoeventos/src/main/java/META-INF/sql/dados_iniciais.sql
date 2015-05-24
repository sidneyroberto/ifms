-- Registros iniciais da tabela curso
insert into curso values(1, 'Tecnologia em Sistemas para Internet', 1);
insert into curso values(2, 'Técnico Integrado em Informática', 0);
insert into curso values(3, 'Técnico Integrado em Edificações', 0);
insert into curso values(4, 'Técnico em Manutenção e Suporte em Informática', 2);
insert into curso values(5, 'Técnico em Edificações', 2);
insert into curso values(6, 'Técnico em Agente Comunitário de Saúde', 2);
insert into curso values(7, 'Técnico em Transações Imobiliárias', 2);
insert into curso values(8, 'Técnico em Inglês', 2);
insert into curso values(9, 'Técnico em Espanhol', 2);

-- Registros iniciais da tabela turma
insert into turma(id, id_curso, periodo, semestre_inicio) values(1, 1, 2, '2013/1');
insert into turma(id, id_curso, periodo, semestre_inicio) values(2, 1, 2, '2014/1');
insert into turma(id, id_curso, periodo, semestre_inicio) values(3, 1, 2, '2014/2');
insert into turma(id, id_curso, periodo, semestre_inicio) values(4, 1, 2, '2015/1');
insert into turma(id, id_curso, periodo, semestre_inicio) values(5, 2, 0, '2012/1');
insert into turma(id, id_curso, periodo, semestre_inicio) values(6, 2, 0, '2014/1');
insert into turma(id, id_curso, periodo, semestre_inicio) values(7, 2, 0, '2015/1');
insert into turma(id, id_curso, periodo, semestre_inicio) values(8, 2, 1, '2013/1');
insert into turma(id, id_curso, periodo, semestre_inicio) values(9, 2, 1, '2015/1');
insert into turma(id, id_curso, periodo, semestre_inicio) values(10, 3, 0, '2013/1');
insert into turma(id, id_curso, periodo, semestre_inicio) values(11, 3, 0, '2014/1');
insert into turma(id, id_curso, periodo, semestre_inicio) values(12, 3, 0, '2015/1');


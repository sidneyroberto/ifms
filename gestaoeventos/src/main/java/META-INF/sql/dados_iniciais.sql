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

-- Registros iniciais da tabela estudante
insert into estudante(id, cpf, nome, ra, datanascimento, sexo, id_turma) values(1, '05141857670', 'SIDNEY ROBERTO DE SOUSA', '100067', '1985-08-29', 0, 1);
insert into estudante(id, cpf, nome, ra, datanascimento, sexo, id_turma) values(2, '73438681013', 'ÃNGELA MARIA ANDRADE','100165', '1993-02-19', 1, 1);
insert into estudante(id, cpf, nome, ra, datanascimento, sexo, id_turma) values(3, '99542244169', 'BRUNO LIMEIRA SILVA','111234', '2000-12-11', 0, 12);
insert into estudante(id, cpf, nome, ra, datanascimento, sexo, id_turma) values(4, '19533228407', 'SÂMIA SOULEIMAN','115432', '2000-07-21', 1, 9);
insert into estudante(id, cpf, nome, ra, datanascimento, sexo, id_turma) values(5, '23441323920', 'THIAGO ALVAREZ SANCHEZ','123432', '1999-03-12', 0, 11);
insert into estudante(id, cpf, nome, ra, datanascimento, sexo, id_turma) values(6, '36017343548', 'MANUELA BRITO NUNES','101980', '1994-03-09', 1, 2);

-- Registros iniciais da tabela evento
insert into evento(id, titulo, descricao, datarealizacao, horarioinicio, horariofim) values(1, 'Flisol 2015', 'Evento de software livre', '2015-04-25', '08:00:00', '16:00:00');
insert into evento(id, titulo, descricao, datarealizacao, horarioinicio, horariofim) values(2, 'Semana do Meio Ambiente 2015', 'Semana nacional do meio ambiente, com atividades diversas', '2015-05-25', null, null);
insert into evento(id, titulo, descricao, datarealizacao, horarioinicio, horariofim) values(3, '2.º Dia da Cultura Nerd', 'Evento com atividades diversas sobre o universo nerd', '2015-11-22', '07:00:00', '16:00:00');


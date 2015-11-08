CREATE TABLE pessoa (
	id integer primary key auto_increment,
    nome varchar(50) NOT NULL,
    sobrenome varchar(50) NOT NULL
);

CREATE TABLE aluno (
	id_pessoa integer primary key auto_increment,
    prontuario integer NOT NULL,
    senha varchar(255) NOT NULL,
	foreign key (id_pessoa) references pessoa(id) on delete cascade on update cascade
);

CREATE TABLE professor (
	id_pessoa integer primary key,
	chapa integer NOT NULL,
    foreign key (id_pessoa) references pessoa(id) on delete cascade on update cascade
);

CREATE TABLE turma (
	id integer primary key auto_increment,
    descricao varchar(50) NOT NULL
);

CREATE TABLE disciplina (
	id integer primary key auto_increment,
    descricao varchar(50) NOT NULL
);

CREATE TABLE projeto (
	id integer primary key auto_increment,
    descricao varchar (100) NOT NULL
);

CREATE TABLE questoes (
	id integer primary key auto_increment,
    descricao varchar (255) NOT NULL
);

CREATE TABLE respostas (
	id integer primary key auto_increment,
    resposta integer NOT NULL,
    id_questoes integer,
    data_resposta datetime NOT NULL,
    foreign key (id_questoes) references questoes (id) on delete cascade on update cascade
);

CREATE TABLE questoes_projeto(
	id_questoes integer,
    id_projeto integer,
	primary key (id_questoes, id_projeto),
	foreign key (id_questoes) references questoes(id) on delete cascade on update cascade,
	foreign key (id_projeto) references projeto(id) on delete cascade on update cascade
);

CREATE TABLE periodo_letivo (
	ano integer,
    semestre integer,
	id_turma integer,
    id_disciplina integer,
    primary key (ano, semestre, id_turma, id_disciplina),
    foreign key (id_turma) references turma(id) on delete cascade on update cascade,
    foreign key (id_disciplina) references disciplina(id) on delete cascade on update cascade
);

CREATE TABLE aula (
	id integer primary key auto_increment,
    id_professor integer,
    id_periodo_letivo integer,
    data_aula date NOT NULL,
    foreign key (id_periodo_letivo) references periodo_letivo (ano) on delete cascade on update cascade,
    foreign key (id_professor) references professor(id_pessoa) on delete cascade on update cascade
);

CREATE TABLE alunos_aula (
	id_aluno integer, 
    id_aula integer,
    primary key (id_aluno, id_aula),
    foreign key (id_aluno) references aluno(id_pessoa),
    foreign key (id_aula) references aula (id)
);

CREATE TABLE avaliacao(
	id integer auto_increment,
    id_projeto integer,
    id_aula integer,
    data_avaliacao date NOT NULL,
	primary key (id, id_projeto, id_aula),
	foreign key (id_projeto) references projeto(id) on delete cascade on update cascade,
	foreign key (id_aula) references aula(id) on delete cascade on update cascade
);

CREATE TABLE alunos_avaliacao (
	id_aluno integer,
    id_avaliacao integer,
    primary key (id_aluno, id_avaliacao),
    foreign key (id_aluno) references aluno (id_pessoa) on delete cascade on update cascade,
    foreign key (id_avaliacao) references avaliacao (id) on delete cascade on update cascade
);

CREATE TABLE respostas_avaliacao (
	id_avaliacao integer,
    id_respostas integer, 
    primary key (id_avaliacao, id_respostas),
    foreign key (id_avaliacao) references avaliacao(id) on delete cascade on update cascade,
    foreign key (id_respostas) references respostas(id) on delete cascade on update cascade
);
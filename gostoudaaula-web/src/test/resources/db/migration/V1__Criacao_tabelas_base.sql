DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(50) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    token VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS aluno;
CREATE TABLE aluno (
    id_aluno INTEGER PRIMARY KEY,
    FOREIGN KEY (id_aluno) REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE cascade
);

DROP TABLE IF EXISTS professor;
CREATE TABLE professor (
    id_professor INTEGER PRIMARY KEY,
    FOREIGN KEY (id_professor) REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE cascade
);

DROP TABLE IF EXISTS turma;
CREATE TABLE turma (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS disciplina;
CREATE TABLE disciplina (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS projeto;
CREATE TABLE projeto (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR (100) NOT NULL
);

DROP TABLE IF EXISTS questoes;
CREATE TABLE questoes (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR (255) NOT NULL
);

DROP TABLE IF EXISTS questoes_projeto;
CREATE TABLE questoes_projeto(
    id_questoes INTEGER,
    id_projeto INTEGER,
    PRIMARY KEY (id_questoes, id_projeto),
    FOREIGN KEY (id_questoes) REFERENCES questoes(id) ON DELETE CASCADE ON UPDATE cascade,
    FOREIGN KEY (id_projeto) REFERENCES projeto(id) ON DELETE CASCADE ON UPDATE cascade
);

DROP TABLE IF EXISTS periodo_letivo;
CREATE TABLE periodo_letivo (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    ano INTEGER,
    semestre INTEGER,
    id_turma INTEGER,
    id_disciplina INTEGER,
    UNIQUE KEY indentificacao_do_periodo (ano, semestre, id_turma, id_disciplina),
    FOREIGN KEY (id_turma) REFERENCES turma(id) ON DELETE CASCADE ON UPDATE cascade,
    FOREIGN KEY (id_disciplina) REFERENCES disciplina(id) ON DELETE CASCADE ON UPDATE cascade
);

DROP TABLE IF EXISTS aula;
CREATE TABLE aula (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_professor INTEGER,
    id_periodo_letivo INTEGER,
    data_aula date NOT NULL,
    FOREIGN KEY (id_periodo_letivo) REFERENCES periodo_letivo (id) ON DELETE CASCADE ON UPDATE cascade,
    FOREIGN KEY (id_professor) REFERENCES professor(id_professor) ON DELETE CASCADE ON UPDATE cascade
);

DROP TABLE IF EXISTS alunos_aula;
CREATE TABLE alunos_aula (
    id_aluno INTEGER, 
    id_aula INTEGER,
    PRIMARY KEY (id_aluno, id_aula),
    FOREIGN KEY (id_aluno) REFERENCES aluno(id_aluno),
    FOREIGN KEY (id_aula) REFERENCES aula (id)
);

DROP TABLE IF EXISTS alunos_avaliacao;
CREATE TABLE avaliacao(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    id_projeto INTEGER,
    id_aula INTEGER,
    data_avaliacao date NOT NULL,
    UNIQUE KEY id_da_avaliacao (id_projeto, id_aula),
    FOREIGN KEY (id_projeto) REFERENCES projeto(id) ON DELETE CASCADE ON UPDATE cascade,
    FOREIGN KEY (id_aula) REFERENCES aula(id) ON DELETE CASCADE ON UPDATE cascade
);

DROP TABLE IF EXISTS respostas;
CREATE TABLE respostas (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    resposta INTEGER NOT NULL,
    id_questoes INTEGER,
    id_avaliacao INTEGER, 
    data_resposta datetime NOT NULL,
    FOREIGN KEY (id_questoes) REFERENCES questoes (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_avaliacao) REFERENCES avaliacao (id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS alunos_avaliacao;
CREATE TABLE alunos_avaliacao (
    id_aluno INTEGER,
    id_avaliacao INTEGER,
    PRIMARY KEY (id_aluno, id_avaliacao),
    FOREIGN KEY (id_aluno) REFERENCES aluno (id_aluno) ON DELETE CASCADE ON UPDATE cascade,
    FOREIGN KEY (id_avaliacao) REFERENCES avaliacao (id) ON DELETE CASCADE ON UPDATE cascade
);
-- Tabela Aluno
CREATE TABLE Aluno (
    matricula BIGINT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf BIGINT NOT NULL UNIQUE,
    endereco VARCHAR(255)
);

-- Tabela Area
CREATE TABLE Area (
    nome VARCHAR(255) PRIMARY KEY,
    descricao TEXT
);

-- Tabela Autor
CREATE TABLE Autor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    sobrenome VARCHAR(255) NOT NULL,
    titulacao VARCHAR(255),
    UNIQUE (nome, sobrenome)
);

-- Tabela Titulo
CREATE TABLE Titulo (
    isbn VARCHAR(13) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    prazo INT,
    edicao INT,
    ano INT,
    editora VARCHAR(255),
    paginas INT,
    area_nome VARCHAR(255) REFERENCES Area(nome)
);

-- Tabela Autor_Titulo (associação muitos-para-muitos entre Autor e Titulo)
CREATE TABLE Autor_Titulo (
    autor_id INT REFERENCES Autor(id),
    titulo_isbn VARCHAR(13) REFERENCES Titulo(isbn),
    PRIMARY KEY (autor_id, titulo_isbn)
);

-- Tabela Livro
CREATE TABLE Livro (
    id SERIAL PRIMARY KEY,
    disponivel BOOLEAN NOT NULL,
    exemplar_biblioteca BOOLEAN NOT NULL,
    titulo_isbn VARCHAR(13) REFERENCES Titulo(isbn)
);

-- Tabela Debito
CREATE TABLE Debito (
    id SERIAL PRIMARY KEY,
    matricula BIGINT REFERENCES Aluno(matricula),
    data DATE NOT NULL,
    valor FLOAT NOT NULL
);

-- Tabela Emprestimo
CREATE TABLE Emprestimo (
    id SERIAL PRIMARY KEY,
    matricula BIGINT REFERENCES Aluno(matricula),
    data_emprestimo DATE NOT NULL,
    data_prevista DATE NOT NULL
);

-- Tabela Item_Emprestimo (associação muitos-para-muitos entre Emprestimo e Livro)
CREATE TABLE Item_Emprestimo (
    id SERIAL PRIMARY KEY,
    emprestimo_id INT REFERENCES Emprestimo(id),
    livro_id INT REFERENCES Livro(id),
    data_devolucao DATE,
    data_prevista DATE
);

-- Devolucao
CREATE TABLE Devolucao (
    id SERIAL PRIMARY KEY,
    emprestimo_id INT REFERENCES Emprestimo(id),
    valor_multa FLOAT NOT NULL
);

-- Item_Devolucao
CREATE TABLE Item_Devolucao (
    id SERIAL PRIMARY KEY,
    devolucao_id INT REFERENCES Devolucao(id),
    livro_id INT REFERENCES Livro(id),
    data_devolucao DATE,
    dias_atraso INT
);

-- ------------------------------------------------------------------

-- Inserir Áreas
INSERT INTO Area (nome, descricao)
VALUES
    ('Ficção', 'Livros do gênero ficção e fantasia'),
    ('Ciências', 'Livros relacionados a temas científicos'),
    ('Arte', 'Livros que abordam temas artísticos e culturais');

-- Inserir Títulos
INSERT INTO Titulo (isbn, nome, prazo, edicao, ano, editora, paginas, area_nome)
VALUES
    ('9781234567897', 'Ficção Fantástica', 15, 1, 2020, 'Editora Aventura', 350, 'Ficção'),
    ('9789876543210', 'Introdução à Física', 30, 3, 2018, 'Editora Ciências', 500, 'Ciências'),
    ('9781928374655', 'História da Arte', 30, 2, 2015, 'Editora Cultural', 400, 'Arte');

-- Inserir Livros
-- Primeiro título (Ficção Fantástica)
INSERT INTO Livro (disponivel, exemplar_biblioteca, titulo_isbn)
VALUES
    (TRUE, FALSE, '9781234567897'), -- Livro 1 disponível
    (TRUE, FALSE, '9781234567897'), -- Livro 2 disponível
    (FALSE, FALSE, '9781234567897'); -- Livro 3 já emprestado

-- Segundo título (Introdução à Física)
INSERT INTO Livro (disponivel, exemplar_biblioteca, titulo_isbn)
VALUES
    (TRUE, FALSE, '9789876543210'), -- Livro 1 disponível
    (TRUE, FALSE, '9789876543210'); -- Livro 2 disponível

-- Terceiro título (História da Arte)
INSERT INTO Livro (disponivel, exemplar_biblioteca, titulo_isbn)
VALUES
    (FALSE, TRUE, '9781928374655'); -- Exemplar da biblioteca

-- Inserir Alunos
INSERT INTO Aluno (matricula, nome, cpf, endereco)
VALUES
    (1, 'Mateus Komarchesqui', 123, 'Rua dos Estudantes, 123'),
    (2, 'Ana Clara Silva', 456, 'Rua das Flores, 456'),
    (3, 'João Pedro Santos', 789, 'Rua dos Poetas, 789');

-- Inserir Empréstimo para Mateus
INSERT INTO Emprestimo (matricula, data_emprestimo, data_prevista)
VALUES
    ((SELECT matricula FROM Aluno WHERE nome = 'Mateus Komarchesqui'), '2024-12-01', '2024-12-15');

-- Associar o Livro 3 ao empréstimo de Mateus
INSERT INTO Item_Emprestimo (emprestimo_id, livro_id, data_devolucao, data_prevista)
VALUES
    ((SELECT id FROM Emprestimo WHERE matricula = (SELECT matricula FROM Aluno WHERE nome = 'Mateus Komarchesqui')),
     (SELECT id FROM Livro WHERE disponivel = FALSE AND exemplar_biblioteca = FALSE AND titulo_isbn = '9781234567897' LIMIT 1),
     NULL, '2024-12-15');


------------------------------------------------------------------

-- Truncar todas as tabelas e resetar os seriais
TRUNCATE TABLE 
    Item_Devolucao, 
    Devolucao, 
    Item_Emprestimo, 
    Emprestimo, 
    Debito, 
    Livro, 
    Autor_Titulo, 
    Autor, 
    Titulo, 
    Area, 
    Aluno
RESTART IDENTITY CASCADE;
CREATE EXTENSION IF NOT EXISTS "pgcrypto";


CREATE TABLE usuario (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255) NOT NULL CHECK (char_length(nome) > 10),
    email VARCHAR(255) NOT NULL UNIQUE CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+[A-Za-z]{2,}$'),
    senha VARCHAR(255) NOT NULL CHECK (char_length(senha) >= 8),
    integridade BOOLEAN NOT NULL,
    data_nascimento DATE NOT NULL CHECK (data_nascimento <= CURRENT_DATE - INTERVAL '18 years'),
    user_role VARCHAR(100) NOT NULL CHECK (user_role IN ('ADMIN', 'LOCADOR', 'LOCATARIO'))
);


CREATE TABLE contato (
    id_contato UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    telefone VARCHAR(255) NOT NULL CHECK (telefone ~ '^[0-9]{10,15}$'),
    id_usuario UUID,
    CONSTRAINT fk_contato_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);






CREATE TABLE salas (
    salas_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    localidade_id UUID,
    descricao TEXT NOT NULL,
    tamanho VARCHAR(255) NOT NULL,
    preco_por_hora DECIMAL(10, 2),
    tempo_inicio_disponivel TIME,
    tempo_fim_disponivel TIME,
    CONSTRAINT fk_localidade FOREIGN KEY (localidade_id) REFERENCES localidades(localidade_id) ON DELETE CASCADE
);


CREATE TABLE sala_imagens (
    ID UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    sala_id UUID,
    imagem VARCHAR(255) NOT NULL,
    CONSTRAINT fk_sala FOREIGN KEY (sala_id) REFERENCES salas(salas_id) ON DELETE CASCADE
);


CREATE TABLE sala_servicos (
    ID UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    sala_id UUID,
    tipo_servico VARCHAR(255) NOT NULL,
    CONSTRAINT fk_sala FOREIGN KEY (sala_id) REFERENCES salas(salas_id) ON DELETE CASCADE
);


CREATE TABLE reservas (
    ID UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    localidade_id UUID,
    locatario_id UUID,
    locador_id UUID,
    tempo_inicio TIME,
    tempo_fim TIME,
    data_reserva DATE,
    status VARCHAR(20) CHECK (status IN ('confirmada', 'pendente', 'cancelada')),
    CONSTRAINT fk_localidade FOREIGN KEY (localidade_id) REFERENCES localidades(localidade_id) ON DELETE CASCADE,
    CONSTRAINT fk_locatario FOREIGN KEY (locatario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    CONSTRAINT fk_locador FOREIGN KEY (locador_id) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE avaliacao (
    ID UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    sala_id UUID,
    quant_Estrelas INT CHECK (quant_Estrelas >= 1 AND quant_Estrelas <= 5),
    CONSTRAINT fk_sala FOREIGN KEY (sala_id) REFERENCES salas(salas_id) ON DELETE CASCADE
);

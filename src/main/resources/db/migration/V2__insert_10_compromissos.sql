INSERT INTO tb_compromisso
    (titulo, descricao, data, hora, cep, cidade, estado, alerta_dias_antes, criado_em, atualizado_em)
VALUES
    ('Consulta médica', 'Consulta com o Dr. Silva', '2025-10-01', '09:00:00', '12345-678', 'São Paulo', 'SP', 2, NOW(), NOW()),
    ('Reunião de trabalho', 'Discussão do projeto X', '2025-10-02', '14:30:00', '23456-789', 'Rio de Janeiro', 'RJ', 1, NOW(), NOW()),
    ('Aniversário da Ana', 'Comprar presente', '2025-10-05', '00:00:00', '34567-890', 'Belo Horizonte', 'MG', 3, NOW(), NOW()),
    ('Entrega do relatório', 'Relatório trimestral', '2025-10-07', '17:00:00', '45678-901', 'Curitiba', 'PR', 1, NOW(), NOW()),
    ('Dentista', 'Limpeza dental', '2025-10-10', '10:00:00', '56789-012', 'Porto Alegre', 'RS', 2, NOW(), NOW()),
    ('Treinamento', 'Curso de capacitação', '2025-10-12', '08:00:00', '67890-123', 'Fortaleza', 'CE', 5, NOW(), NOW()),
    ('Consulta veterinária', 'Vacinação do gato', '2025-10-15', '15:30:00', '78901-234', 'Salvador', 'BA', 1, NOW(), NOW()),
    ('Jantar com clientes', 'Restaurante italiano', '2025-10-18', '20:00:00', '89012-345', 'Brasília', 'DF', 2, NOW(), NOW()),
    ('Manutenção do carro', 'Troca de óleo', '2025-10-20', '09:30:00', '90123-456', 'Manaus', 'AM', 3, NOW(), NOW()),
    ('Encontro de amigos', 'Café no shopping', '2025-10-22', '16:00:00', '01234-567', 'Recife', 'PE', 4, NOW(), NOW());

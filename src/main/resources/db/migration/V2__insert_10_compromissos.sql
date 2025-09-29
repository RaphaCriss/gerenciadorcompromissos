INSERT INTO tb_compromissos (
    compromisso_id,
    titulo,
    descricao,
    data_hora,
    cep,
    cidade,
    estado,
    alerta_dias_antes,
    id_telegram,
    criado_em,
    atualizado_em,
    creation_timestamp
)
VALUES
(UUID(), 'Viagem de Férias', 'Viagem para visitar parentes em Florianópolis', '2025-12-20 06:00:00', '88000000', 'Florianópolis', 'SC', 10, 2001, NOW(), NOW(), NOW()),
(UUID(), 'Consulta médica', 'Consulta com clínico geral',                     '2025-10-05 09:00:00', '12345678', 'São Paulo',      'SP', 3, 2001, NOW(), NOW(), NOW()),
(UUID(), 'Piquenique em família', 'Encontro com os primos no parque central', '2025-10-12 15:00:00', '23456789', 'Campinas',       'SP', 1, 2001, NOW(), NOW(), NOW()),
(UUID(), 'Revisão oftalmológica', 'Check-up anual com oftalmologista',        '2025-10-20 08:30:00', '34567890', 'Curitiba',        'PR', 2, 2001, NOW(), NOW(), NOW()),
(UUID(), 'Aniversário da mãe', 'Almoço em comemoração ao aniversário da mãe', '2025-10-30 12:00:00', '45678901', 'Porto Alegre',    'RS', 5, 2001, NOW(), NOW(), NOW()),
(UUID(), 'Dentista', 'Limpeza e avaliação',                                   '2025-11-02 11:00:00', '56789012', 'Belo Horizonte',  'MG', 2, 2001, NOW(), NOW(), NOW()),
(UUID(), 'Caminhada na orla', 'Atividade física leve no fim de semana',       '2025-10-06 07:30:00', '67890123', 'Recife',          'PE', 0, 2001, NOW(), NOW(), NOW()),
(UUID(), 'Curso de culinária', 'Aula especial de massas italianas',           '2025-11-10 19:00:00', '78901234', 'Salvador',        'BA', 3, 2001, NOW(), NOW(), NOW()),
(UUID(), 'Viagem para evento cultural', 'Festival de música em outra cidade', '2025-12-01 18:00:00', '89012345', 'Fortaleza',       'CE', 7, 2001, NOW(), NOW(), NOW()),
(UUID(), 'Dia de lazer', 'Passeio no shopping com amigos',                    '2025-10-19 16:00:00', '90123456', 'Brasília',        'DF', 1, 2001, NOW(), NOW(), NOW());

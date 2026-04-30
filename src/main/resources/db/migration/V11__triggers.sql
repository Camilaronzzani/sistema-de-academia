-- TRIGGER 1: Validar data de matrícula

CREATE OR REPLACE FUNCTION fn_valida_data_matricula()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.data_matricula > CURRENT_DATE THEN
        RAISE EXCEPTION 'Erro: A data de matricula nao pode ser maior que hoje!';
END IF;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER tg_valida_data
    BEFORE INSERT OR UPDATE ON alunos
                         FOR EACH ROW
                         EXECUTE FUNCTION fn_valida_data_matricula();


-- TRIGGER 2: Impedir agendamento duplicado

CREATE OR REPLACE FUNCTION verificar_agendamento_duplicado()
RETURNS TRIGGER AS $$
BEGIN
    IF EXISTS (
        SELECT 1 FROM agendamentos
        WHERE id_disponibilidade = NEW.id_disponibilidade
        AND status IN ('AGENDADO', 'CONFIRMADO')
    ) THEN
        RAISE EXCEPTION 'Horario ja ocupado!';
END IF;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_verificar_agendamento
    BEFORE INSERT ON agendamentos
    FOR EACH ROW
    EXECUTE FUNCTION verificar_agendamento_duplicado();

-- TRIGGER 3: Validar check-in
CREATE OR REPLACE FUNCTION validar_checkin()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM agendamentos
        WHERE id_agendamento = NEW.id_agendamento
        AND status = 'AGENDADO'
    ) THEN
        RAISE EXCEPTION 'Check-in invalido! Agendamento nao permitido.';
END IF;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_validar_checkin
    BEFORE INSERT ON checkins
    FOR EACH ROW
    EXECUTE FUNCTION validar_checkin();


-- TRIGGER 4: Atualizar status após check-in
CREATE OR REPLACE FUNCTION atualizar_status_agendamento()
RETURNS TRIGGER AS $$
BEGIN
UPDATE agendamentos
SET status = 'CONFIRMADO'
WHERE id_agendamento = NEW.id_agendamento;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_checkin_confirma
    AFTER INSERT ON checkins
    FOR EACH ROW
    EXECUTE FUNCTION atualizar_status_agendamento();
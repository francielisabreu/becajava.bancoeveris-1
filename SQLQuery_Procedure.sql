create procedure SP_OperacoesPorConta
    @idConta BIGINT
as
begin
    select 
        id,
        tipo,
        valor,
        contaDestinoId,
        contaOrigemId
    from Operacao where contaDestinoId = @idConta
    union
    select 
        id,
        tipo,
        valor,
        contaDestinoId,
        contaOrigemId
    from Operacao where contaOrigemId = @idConta
end

EXEC SP_OperacoesPorConta 4
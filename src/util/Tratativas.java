if (cpf == null || cpf.isEmpty() || !cpf.matches("\\d{11}")) {
    throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos.");
}
if (crm == null || crm.isEmpty()) {
    throw new IllegalArgumentException("CRM do médico é obrigatório.");
}
if (codigoConsulta <= 0) {
    throw new IllegalArgumentException("Código da consulta deve ser maior que zero.");
}
if (email == null || email.isEmpty() || !email.contains("@")) {
    throw new IllegalArgumentException("Email inválido.");
}
if (senha == null || senha.isEmpty()) {
    throw new IllegalArgumentException("Senha é obrigatória.");
}
if (nome == null || nome.isEmpty()) {
    throw new IllegalArgumentException("Nome é obrigatório.");
}
if (idade <= 0) {
    throw new IllegalArgumentException("Idade deve ser maior que zero.");
}
try {
    LocalDate.parse(data);
} catch (DateTimeParseException e) {
    throw new IllegalArgumentException("Data inválida. Use o formato YYYY-MM-DD.");
}
if (telefoneContato == null || telefoneContato.isEmpty()) {
    throw new IllegalArgumentException("Telefone de contato é obrigatório.");
}
if (endereco == null || endereco.isEmpty()) {
    throw new IllegalArgumentException("Endereço é obrigatório.");
}
if (tipoPlanoSaude == null || tipoPlanoSaude.isEmpty()) {
    throw new IllegalArgumentException("Tipo de plano de saúde é obrigatório.");
}
if (tipoSanguineo == null || tipoSanguineo.isEmpty()) {
    throw new IllegalArgumentException("Tipo sanguíneo é obrigatório.");
}
if (alergias == null || alergias.isEmpty()) {
    throw new IllegalArgumentException("Alergias são obrigatórias.");
}
if (especialidade == null || especialidade.isEmpty()) {
    throw new IllegalArgumentException("Especialidade é obrigatória.");
}
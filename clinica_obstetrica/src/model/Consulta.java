    package model;

    public class Consulta {
        private Int codigoConsulta;
        private Date dataConsulta;
        private Date dataPrevista;
        private Date dataUltimaMenstruacao;
        private String tipoParto;
        private String qtdSemanas;

        public Consulta(Int codigoConsulta, Date dataConsulta, Date dataPrevista, Date dataUltimaMenstruacao, String tipoParto, String qtdSemanas) {
            super(nomeUsuario, senha, email);
            this.codigoConsulta = codigoConsulta;
            this.dataConsulta = dataConsulta;
            this.dataPrevista = dataPrevista;
            this.dataUltimaMenstruacao = dataUltimaMenstruacao;
            this.tipoParto = tipoParto;
            this.qtdSemanas = qtdSemanas;
        }
    }
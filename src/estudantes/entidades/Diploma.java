package src.estudantes.entidades;

import src.professor.entidades.CodigoCurso;

public class Diploma extends Certificado {
    private String habilitacao;

    public Diploma(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante,
            long matricula, String descricao, String habilitacao) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula, descricao);
        this.habilitacao = habilitacao;
    }

    public String getHabilitacao() {
        return this.habilitacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || o.getClass() != getClass())
            return false;

        Diploma aux = (Diploma) o;

        return this.getCriador().equals(aux.getCriador()) && this.getCodigoCurso() == aux.getCodigoCurso()
                && this.getPaginas() == aux.getPaginas() && this.getAutenticacao() == aux.getAutenticacao()
                && this.getEstudante().equals(aux.getEstudante()) && this.getMatricula() == aux.getMatricula()
                && this.getDescricao().equals(aux.getDescricao()) && this.habilitacao.equals(aux.getHabilitacao());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + (this.getCriador().equals(null) ? 0 : this.getCriador().hashCode());
        result = prime * result + (this.getCodigoCurso() == null ? 0 : this.getCodigoCurso().hashCode());
        result = prime * result + this.getPaginas();
        result = prime * result + (int) this.getAutenticacao();
        result = prime * result + (this.getEstudante().equals(null) ? 0 : this.getEstudante().hashCode());
        result = prime * result + (int) this.getMatricula();
        result = prime * result + (this.getDescricao().equals(null) ? 0 : this.getDescricao().hashCode());
        result = prime * result + (this.habilitacao.equals(null) ? 0 : this.habilitacao.hashCode());

        return result;
    }
}

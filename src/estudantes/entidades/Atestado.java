package src.estudantes.entidades;

import src.professor.entidades.CodigoCurso;

public class Atestado extends Registro {
    private String descricao;
    private String categoria;

    public Atestado(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante,
            long matricula, String descricao, String categoria) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getCategoria() {
        return this.categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || o.getClass() != getClass())
            return false;

        Atestado aux = (Atestado) o;

        return this.getCriador().equals(aux.getCriador()) && this.getCodigoCurso() == aux.getCodigoCurso()
                && this.getPaginas() == aux.getPaginas() && this.getAutenticacao() == aux.getAutenticacao()
                && this.getEstudante().equals(aux.getEstudante()) && this.getMatricula() == aux.getMatricula()
                && this.descricao.equals(aux.getDescricao()) && this.categoria.equals(aux.getCategoria());
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
        result = prime * result + (this.descricao.equals(null) ? 0 : this.descricao.hashCode());
        result = prime * result + (this.categoria.equals(null) ? 0 : this.categoria.hashCode());

        return result;
    }
}

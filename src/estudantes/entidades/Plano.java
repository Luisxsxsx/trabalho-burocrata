package src.estudantes.entidades;

import src.professor.entidades.CodigoCurso;

public class Plano extends DocumentoAcademico {
    private String responsavel;
    private String[] planejamento;

    public Plano(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String responsavel,
            String[] planejamento) {
        super(criador, codigoCurso, paginas, autenticacao);
        this.responsavel = responsavel;
        this.planejamento = planejamento;
    }

    public String getResponsavel() {
        return this.responsavel;
    }

    public String[] getPlanejamento() {
        return this.planejamento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || o.getClass() != getClass())
            return false;

        Plano aux = (Plano) o;

        return this.getCriador().equals(aux.getCriador()) && this.getCodigoCurso() == aux.getCodigoCurso()
                && this.getPaginas() == aux.getPaginas() && this.getAutenticacao() == aux.getAutenticacao()
                && this.responsavel.equals(aux.getResponsavel()) && this.planejamento.equals(aux.getPlanejamento());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (this.responsavel.equals(null) ? 0 : this.responsavel.hashCode());
        result = prime * result + (this.planejamento.equals(null) ? 0 : this.planejamento.hashCode());

        return result;
    }
}

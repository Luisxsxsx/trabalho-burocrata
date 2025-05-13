package src.estudantes.entidades;

import src.professor.entidades.CodigoCurso;

public abstract class Registro extends DocumentoAcademico {
    private String estudante;
    private long matricula;

    public Registro(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante,
            long matricula) {
        super(criador, codigoCurso, paginas, autenticacao);
        this.estudante = estudante;
        this.matricula = matricula;
    }

    public String getEstudante() {
        return this.estudante;
    }

    public long getMatricula() {
        return this.matricula;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || o.getClass() != getClass())
            return false;

        Registro aux = (Registro) o;

        return this.getCriador().equals(aux.getCriador()) && this.getCodigoCurso() == aux.getCodigoCurso()
                && this.getPaginas() == aux.getPaginas() && this.getAutenticacao() == aux.getAutenticacao()
                && this.estudante.equals(aux.getEstudante()) && this.matricula == aux.getMatricula();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (this.estudante.equals(null) ? 0 : this.estudante.hashCode());
        result = prime * result + (int) this.matricula;

        return result;
    }
}

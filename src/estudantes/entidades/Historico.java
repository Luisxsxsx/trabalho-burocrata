package src.estudantes.entidades;

import src.professor.entidades.CodigoCurso;

public class Historico extends Registro {
    private double coeficiente;
    private String[] componentes;

    public Historico(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante,
            long matricula, double coeficiente, String[] componentes) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);
        this.coeficiente = coeficiente;
        this.componentes = componentes;
    }

    public double getCoeficiente() {
        return this.coeficiente;
    }

    public String[] getComponentes() {
        return this.componentes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Historico aux = (Historico) o;

        return this.getCriador().equals(aux.getCriador()) && this.getCodigoCurso() == aux.getCodigoCurso()
                && this.getPaginas() == aux.getPaginas() && this.getAutenticacao() == aux.getAutenticacao()
                && this.getEstudante().equals(aux.getEstudante()) && this.getMatricula() == aux.getMatricula()
                && this.coeficiente == aux.getCoeficiente() && this.componentes.equals(aux.getComponentes());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();

        result = prime * result + (int) this.coeficiente;
        result = prime * result + (this.componentes.equals(null) ? 0 : this.componentes.hashCode());

        return result;
    }
}

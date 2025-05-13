package src.estudantes.entidades;

import src.professor.entidades.CodigoCurso;

public class Portaria extends Norma {
    private int anoInicio;

    public Portaria(String criador, CodigoCurso codigoCurso, int paginas, int numero, boolean valido, String texto,
            int anoInicio) {
        super(criador, codigoCurso, paginas, numero, valido, texto);
        this.anoInicio = anoInicio;
    }

    public int getAnoInicio() {
        return this.anoInicio;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (o == null || o.getClass() == this.getClass())
            return false;

        Portaria aux = (Portaria) o;

        return this.getCriador().equals(aux.getCriador()) && this.getCodigoCurso() == aux.getCodigoCurso()
                && this.getPaginas() == aux.getPaginas() && this.getNumero() == aux.getNumero()
                && this.getValido() == aux.getValido() && this.getTexto().equals(aux.getTexto())
                && this.anoInicio == aux.getAnoInicio();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + this.anoInicio;

        return result;
    }

}

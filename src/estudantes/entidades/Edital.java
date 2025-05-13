package src.estudantes.entidades;

import src.professor.entidades.CodigoCurso;

public class Edital extends Norma {
    private String[] responsaveis;

    public Edital(String criador, CodigoCurso codigoCurso, int paginas, int numero, boolean valido, String texto,
            String[] responsaveis) {
        super(criador, codigoCurso, paginas, numero, valido, texto);
        this.responsaveis = responsaveis;
    }

    public String[] getResponsaveis() {
        return this.responsaveis;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || o.getClass() != getClass())
            return false;

        Edital aux = (Edital) o;

        return this.getCriador().equals(aux.getCriador()) && this.getCodigoCurso() == aux.getCodigoCurso()
                && this.getPaginas() == aux.getPaginas() && this.getNumero() == aux.getNumero()
                && this.getValido() == aux.getValido() && this.getTexto() == aux.getTexto()
                && this.responsaveis == aux.getResponsaveis();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (this.responsaveis == null ? 0 : this.responsaveis.hashCode());

        return result;
    }
}

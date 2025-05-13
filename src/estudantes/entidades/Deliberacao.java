package src.estudantes.entidades;

import src.professor.entidades.CodigoCurso;

public abstract class Deliberacao extends DocumentoAdministrativo {

    public String texto;

    public Deliberacao(String criador, CodigoCurso codigoCurso, int paginas, String texto) {
        super(criador, codigoCurso, paginas);
        this.texto = texto;
    }

    public String getTexto() {
        return this.texto;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || o.getClass() != getClass())
            return false;

        Deliberacao aux = (Deliberacao) o;

        return this.getCriador().equals(aux.getCriador()) && this.getCodigoCurso() == aux.getCodigoCurso()
                && this.getPaginas() == aux.getPaginas() && this.texto.equals(aux.getTexto());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + (this.getCriador().equals(null) ? 0 : this.getCriador().hashCode());
        result = prime * result + (this.getCodigoCurso() == null ? 0 : this.getCodigoCurso().hashCode());
        result = prime * result + this.getPaginas();
        result = prime * result + (this.getTexto().equals(null) ? 0 : this.getTexto().hashCode());

        return result;
    }
}

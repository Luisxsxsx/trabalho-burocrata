package src.estudantes.entidades;

import src.professor.entidades.CodigoCurso;

/**
 * Classe que representa um documento genérico.
 * <br>
 * <br>
 * <strong>Seu trabalho começa aqui...</strong>
 * 
 * @author Jean & Luis Felipe
 */
public abstract class Documento {
    private String criador;
    private CodigoCurso codigoCurso;
    private int paginas;

    public Documento(String criador, CodigoCurso codigoCurso, int paginas) {
        this.criador = criador;
        this.codigoCurso = codigoCurso;
        this.paginas = paginas;
    }

    public String getCriador() {
        return this.criador;
    }

    public CodigoCurso getCodigoCurso() {
        return this.codigoCurso;
    }

    public int getPaginas() {
        return this.paginas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Documento aux = (Documento) o;
        if (this.criador.equals(aux.getCriador()) && aux.getCodigoCurso() == this.codigoCurso
                && aux.getPaginas() == this.paginas)
            return true;

        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31; // numero primo
        int result = 1;

        result = prime * result + (this.criador.equals(null) ? 0 : this.criador.hashCode());
        result = prime * result + (this.codigoCurso == null ? 0 : this.codigoCurso.hashCode());
        result = prime * result + paginas;

        return result;
    }

}

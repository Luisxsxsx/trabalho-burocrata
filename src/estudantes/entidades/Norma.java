package src.estudantes.entidades;

import src.professor.entidades.CodigoCurso;

public class Norma extends DocumentoAdministrativo {
    private int numero;
    private boolean valido;
    private String texto;

    public Norma(String criador, CodigoCurso codigoCurso, int paginas, int numero, boolean valido, String texto) {
        super(criador, codigoCurso, paginas);
        this.numero = numero;
        this.valido = valido;
        this.texto = texto;
    }

    public int getNumero() {
        return this.numero;
    }

    public boolean getValido() {
        return this.valido;
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

        Norma aux = (Norma) o;
        if (this.getCriador().equals(aux.getCriador()) && aux.getCodigoCurso() == this.getCodigoCurso()
                && aux.getPaginas() == this.getPaginas() && aux.getNumero() == this.numero
                && aux.getValido() == this.valido && this.texto.equals(aux.getTexto()))

            return true;

        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + this.numero;
        result = prime * result + (!this.valido ? 0 : 1);
        result = prime * result + (this.texto == null ? 0 : this.texto.hashCode());

        return result;
    }
}

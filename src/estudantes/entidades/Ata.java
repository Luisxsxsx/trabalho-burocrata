package src.estudantes.entidades;

import src.professor.entidades.CodigoCurso;

public class Ata extends Documento {
    private int numero;
    private String texto;
    private String[] presentes;

    public Ata(String criador, CodigoCurso codigoCurso, int paginas, int numero, String texto, String[] presentes) {
        super(criador, codigoCurso, paginas);
        this.texto = texto;
        this.numero = numero;
        this.presentes = presentes;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getTexto() {
        return this.texto;
    }

    public String[] getPresentes() {
        return this.presentes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Ata aux = (Ata) o;
        if (this.getCriador().equals(aux.getCriador()) && aux.getCodigoCurso() == this.getCodigoCurso()
                && aux.getPaginas() == this.getPaginas() && aux.getNumero() == this.numero
                && this.presentes.equals(aux.getPresentes()))
            return true;

        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + (this.getCriador().equals(null) ? 0 : this.getCriador().hashCode());
        result = prime * result + (this.getCodigoCurso() == null ? 0 : this.getCodigoCurso().hashCode());
        result = prime * result + this.getPaginas();
        result = prime * result + this.numero;
        result = prime * result + (this.texto.equals(null) ? 0 : this.texto.hashCode());
        result = prime * result + (this.presentes.equals(null) ? 0 : this.presentes.hashCode());

        return result;
    }
}

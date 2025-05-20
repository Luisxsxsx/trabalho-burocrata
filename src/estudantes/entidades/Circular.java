package src.estudantes.entidades;

import src.professor.entidades.CodigoCurso;

public class Circular extends Deliberacao {
    private String[] destinatarios;

    public Circular(String criador, CodigoCurso codigoCurso, int paginas, String texto, String[] destinatarios) {
        super(criador, codigoCurso, paginas, texto);
        this.destinatarios = destinatarios;
    }

    public String[] getDestinatarios() {
        return this.destinatarios;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || o.getClass() != getClass())
            return false;

        Circular aux = (Circular) o;

        return this.getCriador().equals(aux.getCriador()) && this.getCodigoCurso() == aux.getCodigoCurso()
                && this.getPaginas() == aux.getPaginas() && this.getTexto().equals(aux.getTexto())
                && this.destinatarios.equals(aux.getDestinatarios());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + (this.getCriador().equals(null) ? 0 : this.getCriador().hashCode());
        result = prime * result + (this.getCodigoCurso() == null ? 0 : this.getCodigoCurso().hashCode());
        result = prime * result + this.getPaginas();
        result = prime * result + (this.getTexto().equals(null) ? 0 : this.getTexto().hashCode());
        result = prime * result + (this.getDestinatarios().equals(null) ? 0 : this.getDestinatarios().hashCode());

        return result;
    }
}

package src.estudantes.entidades;

import src.professor.entidades.CodigoCurso;

public class Oficio extends Deliberacao {
    private String destinatario;

    public Oficio(String criador, CodigoCurso codigoCurso, int paginas, String texto, String destinatario) {
        super(criador, codigoCurso, paginas, texto);
        this.destinatario = destinatario;
    }

    public String getDestinatario() {
        return this.destinatario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || o.getClass() == getClass())
            return false;

        Oficio aux = (Oficio) o;

        return this.getCriador().equals(aux.getCriador()) && this.getCodigoCurso() == aux.getCodigoCurso()
                && this.getPaginas() == aux.getPaginas() && this.getTexto().equals(aux.getTexto())
                && this.destinatario.equals(aux.getDestinatario());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (this.destinatario.equals(null) ? 0 : this.destinatario.hashCode());

        return result;
    }
}

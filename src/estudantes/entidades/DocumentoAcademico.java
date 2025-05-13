package src.estudantes.entidades;

import src.professor.entidades.CodigoCurso;

public abstract class DocumentoAcademico extends Documento {
    private long autenticacao;

    public DocumentoAcademico(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao) {
        super(criador, codigoCurso, paginas);
        this.autenticacao = autenticacao;
    }

    public long getAutenticacao() {
        return this.autenticacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        DocumentoAcademico aux = (DocumentoAcademico) o;

        if (this.getCriador().equals(aux.getCriador()) && aux.getCodigoCurso() == this.getCodigoCurso()
                && aux.getPaginas() == this.getPaginas() && aux.getAutenticacao() == this.autenticacao)
            return true;

        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();

        result = prime * result + (int) this.autenticacao;

        return result;
    }
}

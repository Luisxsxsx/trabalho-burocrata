package src.estudantes.entidades;

import src.professor.entidades.CodigoCurso;

public abstract class DocumentoAdministrativo extends Documento {

    public DocumentoAdministrativo(String criador, CodigoCurso codigoCurso, int paginas) {
        super(criador, codigoCurso, paginas);
    }

}

package src.estudantes.entidades;

// import java.util.Random;
import java.util.LinkedList;

import src.professor.entidades.*;

/**
 * Classe que traz a lógica do algoritmo de organização e despacho de processos.
 * <br>
 * <br>
 * Você pode incluir novos atributos e métodos nessa classe para criar
 * lógicas mais complexas para o gerenciamento da organização e despacho de
 * processos, mas esses <strong>atributos e métodos devem ser todos
 * privados</strong> e eles não serão invocados diretamente pelo simulador.
 * <br>
 * <br>
 * Os únicos métodos públicos devem ser: getEstresse, trabalhar, estressar e
 * estressarMuito.
 * 
 * @author coloque os nomes dos autores aqui
 */
public class Burocrata {
    private int estresse = 0;
    private Mesa mesa;
    private Universidade universidade;

    /**
     * Construtor de Burocrata.
     * 
     * @param mesa         com os processos
     * @param universidade com os montes dos cursos e a secretaria
     */
    public Burocrata(Mesa mesa, Universidade universidade) {
        this.mesa = mesa;
        this.universidade = universidade;
    }

    /**
     * Retorna o nível atual de estresse do burocrata.
     * 
     * @return estresse do Burocrata
     */
    public int getEstresse() {
        return estresse;
    }

    private int contarPaginasProcesso(Processo processo) {
        int paginas = 0;
        Documento[] docs = processo.pegarCopiaDoProcesso();
        if (docs.length == 0)
            return paginas;

        for (Documento doc : docs) {
            paginas += doc.getPaginas();
        }

        return paginas;
    }

    private boolean precisaRemoverDocumento(Processo processo) {
        if (contarPaginasProcesso(processo) > 250)
            return true;
        return false;
    }

    private boolean hasAdmDoc(Processo processo) {
        Documento[] docs = processo.pegarCopiaDoProcesso();
        for (Documento doc : docs) {
            if (doc instanceof DocumentoAdministrativo)
                return true;
        }
        return false;
    }

    private boolean hasAcdDoc(Processo processo) {
        Documento[] docs = processo.pegarCopiaDoProcesso();
        for (Documento doc : docs) {
            if (doc instanceof DocumentoAcademico)
                return true;
        }
        return false;
    }

    private boolean hasDocSubstancial(Processo processo) {
        for (Documento documento : processo.pegarCopiaDoProcesso()) {
            if (isDocSubstancial(documento))
                return true;
        }
        return false;
    }

    private boolean isDocSubstancial(Documento doc) {
        if (doc.getPaginas() >= 100) {
            if (doc instanceof Portaria) {
                Portaria temp = (Portaria) doc;
                if (temp.getValido())
                    return true;
                else
                    return false;
            }
            if (doc instanceof Edital) {
                Edital temp = (Edital) doc;
                if (temp.getValido())
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

    private boolean hasDestinatarioComum(Processo processo) {
        Documento[] docs = processo.pegarCopiaDoProcesso();
        LinkedList<String> nomes = new LinkedList<>();
        for (Documento doc : docs) {
            if (doc instanceof Oficio) {
                Oficio temp = (Oficio) doc;
                nomes.add(temp.getDestinatario());
            }
            if (doc instanceof Circular) {
                Circular temp = (Circular) doc;
                for (String string : temp.getDestinatarios()) {
                    nomes.add(string);
                }
            }
        }
        for (int i = 0; i < nomes.size(); i++) {
            for (int j = i + 1; j < nomes.size(); j++) {
                if (nomes.get(i).equals(nomes.get(j)))
                    return true;
            }
        }

        return false;
    }

    private boolean hasOnlyAtas(Processo processo) {
        for (Documento doc : processo.pegarCopiaDoProcesso()) {
            if (!(doc instanceof Ata))
                return false;
        }
        return true;
    }

    private boolean isAdmDoc(Documento doc) {
        if (doc instanceof DocumentoAdministrativo)
            return true;
        return false;
    }

    private void addDocumentosProcessos(Processo[] processos, Documento[] docs, CodigoCurso aux) {
        Documento[] monte = docs;
        // Documento documento = null;
        // Verificar se tem documento substancial no monte
        boolean docSubstancial = false;
        for (Documento doc : monte) {
            if (isDocSubstancial(doc)) {
                docSubstancial = true;
                break;
            }
        }

        // Adicionar antes de tudo em um processo
        if (docSubstancial) {
            for (Processo processo : processos) {
                for (int i = 0; i < monte.length; i++) {
                    if (monte[i] != null && isDocSubstancial(monte[i]) && processo.pegarCopiaDoProcesso().length == 0) {
                        processo.adicionarDocumento(monte[i]);
                        universidade.removerDocumentoDoMonteDoCurso(monte[i], aux);
                        monte[i] = null;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < processos.length; i++) {
            for (int j = 0; j < monte.length; j++) {
                if (monte[j] != null) {
                    if (!hasDocSubstancial(processos[i])) {
                        processos[i].adicionarDocumento(monte[j]);
                        universidade.removerDocumentoDoMonteDoCurso(monte[j], aux);

                        if (precisaRemoverDocumento(processos[i])) {
                            processos[i].removerDocumento(monte[j]);
                            break;
                        }
                    }
                    monte[j] = null;

                }
            }
        }
    }

    private boolean hasDiplomasECertificados(Processo processo) {
        int temp = -1;
        for (Documento documento : processo.pegarCopiaDoProcesso()) {
            if (documento instanceof Certificado) {
                temp++;
                break;
            }
        }
        for (Documento documento : processo.pegarCopiaDoProcesso()) {
            if (documento instanceof Diploma) {
                temp++;
                break;
            }
        }
        if (temp == 1)
            return true;
        return false;
    }

    private boolean hasAtestadosDiferentes(Processo processo) {
        int temp = 0;
        for (Documento doc : processo.pegarCopiaDoProcesso()) {
            if (doc instanceof Atestado)
                temp++;
            if (temp > 1)
                break;
        }
        if (temp > 1) {
            LinkedList<Atestado> aux = new LinkedList<>();
            Documento[] monte = processo.pegarCopiaDoProcesso();
            for (Documento documento : monte) {
                if (documento instanceof Atestado) {
                    aux.add((Atestado) documento);
                }
            }
            for (int i = 0; i < aux.size(); i++) {
                for (int j = i; j < aux.size(); j++) {
                    if (!(aux.get(i).getCategoria().equals(aux.get(j).getCategoria())))
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * Metodo para escolher o monte com a maior quantidade de documentos
     * @return CodigoCurso
     */
    private CodigoCurso escolheMaiorMonte(){
        CodigoCurso temp = null;
        int maior = 0;
        for (CodigoCurso curso : CodigoCurso.values()) {
            if(universidade.contarDocumentosNoMonteDoCurso(curso) > maior){
                maior = universidade.contarDocumentosNoMonteDoCurso(curso);
                temp = curso;
            }
        }   
        return temp;
    }

    /**
     * Executa a lógica de criação e despacho dos processos.
     * <br>
     * <br>
     * Esse método é o único método de controle invocado durante a simulação
     * da universidade.
     * <br>
     * <br>
     * Aqui podem ser feitas todas as verificações sobre os documentos nos
     * montes dos cursos e dos processos abertos na mesa do Burocrata. A partir
     * dessas informações, você pode colocar documentos nos processos abertos
     * e despachar os processos para a secretaria acadêmica.
     * <br>
     * <br>
     * Cuidado com a complexidade do seu algoritmo, porque se ele demorar muito
     * serão criados menos documentos na sua execução e sua produtividade geral
     * vai cair.
     * <br>
     * <br>
     * <strong>O burocrata não pode manter documentos com ele</strong> depois
     * que o método trabalhar terminar de executar, ou seja, você deve devolver
     * para os montes dos cursos todos os documentos que você removeu dos montes
     * dos cursos.
     * 
     * @see professor.entidades.Universidade#despachar(Processo)
     * @see professor.entidades.Universidade#removerDocumentoDoMonteDoCurso(estudantes.entidades.Documento,
     *      professor.entidades.CodigoCurso)
     * @see professor.entidades.Universidade#devolverDocumentoParaMonteDoCurso(estudantes.entidades.Documento,
     *      professor.entidades.CodigoCurso)
     */
    /*
     * Um processo não pode conter Doc de Administração
     * e Doc Acadêmicos simultaneamente, mas Atas podem (feito)
     * 
     * Um processo não pode ser despachado apenas com atas (feito)
     * 
     * Um processo não pode despachar Doc de graduação e Pós
     * ao mesmo tempo (Feito)
     * 
     * Uma Portaria e Edital com 100 páginas é um
     * Documento Substancial, portanto devem ser
     * despachados sozinho, exceto caso estiverem invalidos (Feito)
     *
     * Diferentes Circulares e Ofícios só podem ser despachados
     * juntos se tiverem um destinatário em comum. (Feito)
     * 
     * Diplomas só podem ser despachados com Diplomas, Certificados
     * ou Atas. (Feito)
     * 
     * Atestados de diferentes categorias não podem estar no mesmo
     * processo. (Feito)
     * 
     * DESCUMPRIR ESSAS REGRAS GERA ESTRESSE!
     * 
     * Números de páginas máximos por processo = 250
     * exceder este número causa perda de todos arquivos
     * no despacho e uma Advertência administrativa
     * 
     * Sempre que um processo é despachado, outro vazio é criado
     */
    public void trabalhar() {
        // Random aleatorio = new Random();
        // int escolheMonte;
        Documento[] monte = null;
        Processo[] processos = mesa.getProcessos();
        CodigoCurso aux = null;
        LinkedList<Documento> docsRetirados = new LinkedList<>();

        aux = escolheMaiorMonte();
        // System.out.println(aux);
        monte = universidade.pegarCopiaDoMonteDoCurso(aux);

        // while (monte == null || monte.length == 0) {
        //     escolheMonte = aleatorio.nextInt(0, 10);
        //     // System.out.println(escolheMonte);
        //     switch (escolheMonte) {
        //         case 0:
        //             aux = CodigoCurso.GRADUACAO_CIENCIA_DA_COMPUTACAO;
        //             monte = universidade.pegarCopiaDoMonteDoCurso(aux);
        //             break;
        //         case 1:
        //             aux = CodigoCurso.GRADUACAO_ENGENHARIA_AGRICOLA;
        //             monte = universidade.pegarCopiaDoMonteDoCurso(aux);
        //             break;
        //         case 2:
        //             aux = CodigoCurso.GRADUACAO_ENGENHARIA_CIVIL;
        //             monte = universidade.pegarCopiaDoMonteDoCurso(aux);
        //             break;
        //         case 3:
        //             aux = CodigoCurso.GRADUACAO_ENGENHARIA_ELETRICA;
        //             monte = universidade.pegarCopiaDoMonteDoCurso(aux);
        //             break;
        //         case 4:
        //             aux = CodigoCurso.GRADUACAO_ENGENHARIA_MECANICA;
        //             monte = universidade.pegarCopiaDoMonteDoCurso(aux);
        //             break;
        //         case 5:
        //             aux = CodigoCurso.GRADUACAO_ENGENHARIA_SOFTWARE;
        //             monte = universidade.pegarCopiaDoMonteDoCurso(aux);
        //             break;
        //         case 6:
        //             aux = CodigoCurso.GRADUACAO_ENGENHARIA_TELECOMUNICACOES;
        //             monte = universidade.pegarCopiaDoMonteDoCurso(aux);
        //             break;
        //         case 7:
        //             aux = CodigoCurso.POS_GRADUACAO_ENGENHARIA;
        //             monte = universidade.pegarCopiaDoMonteDoCurso(aux);
        //             break;
        //         case 8:
        //             aux = CodigoCurso.POS_GRADUACAO_ENGENHARIA_ELETRICA;
        //             monte = universidade.pegarCopiaDoMonteDoCurso(aux);
        //             break;
        //         case 9:
        //             aux = CodigoCurso.POS_GRADUACAO_ENGENHARIA_SOFTWARE;
        //             monte = universidade.pegarCopiaDoMonteDoCurso(aux);
        //             break;
        //         default:
        //             break;
        //     }
        // }

        addDocumentosProcessos(processos, monte, aux);

        for (Processo processo : processos) {
            if (hasDocSubstancial(processo))
                continue;

            if (hasAdmDoc(processo) && hasAcdDoc(processo)) {
                for (Documento doc : processo.pegarCopiaDoProcesso()) {
                    if (isAdmDoc(doc)) {
                        docsRetirados.add(doc);
                        processo.removerDocumento(doc);
                    }
                }
            }
            if (!hasDestinatarioComum(processo)) {
                for (Documento doc : processo.pegarCopiaDoProcesso()) {
                    if (doc instanceof Oficio || doc instanceof Circular) {
                        docsRetirados.add(doc);
                        processo.removerDocumento(doc);
                        break;
                    }
                }
            }
            if (hasDiplomasECertificados(processo)) {
                for (Documento doc : processo.pegarCopiaDoProcesso()) {
                    if (doc instanceof Diploma) {
                        docsRetirados.add(doc);
                        processo.removerDocumento(doc);
                    }
                }
            }
            if (hasAtestadosDiferentes(processo)) {
                for (Documento documento : processo.pegarCopiaDoProcesso()) {
                    if (documento instanceof Atestado) {
                        docsRetirados.add(documento);
                        processo.removerDocumento(documento);
                    }
                }
            }
        }
        Documento[] monteRetirados = docsRetirados.toArray(new Documento[docsRetirados.size()]);

        for (Processo processo : processos) {
            if (hasOnlyAtas(processo)) {
                for (int i = 0; i < monteRetirados.length; i++) {
                    if (monteRetirados[i] != null) {
                        processo.adicionarDocumento(monteRetirados[i]);
                        if (precisaRemoverDocumento(processo)) {
                            processo.removerDocumento(monteRetirados[i]);
                            continue;
                        }
                        monteRetirados[i] = null;
                    }
                }
            }
        }

        if(monteRetirados.length > 0){
            // System.out.println(monteRetirados.length);
            for (Documento documento : monteRetirados) {
                universidade.devolverDocumentoParaMonteDoCurso(documento, aux);
            }
        }

        for (Processo processo : processos) {
            if (processo.pegarCopiaDoProcesso().length > 0) {
                if (!hasOnlyAtas(processo))
                    universidade.despachar(processo);
            }
        }
    }

    /**
     * Aumenta o estresse do burocrata em uma unidade.
     */
    public void estressar() {
        estresse++;
    }

    /**
     * Aumenta o estresse do burocrata em dez unidades.
     */
    public void estressarMuito() {
        estresse += 10;
    }
}

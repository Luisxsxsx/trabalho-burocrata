package src.estudantes.entidades;

import java.util.Random;

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

    private static int contarPaginasProcesso(Processo processo) {
        int paginas = 0;
        Documento[] docs = processo.pegarCopiaDoProcesso();
        if (docs.length == 0)
            return paginas;

        for (Documento doc : docs) {
            paginas += doc.getPaginas();
        }

        return paginas;
    }

    private static boolean precisaRemoverDocumento(Processo processo) {
        if (contarPaginasProcesso(processo) > 250)
            return true;
        return false;
    }

    private static boolean hasDocumentosPos(Processo processo) {
        Documento[] docs = processo.pegarCopiaDoProcesso();
        for (Documento documento : docs) {
            if (documento.getCodigoCurso() == CodigoCurso.POS_GRADUACAO_ENGENHARIA
                    || documento.getCodigoCurso() == CodigoCurso.POS_GRADUACAO_ENGENHARIA_ELETRICA
                    || documento.getCodigoCurso() == CodigoCurso.POS_GRADUACAO_ENGENHARIA_SOFTWARE)
                return true;
        }
        return false;
    }

    private static boolean hasAdmDoc(Processo processo) {
        Documento[] docs = processo.pegarCopiaDoProcesso();
        for (Documento doc : docs) {
            if (doc instanceof DocumentoAdministrativo)
                return true;
        }
        return false;
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
     * e Doc Acadêmicos simultaneamente, mas Atas podem
     * 
     * Um processo não pode ser despachado apenas com atas
     * 
     * Um processo não pode despachar Doc de graduação e Pós
     * ao mesmo tempo (Feito)
     * 
     * Uma Portaria e Edital com 100 páginas é um
     * Documento Substancial, portanto devem ser despachados
     * sozinho, exceto caso estiverem invalidos
     *
     * Diferentes Circulares e Ofícios só podem ser despachados
     * juntos se tiverem um destinatário em comum.
     * 
     * Diplomas só podem ser despachados com Diplomas, Certificados
     * ou Atas.
     * 
     * Atestados de diferentes categorias não podem estar no mesmo
     * processo.
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
        Random aleatorio = new Random();
        int escolheMonte = (int) aleatorio.nextInt(0, 9);
        Documento[] monte = null;
        Documento escolhido = null;
        Processo[] processos = mesa.getProcessos();
        CodigoCurso aux = null;
        while (monte == null) {
            switch (escolheMonte) {
                case 0:
                    aux = CodigoCurso.GRADUACAO_CIENCIA_DA_COMPUTACAO;
                    monte = universidade.pegarCopiaDoMonteDoCurso(aux);
                    break;
                case 1:
                    aux = CodigoCurso.GRADUACAO_ENGENHARIA_AGRICOLA;
                    monte = universidade.pegarCopiaDoMonteDoCurso(aux);
                    break;
                case 2:
                    aux = CodigoCurso.GRADUACAO_ENGENHARIA_CIVIL;
                    monte = universidade.pegarCopiaDoMonteDoCurso(aux);
                    break;
                case 3:
                    aux = CodigoCurso.GRADUACAO_ENGENHARIA_ELETRICA;
                    monte = universidade.pegarCopiaDoMonteDoCurso(aux);
                    break;
                case 4:
                    aux = CodigoCurso.GRADUACAO_ENGENHARIA_MECANICA;
                    monte = universidade.pegarCopiaDoMonteDoCurso(aux);
                    break;
                case 5:
                    aux = CodigoCurso.GRADUACAO_ENGENHARIA_SOFTWARE;
                    monte = universidade.pegarCopiaDoMonteDoCurso(aux);
                    break;
                case 6:
                    aux = CodigoCurso.GRADUACAO_ENGENHARIA_TELECOMUNICACOES;
                    monte = universidade.pegarCopiaDoMonteDoCurso(aux);
                    break;
                case 7:
                    aux = CodigoCurso.POS_GRADUACAO_ENGENHARIA;
                    monte = universidade.pegarCopiaDoMonteDoCurso(aux);
                    break;
                case 8:
                    aux = CodigoCurso.POS_GRADUACAO_ENGENHARIA_ELETRICA;
                    monte = universidade.pegarCopiaDoMonteDoCurso(aux);
                    break;
                case 9:
                    aux = CodigoCurso.POS_GRADUACAO_ENGENHARIA_SOFTWARE;
                    monte = universidade.pegarCopiaDoMonteDoCurso(aux);
                    break;
                default:
                    break;
            }
        }

        if (monte.length != 0) {
            int i;
            boolean proximo;
            for (i = 0; i < 5; i++) {
                proximo = false;
                for (int j = 0; j < monte.length; j++) {
                    if (monte[j] != null) {
                        escolhido = monte[j];
                        if (processos[i].pegarCopiaDoProcesso().length == 0) {

                            processos[i].adicionarDocumento(escolhido);
                            universidade.removerDocumentoDoMonteDoCurso(escolhido, aux);

                            monte[j] = null;
                        } else if (((escolheMonte > 6) && hasDocumentosPos(processos[i])) || escolhido instanceof Ata) {

                            processos[i].adicionarDocumento(escolhido);
                            universidade.removerDocumentoDoMonteDoCurso(escolhido, aux);

                            if (precisaRemoverDocumento(processos[i])) {
                                processos[i].removerDocumento(escolhido);
                                proximo = true;
                                break;
                            }
                            monte[j] = null;

                        } else {
                            proximo = true;
                            break;
                        }
                    }
                }
                if (!proximo)
                    break;
            }

        }
        
        for (Processo processo : processos) {
            if (processo.pegarCopiaDoProcesso().length > 0)
                universidade.despachar(processo);
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

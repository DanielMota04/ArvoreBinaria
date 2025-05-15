import java.util.ArrayList;
import java.util.List;

class ArvoreBinaria<T extends Comparable<T>> {
    private No<T> raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public No<T> getRaiz() {
        return raiz;
    }

    private No<T> inserir(No<T> atual, No<T> novoNo) {
        if (atual == null) {
            return novoNo;
        } else if (novoNo.getConteudo().compareTo(atual.getConteudo()) < 0) {
            atual.setNoEsquerdo(inserir(atual.getNoEsquerdo(), novoNo));
        } else {
            atual.setNoDireito(inserir(atual.getNoDireito(), novoNo));
        }

        atualizarAltura(atual);

        int fatorBalanceamento = fatorBalanceamento(atual);

        if (fatorBalanceamento > 1 && novoNo.getConteudo().compareTo(atual.getNoEsquerdo().getConteudo()) < 0) {
            return rotacaoSimplesDireita(atual);
        }

        if (fatorBalanceamento < -1 && novoNo.getConteudo().compareTo(atual.getNoDireito().getConteudo()) > 0) {
            return rotacaoSimplesEsquerda(atual);
        }

        if (fatorBalanceamento > 1 && novoNo.getConteudo().compareTo(atual.getNoEsquerdo().getConteudo()) > 0) {
            return rotacaoDuplaEsquerdaDireita(atual);
        }

        if (fatorBalanceamento < -1 && novoNo.getConteudo().compareTo(atual.getNoDireito().getConteudo()) < 0) {
            return rotacaoDuplaDireitaEsquerda(atual);
        }

        return atual;
    }

    public void inserir(T conteudo) {
        No<T> novoNo = new No<>(conteudo);
        raiz = inserir(raiz, novoNo);
    }
    private No<T> buscarNo(No<T> no, T valor) {
        if (no == null || no.getConteudo().equals(valor)) {
            return no;
        }
        No<T> encontrado = buscarNo(no.getNoEsquerdo(), valor);
        if (encontrado == null) {
            encontrado = buscarNo(no.getNoDireito(), valor);
        }
        return encontrado;
    }

    public int grauNo(T valor) {
        No<T> no = buscarNo(raiz, valor);
        if (no == null) return -1;
        int grau = 0;
        if (no.getNoEsquerdo() != null) grau++;
        if (no.getNoDireito() != null) grau++;
        return grau;
    }

    public int grauArvore() {
        return calcularGrau(raiz);
    }

    private int calcularGrau(No<T> no) {
        if (no == null) return 0;
        int grauAtual = 0;
        if (no.getNoEsquerdo() != null) grauAtual++;
        if (no.getNoDireito() != null) grauAtual++;
        return Math.max(grauAtual, Math.max(calcularGrau(no.getNoEsquerdo()), calcularGrau(no.getNoDireito())));
    }

    public int profundidadeNo(T valor) {
        return calcularProfundidade(raiz, valor, 0);
    }

    private int calcularProfundidade(No<T> no, T valor, int profundidade) {
        if (no == null) return -1;
        if (no.getConteudo().equals(valor)) return profundidade;
        int esq = calcularProfundidade(no.getNoEsquerdo(), valor, profundidade + 1);
        if (esq != -1) return esq;
        return calcularProfundidade(no.getNoDireito(), valor, profundidade + 1);
    }

    public int profundidadeArvore() {
        return calcularProfundidadeArvore(raiz);
    }

    private int calcularProfundidadeArvore(No<T> no) {
        if (no == null) {
            return 0;
        }
        int profundidadeEsq = calcularProfundidadeArvore(no.getNoEsquerdo());
        int profundidadeDir = calcularProfundidadeArvore(no.getNoDireito());

        return 1 + Math.max(profundidadeEsq, profundidadeDir);
    }

    public int alturaNo(T valor) {
        No<T> no = buscarNo(raiz, valor);
        if (no == null) {
            return -1;
        }
        return calcularAltura(no);
    }

    private int calcularAltura(No<T> no) {
        if (no == null) {
            return -1;
        }
        int alturaEsq = calcularAltura(no.getNoEsquerdo());
        int alturaDir = calcularAltura(no.getNoDireito());

        return 1 + Math.max(alturaEsq, alturaDir);
    }


    public int alturaArvore() {
        return calcularAltura(raiz);
    }

    public int nivelNo(T valor) {
        return calcularNivel(raiz, valor, 0);
    }

    private int calcularNivel(No<T> no, T valor, int nivelAtual) {
        if (no == null) {
            return -1;
        }
        if (no.getConteudo().equals(valor)) {
            return nivelAtual;
        }

        int nivelEsquerda = calcularNivel(no.getNoEsquerdo(), valor, nivelAtual + 1);
        if (nivelEsquerda != -1) {
            return nivelEsquerda;
        }

        return calcularNivel(no.getNoDireito(), valor, nivelAtual + 1);
    }

    public int nivelArvore() {
        return calcularAltura(raiz) - 1;
    }

    public List<T> nosFolhas() {
        List<T> folhas = new ArrayList<>();
        encontrarNosFolhas(raiz, folhas);
        return folhas;
    }

    private void encontrarNosFolhas(No<T> no, List<T> folhas) {
        if (no == null) {
            return;
        }

        if (no.getNoEsquerdo() == null && no.getNoDireito() == null) {
            folhas.add(no.getConteudo());
        }

        encontrarNosFolhas(no.getNoEsquerdo(), folhas);
        encontrarNosFolhas(no.getNoDireito(), folhas);
    }

    public String preOrdem() {
        List<T> resultado = new ArrayList<>();
        percorrerPreOrdem(raiz, resultado);
        return resultado.toString();
    }

    private void percorrerPreOrdem(No<T> no, List<T> resultado) {
        if (no == null) return;
        resultado.add(no.getConteudo());
        percorrerPreOrdem(no.getNoEsquerdo(), resultado);
        percorrerPreOrdem(no.getNoDireito(), resultado);
    }

    public String inOrdem() {
        List<T> resultado = new ArrayList<>();
        percorrerInOrdem(raiz, resultado);
        return resultado.toString();
    }

    private void percorrerInOrdem(No<T> no, List<T> resultado) {
        if (no == null) return;
        percorrerInOrdem(no.getNoEsquerdo(), resultado);
        resultado.add(no.getConteudo());
        percorrerInOrdem(no.getNoDireito(), resultado);
    }

    public String posOrdem() {
        List<T> resultado = new ArrayList<>();
        percorrerPosOrdem(raiz, resultado);
        return resultado.toString();
    }

    private void percorrerPosOrdem(No<T> no, List<T> resultado) {
        if (no == null) return;
        percorrerPosOrdem(no.getNoEsquerdo(), resultado);
        percorrerPosOrdem(no.getNoDireito(), resultado);
        resultado.add(no.getConteudo());
    }

    private int fatorBalanceamento(No<T> no) {
        if (no == null) return 0;
        int alturaEsq = no.getNoEsquerdo() == null ? 0 : no.getNoEsquerdo().getAltura();
        int alturaDir = no.getNoDireito() == null ? 0 : no.getNoDireito().getAltura();
        return alturaEsq - alturaDir;
    }

    public String mostrarFatoresBalanceamento() {
        StringBuilder sb = new StringBuilder();
        mostrarFatoresRecursivo(raiz, sb);
        return sb.toString();
    }

    private void mostrarFatoresRecursivo(No<T> no, StringBuilder sb) {
        if (no != null) {
            int altura = calcularAltura(no);
            int fator = fatorBalanceamento(no);
            sb.append("Nó ").append(no.getConteudo())
                    .append(" -> Altura: ").append(altura)
                    .append(", Fator de Balanceamento: ").append(fator)
                    .append("\n");
            mostrarFatoresRecursivo(no.getNoEsquerdo(), sb);
            mostrarFatoresRecursivo(no.getNoDireito(), sb);
        }
    }

    public boolean estaBalanceada() {
        return verificarBalanceamento(raiz) != -1;
    }

    private int verificarBalanceamento(No<T> no) {
        if (no == null) return 0;

        int alturaEsq = verificarBalanceamento(no.getNoEsquerdo());
        if (alturaEsq == -1) return -1;

        int alturaDir = verificarBalanceamento(no.getNoDireito());
        if (alturaDir == -1) return -1;

        if (Math.abs(alturaEsq - alturaDir) > 1) return -1;

        return Math.max(alturaEsq, alturaDir) + 1;
    }

    private No<T> rotacaoSimplesDireita(No<T> noDesbalanceado) {
        No<T> novoPai = noDesbalanceado.getNoEsquerdo();
        No<T> filhoDireitaNovoPai = novoPai.getNoDireito();

        // Realiza a rotação
        novoPai.setNoDireito(noDesbalanceado);
        noDesbalanceado.setNoEsquerdo(filhoDireitaNovoPai);

        // Atualiza alturas
        atualizarAltura(noDesbalanceado);
        atualizarAltura(novoPai);

        return novoPai;
    }

    private No<T> rotacaoSimplesEsquerda(No<T> noDesbalanceado) {
        No<T> novoPai = noDesbalanceado.getNoDireito();
        No<T> filhoEsquerdaNovoPai = novoPai.getNoEsquerdo();

        // Realiza a rotação
        novoPai.setNoEsquerdo(noDesbalanceado);
        noDesbalanceado.setNoDireito(filhoEsquerdaNovoPai);

        // Atualiza alturas
        atualizarAltura(noDesbalanceado);
        atualizarAltura(novoPai);

        return novoPai;
    }

    private No<T> rotacaoDuplaEsquerdaDireita(No<T> noDesbalanceado) {
        noDesbalanceado.setNoEsquerdo(rotacaoSimplesEsquerda(noDesbalanceado.getNoEsquerdo()));
        return rotacaoSimplesDireita(noDesbalanceado);
    }

    private No<T> rotacaoDuplaDireitaEsquerda(No<T> noDesbalanceado) {
        noDesbalanceado.setNoDireito(rotacaoSimplesDireita(noDesbalanceado.getNoDireito()));
        return rotacaoSimplesEsquerda(noDesbalanceado);
    }

    // Método auxiliar para atualizar altura de um nó
    private void atualizarAltura(No<T> no) {
        if (no == null) return;
        int alturaEsq = no.getNoEsquerdo() == null ? 0 : no.getNoEsquerdo().getAltura();
        int alturaDir = no.getNoDireito() == null ? 0 : no.getNoDireito().getAltura();
        no.setAltura(1 + Math.max(alturaEsq, alturaDir));
    }





    public String buscarInformacoes(T valor) {
        if (raiz == null) return "Árvore vazia!";

        No<T> pai = encontrarPai(raiz, valor);
        No<T> noEncontrado = buscarNo(raiz, valor);

        if (noEncontrado == null) return "Valor não encontrado na árvore.";

        StringBuilder info = new StringBuilder();
        info.append("Nó encontrado: ").append(valor).append("\n");

        if (pai != null) {
            info.append("Pai: ").append(pai.getConteudo()).append("\n");
            if (pai.getNoEsquerdo() != null && pai.getNoEsquerdo().getConteudo().equals(valor)) {
                info.append("É filho esquerdo.\n");
            } else {
                info.append("É filho direito.\n");
            }
        } else {
            info.append("Este nó é a raiz da árvore.\n");
        }

        if (pai != null) {
            if (pai.getNoEsquerdo() != null && !pai.getNoEsquerdo().getConteudo().equals(valor)) {
                info.append("Irmão: ").append(pai.getNoEsquerdo().getConteudo()).append("\n");
            }
            if (pai.getNoDireito() != null && !pai.getNoDireito().getConteudo().equals(valor)) {
                info.append("Irmão: ").append(pai.getNoDireito().getConteudo()).append("\n");
            }
        }

        No<T> avo = encontrarPai(raiz, pai != null ? pai.getConteudo() : null);
        if (avo != null) {
            if (avo.getNoEsquerdo() != null && !avo.getNoEsquerdo().getConteudo().equals(pai.getConteudo())) {
                info.append("Tio: ").append(avo.getNoEsquerdo().getConteudo()).append("\n");
            }
            if (avo.getNoDireito() != null && !avo.getNoDireito().getConteudo().equals(pai.getConteudo())) {
                info.append("Tio: ").append(avo.getNoDireito().getConteudo()).append("\n");
            }
        }

        return info.toString();
    }

    private No<T> encontrarPai(No<T> no, T valor) {
        if (no == null || no.getNoEsquerdo() == null && no.getNoDireito() == null) return null;

        if ((no.getNoEsquerdo() != null && no.getNoEsquerdo().getConteudo().equals(valor)) ||
                (no.getNoDireito() != null && no.getNoDireito().getConteudo().equals(valor))) {
            return no;
        }

        No<T> encontrado = encontrarPai(no.getNoEsquerdo(), valor);
        return (encontrado != null) ? encontrado : encontrarPai(no.getNoDireito(), valor);
    }
}

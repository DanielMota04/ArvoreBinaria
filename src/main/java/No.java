class No<T> {
    private T conteudo;
    private No<T> noEsquerdo;
    private No<T> noDireito;
    private int altura;  // Adicionado para armazenar a altura do nó

    public No(T conteudo) {
        this.conteudo = conteudo;
        this.noEsquerdo = null;
        this.noDireito = null;
        this.altura = 1;  // Novo nó é inicializado com altura 1
    }

    public T getConteudo() {
        return conteudo;
    }

    public No<T> getNoEsquerdo() {
        return noEsquerdo;
    }

    public void setNoEsquerdo(No<T> noEsquerdo) {
        this.noEsquerdo = noEsquerdo;
    }

    public No<T> getNoDireito() {
        return noDireito;
    }

    public void setNoDireito(No<T> noDireito) {
        this.noDireito = noDireito;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}
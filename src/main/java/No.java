class No<T> {
    private T conteudo;
    private No<T> noEsquerdo;
    private No<T> noDireito;

    public No(T conteudo) {
        this.conteudo = conteudo;
        this.noEsquerdo = null;
        this.noDireito = null;
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
}

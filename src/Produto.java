public class Produto {
    private int codigo;
    private String nome;
    private int quantidade;
    private double preco_unit;
    private String categoria;

    Produto(int codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the preco_unit
     */
    public double getPreco_unit() {
        return preco_unit;
    }

    /**
     * @param preco_unit the preco_unit to set
     */
    public void setPreco_unit(double preco_unit) {
        this.preco_unit = preco_unit;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return this.codigo + " " + this.nome;
    }
}

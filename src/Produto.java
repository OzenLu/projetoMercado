public class Produto {
    private int codigo;
    private String nome;
    private int quantidade;
    private String categoria;
    private double precoUnit;

    Produto(int codigo, String nome, int quantidade, String categoria, double precoUnit){
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.precoUnit = precoUnit;
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
    public double getPrecoUnit() {
        return precoUnit;
    }

    /**
     * @param preco_unit the preco_unit to set
     */
    public void setPrecoUnit(double preco_unit) {
        this.precoUnit = preco_unit;
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
        return this.codigo + " " + this.nome + " " + this.quantidade + " " + this.categoria + " " + this.precoUnit;
    }
}

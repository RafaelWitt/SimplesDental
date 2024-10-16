package br.com.simplesdental.type;

/**
 *
 * @author Rafael.Witt
 */
public enum CargoType {
    Desenvolvedor("Desenvolvedor"),
    Designer("Designer"),
    Tester("Tester"),
    Suporte("Suporte");
    private final String descricao;

    /**
     * Construtor.
     *
     * @param descricaoTmp String
     */
    private CargoType(final String descricaoTmp) {
        this.descricao = descricaoTmp;
    }

    public String getDescricao() {
        return descricao;
    }
}

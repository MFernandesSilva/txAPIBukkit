package txdev.txapibukkit.api;

public class DefaultMessages {
    private String noPermission = "&cVocê não tem permissão para usar este comando.";
    private String consoleCommand = "&cComando apenas para jogadores.";

    public String np() {
        return noPermission;
    }

    public String cc() {
        return consoleCommand;
    }
}

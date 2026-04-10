package Camilaronzzani.com.github;

public class Main {
    public static void main(String[] args) {
        FlywayConfig.migrate();

        Visualizacao visualizacao = new Visualizacao();
        visualizacao.menuPrincipal();
    }1
}

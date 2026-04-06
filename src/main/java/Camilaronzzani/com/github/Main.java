package Camilaronzzani.com.github;

import config.FlyWayconfig;

public class Main {
    public static void main(String[] args) {
        FlyWayconfig.migrate();
        Visualizacao visualizacao = new Visualizacao();
        visualizacao.menuPrincipal();
    }
}

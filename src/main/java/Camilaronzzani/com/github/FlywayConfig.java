package Camilaronzzani.com.github;

import org.flywaydb.core.Flyway;

public class FlywayConfig {
    public static void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(
                        "jdbc:postgresql://localhost:5432/uniamerica",
                        "root",
                        "root"
                )
                .baselineOnMigrate(true)
                .load();
        flyway.migrate();
    }
}

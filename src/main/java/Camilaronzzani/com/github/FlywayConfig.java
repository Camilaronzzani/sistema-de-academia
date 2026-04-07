package Camilaronzzani.com.github;

import org.flywaydb.core.Flyway;

public class FlywayConfig {
    public static void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(
                        "jdbc:postgresql://localhost:5432/cadpessoa",
                        "postgres",
                        "root"
                )
                .baselineOnMigrate(true)
                .load();
        flyway.migrate();
    }
}

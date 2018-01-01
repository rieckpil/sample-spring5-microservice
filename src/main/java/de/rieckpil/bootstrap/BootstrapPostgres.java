package de.rieckpil.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"dev", "prod"})
public class BootstrapPostgres implements CommandLineRunner{

    @Override
    public void run(String... args) throws Exception {

    }
}

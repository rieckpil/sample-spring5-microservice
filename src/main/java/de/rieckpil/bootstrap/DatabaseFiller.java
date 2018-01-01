package de.rieckpil.bootstrap;

import de.rieckpil.domain.*;
import de.rieckpil.repositories.MachineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DatabaseFiller implements CommandLineRunner {

    private MachineRepository machineRepository;

    public DatabaseFiller(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadMachines();
    }

    private void loadMachines() {


        Country germany = new Country();
        germany.setName("Germany");

        City herzogenaurach = new City();
        herzogenaurach.setCountry(germany);
        herzogenaurach.setName("Herzogenaurach");

        germany.setCities(Arrays.asList(herzogenaurach));

        Plant iws = new Plant();
        iws.setName("IWS");
        iws.setCity(herzogenaurach);

        herzogenaurach.setPlants(Arrays.asList(iws));

        Hall g1 = new Hall();
        g1.setName("G1");
        g1.setPlant(iws);

        iws.setHalls(Arrays.asList(g1));
        Machine m1 = new Machine();
        m1.setName("M1");
        m1.setHall(g1);

        g1.setMachines(Arrays.asList(m1));

        machineRepository.save(m1);
    }

}

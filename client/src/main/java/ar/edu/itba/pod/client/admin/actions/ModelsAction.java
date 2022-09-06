package ar.edu.itba.pod.client.admin.actions;

import ar.edu.itba.pod.client.admin.CliParser;
import ar.edu.itba.pod.exceptions.PlaneModelAlreadyExistsException;
import ar.edu.itba.pod.models.SeatCategory;
import ar.edu.itba.pod.services.AdminService;
import ar.edu.itba.pod.utils.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelsAction {
    private final Logger logger = LoggerFactory.getLogger(ModelsAction.class);
    private final AdminService service;
    private final CliParser.Arguments arguments;

    public ModelsAction(AdminService service, CliParser.Arguments arguments) {
        this.service = service;
        this.arguments = arguments;
    }

    public void run() throws Exception {

        var planeModels = Files
                    .readAllLines(Path.of(String.valueOf(arguments.getFilePath())))
                    .stream().skip(1)
                    .map(t-> t.split(";"))
                    .map(t-> new PlaneModel(t[0], t[1]))
                    .collect(Collectors.toList());

        int addedPlaneModels = 0;
        for (var planeModel : planeModels) {
             try {
                 service.addPlane(planeModel.getPlaneModelName(), planeModel.getSeatsPerCategory());
                 addedPlaneModels++;
             }
             catch (RemoteException e) {
                    logger.error("Cannot add model {}", planeModel.getPlaneModelName());
             }

        }

        logger.info("{} added models", addedPlaneModels);

    }

    private static class PlaneModel{
        private final String planeModelName;
        private final Map<SeatCategory, Pair<Integer, Integer>> seatsPerCategory;

        private PlaneModel(String planeModelName, String seats) {
            this.planeModelName = planeModelName;
            // BUSINESS#2#3,PREMIUM_ECONOMY#3#3,ECONOMY#20#10
            this.seatsPerCategory = new HashMap<>();

            for (var section: seats.split(",")) {
                var a = section.split("#");
                this.seatsPerCategory.put(SeatCategory.valueOf(a[0].toUpperCase()),
                        new Pair<>(Integer.parseInt(a[1]), Integer.parseInt(a[2]))
                );
            }
        }

        public String getPlaneModelName() {
            return planeModelName;
        }

        public Map<SeatCategory, Pair<Integer, Integer>> getSeatsPerCategory() {
            return seatsPerCategory;
        }

    }
}

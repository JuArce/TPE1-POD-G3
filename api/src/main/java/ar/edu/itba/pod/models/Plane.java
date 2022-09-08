package ar.edu.itba.pod.models;

import ar.edu.itba.pod.exceptions.EmptySeatDistributionException;
import ar.edu.itba.pod.utils.Pair;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Plane {
    @Getter
    private final String modelName;
    @Getter
    private final List<RowDescription> rows;

    public Plane(String modelName, TreeMap<SeatCategory, Pair<Integer, Integer>> seatsPerCategory) {
        this.modelName = modelName;

        int rows = seatsPerCategory.values().stream().mapToInt(Pair::getFirst).sum();
        if (rows == 0) {
            throw new EmptySeatDistributionException();
        }
        this.rows = new ArrayList<>(rows);

        for (Map.Entry<SeatCategory, Pair<Integer, Integer>> entry : seatsPerCategory.entrySet()) {
            if (entry.getValue().getFirst() <= 0 || entry.getValue().getSecond() <= 0) {
                throw new EmptySeatDistributionException();
            }
            for (int i = 0; i < entry.getValue().getFirst(); i++) {
                this.rows.add(new RowDescription(entry.getValue().getSecond(), entry.getKey()));
            }
        }
    }



    @Override
    public int hashCode() {
        return modelName.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return modelName.equals(plane.modelName);
    }

    public static class RowDescription {
        @Getter
        private final int columns;
        @Getter
        private final SeatCategory category;

        public RowDescription(int columns, SeatCategory category) {
            this.columns = columns;
            this.category = category;
        }
    }
}

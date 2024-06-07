package dev.danvega.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
/*
 records are immutable and cant be updated as they dont contain set methods
 reduces the lines of code we use records and provides implementation for equals hash codes and toStrings
 */
public record Run(
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,
        Location location
) {
    public Run{      //Its non-Canonical constructor is without parameters and it explicitly call canonical constructor
        if (!completedOn.isAfter(startedOn)){
            throw new IllegalArgumentException("Completed on must be after startedon");
        }
    }
}


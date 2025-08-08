package it.sterz.reservationsystem.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {

    COMPLETED("Completed"),
    FAILED("Failed");

    private final String description;
}

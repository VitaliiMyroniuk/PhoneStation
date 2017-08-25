package ua.company.myroniuk.model.entity;

import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class SimCard {
    private Long id;
    private Long userId;
    private Long serviceId;
    private String number;
    private List<Service> services;
    private double balance;
    private boolean isBlocked;
}

package ua.company.myroniuk.model.entity;

import java.util.Objects;

/**
 * The class describes service entity.
 *
 * @author Vitalii Myroniuk
 */
public class Service {
    /**
     * Id of the service.
     */
    private long id;

    /**
     * Name of the service.
     */
    private String name;

    /**
     * Description of the service.
     */
    private String description;

    /**
     * Duration of the service.
     */
    private int duration;

    /**
     * Price of the service.
     */
    private int price;

    // Getters and setters.
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        Service service = (Service) o;
        return id == service.id &&
               duration == service.duration &&
               price == service.price &&
               Objects.equals(name, service.name) &&
               Objects.equals(description, service.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, duration, price);
    }
}

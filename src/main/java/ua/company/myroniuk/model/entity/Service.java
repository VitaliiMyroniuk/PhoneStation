package ua.company.myroniuk.model.entity;

import java.util.Objects;

/**
 * The class describes service entity.
 *
 * @author Vitalii Myroniuk
 */
public class Service {
    private long id;
    private String name;
    private String description;
    private int duration;        // duration of the service (days count).
    private int price;

    public Service() {
    }

    private Service(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.duration = builder.duration;
        this.price = builder.price;
    }

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

    public static class Builder {
        private long id;
        private String name;
        private String description;
        private int duration;
        private int price;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder setPrice(int price) {
            this.price = price;
            return this;
        }

        public Service build() {
            return new Service(this);
        }
    }
}

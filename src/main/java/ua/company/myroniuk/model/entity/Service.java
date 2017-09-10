package ua.company.myroniuk.model.entity;

/**
 * @author Vitalii Myroniuk
 */
public class Service {
    private long id;
    private String name;
    private String description;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return (int) this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Service service = (Service) obj;
        if (this.id != service.id) {
            return false;
        }
        if ((this.name == null) ? (service.name != null) : !this.name.equals(service.name)) {
            return false;
        }
        if ((this.description == null) ? (service.description != null) : !this.description.equals(service.description)) {
            return false;
        }
        if (this.price != service.price) {
            return false;
        }
        return true;
    }
}

import java.io.Serializable;

public class People implements Serializable {
    private Long id;
    private String name;
    private String birthday;
    private String address;
    private Double height;
    private Double weight;


    public People() {
    }

    public People(Long id, String name, String birthday, String address, Double height, Double weight) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.height = height;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
            this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {

        this.weight = weight;
    }


    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday='"+ birthday+
                ", address='" + address + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }

    public void informationPeople() {
        System.out.printf("%-5d %-20s %-15s %-15s \n", id, name, address, height, weight);
    }
}

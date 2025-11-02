package vcarb.stockexchange.server.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "GenericTableName")

public class GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Prop1;
    private String Prop2;

    private int Prop3;

    public String getProp1() { return Prop1; }
    public void setProp1(String symbol) { this.Prop1 = Prop1; }

    public String getProp2() { return Prop2; }
    public void setProp2(double price) { this.Prop2 = Prop2; }

    public int getProp3() { return Prop3; }
    public void setProp3(double price) { this.Prop3 = Prop3; }


}

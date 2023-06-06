package modules.cargoType.entities;

public class DurableCargoType extends CargoType {
    private String sector;
    private String mainMaterial;
    private double ipiPercentage;

    public DurableCargoType(String id, String description, String sector, String mainMaterial,
            double ipiPercentage) {
        super(id, description);
        this.sector = sector;
        this.mainMaterial = mainMaterial;
        this.ipiPercentage = ipiPercentage;
    }

    public String getSector() {
        return sector;
    }

    public String getMainMaterial() {
        return mainMaterial;
    }

    public double getIpiPercentage() {
        return ipiPercentage;
    }
}

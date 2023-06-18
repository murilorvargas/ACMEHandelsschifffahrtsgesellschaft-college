package modules.cargo.entities.interfaces;

import modules.cargo.enums.CargoPriority;
import modules.cargo.enums.CargoStatus;
import modules.cargoType.entities.interfaces.ICargoTypeReadable;
import modules.harbor.entities.interfaces.IHarborReadable;

public interface ICargoReadable {
    int getId();

    double getWeight();

    double getDeclaredValue();

    int getMaxTime();

    CargoPriority getPriority();

    CargoStatus getStatus();

    ICargoTypeReadable getCargoType();

    IHarborReadable getOriginHarbor();

    IHarborReadable getDestinationHarbor();
}

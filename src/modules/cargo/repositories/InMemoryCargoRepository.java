package modules.cargo.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import modules.cargo.entities.Cargo;
import modules.cargo.entities.interfaces.ICargoReadable;
import modules.cargo.enums.CargoPriority;
import modules.cargo.enums.CargoStatus;
import modules.cargo.repositories.interfaces.ICargoRepository;
import modules.cargoType.entities.interfaces.ICargoTypeReadable;
import modules.client.entities.interfaces.IClientReadable;
import modules.harbor.entities.interfaces.IHarborReadable;
import modules.ship.entities.interfaces.IShipReadable;

public class InMemoryCargoRepository implements ICargoRepository {
    private TreeSet<Cargo> cargoList;
    private static InMemoryCargoRepository instance = null;

    public static synchronized InMemoryCargoRepository instanceOf() {
        if (instance == null) {
            instance = new InMemoryCargoRepository();
        }
        return instance;
    }

    private InMemoryCargoRepository() {
        this.cargoList = new TreeSet<Cargo>();
    }

    @Override
    public ICargoReadable create(int id, double weight, double declaredValue, int maxTime, CargoPriority priority,
            ICargoTypeReadable cargoType, IHarborReadable originHarbor, IHarborReadable destinationHarbor,
            IClientReadable client) {
        Cargo cargo = new Cargo(id, weight, declaredValue,
                maxTime, priority, cargoType, originHarbor, destinationHarbor, client);
        this.cargoList.add(cargo);

        return cargo;
    }

    @Override
    public ICargoReadable updateStatus(int id, CargoStatus cargoStatus) {
        for (Cargo cargo : this.cargoList) {
            if (cargo.getId() == id) {
                cargo.setStatus(cargoStatus);
                return cargo;
            }
        }

        return null;
    }

    @Override
    public ICargoReadable updateDestinedShip(int id, IShipReadable destinedShip) {
        for (Cargo cargo : this.cargoList) {
            if (cargo.getId() == id) {
                cargo.setDestinedShip(destinedShip);
                return cargo;
            }
        }

        return null;
    }

    @Override
    public List<ICargoReadable> findAll() {
        List<ICargoReadable> cargoSet = new ArrayList<>();
        for (Cargo cargo : this.cargoList) {
            cargoSet.add(cargo);
        }
        return cargoSet;
    }

    @Override
    public List<ICargoReadable> findByStatus(CargoStatus cargoStatus) {
        List<ICargoReadable> cargoSet = new ArrayList<>();
        for (Cargo cargo : this.cargoList) {
            if (cargo.getStatus() == cargoStatus) {
                cargoSet.add(cargo);
            }
        }
        return cargoSet;
    }

    @Override
    public ICargoReadable findById(int id) {
        for (Cargo cargo : this.cargoList) {
            if (cargo.getId() == id) {
                return cargo;
            }
        }

        return null;
    }
}

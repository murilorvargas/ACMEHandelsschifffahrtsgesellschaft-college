package modules.freight.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import modules.cargo.entities.interfaces.ICargoReadable;
import modules.freight.entities.Freight;
import modules.freight.entities.interfaces.IFreightReadable;
import modules.freight.enums.FreightStatus;
import modules.freight.repositories.interfaces.IFreightRepository;
import modules.ship.entities.interfaces.IShipReadable;

public class InMemoryFreightRepository implements IFreightRepository {
    private TreeSet<Freight> freightSet;
    private static InMemoryFreightRepository instance = null;

    public static synchronized InMemoryFreightRepository instanceOf() {
        if (instance == null) {
            instance = new InMemoryFreightRepository();
        }
        return instance;
    }

    private InMemoryFreightRepository() {
        this.freightSet = new TreeSet<>();
    }

    @Override
    public IFreightReadable createFreight(double value, FreightStatus status, IShipReadable ship,
            ICargoReadable cargo) {
        Freight freight = new Freight(freightSet.size() + 1, value, status, ship, cargo);
        freightSet.add(freight);
        return freight;
    }

    @Override
    public void updateFreight(int id, FreightStatus status) {
        for (Freight freight : freightSet) {
            if (freight.getId() == id) {
                freight.setStatus(status);
                break;
            }
        }
    }

    @Override
    public List<IFreightReadable> findAllInProgress() {
        List<IFreightReadable> freightList = new ArrayList<>();
        for (Freight freight : freightSet) {
            if (freight.getStatus() == FreightStatus.IN_PROGRESS) {
                freightList.add(freight);
            }
        }

        return freightList;
    }

    @Override
    public IFreightReadable findById(int id) {
        for (Freight freight : freightSet) {
            if (freight.getId() == id) {
                return freight;
            }
        }
        return null;
    }

    @Override
    public List<IFreightReadable> findAll() {
        List<IFreightReadable> freightList = new ArrayList<>();
        for (Freight freight : freightSet) {
            freightList.add(freight);
        }

        return freightList;
    }
}

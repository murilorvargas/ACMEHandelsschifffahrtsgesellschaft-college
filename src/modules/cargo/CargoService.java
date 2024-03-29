package modules.cargo;

import java.util.List;

import modules.cargo.dtos.CreateCargoDTO;
import modules.cargo.dtos.FindCargoByIdDTO;
import modules.cargo.dtos.UpdateCargoStatusDTO;
import modules.cargo.entities.interfaces.ICargoReadable;
import modules.cargo.enums.CargoPriority;
import modules.cargo.enums.CargoStatus;
import modules.cargo.repositories.InMemoryCargoRepository;
import modules.cargo.repositories.interfaces.ICargoRepository;
import modules.cargoType.entities.interfaces.ICargoTypeReadable;
import modules.cargoType.repositories.InMemoryCargoTypeRepository;
import modules.cargoType.repositories.interfaces.ICargoTypeRepository;
import modules.client.entities.interfaces.IClientReadable;
import modules.client.repositories.InMemoryClientRepository;
import modules.client.repositories.interfaces.IClientRepository;
import modules.harbor.entities.interfaces.IHarborReadable;
import modules.harbor.repositories.InMemoryHarborRepository;
import modules.harbor.repositories.interfaces.IHarborRepository;
import shared.errors.CargoNotFound;
import shared.errors.CargoTypeNotFound;
import shared.errors.ClientCodeNotFound;
import shared.errors.DestinationHarborNotFound;
import shared.errors.NoCargoRegistered;
import shared.errors.OriginAndDestinationHarborAreTheSame;
import shared.errors.OriginHarborNotFound;
import shared.errors.StatusChangeNotAllowed;

public class CargoService {
    private IHarborRepository harborRepository;
    private ICargoTypeRepository cargoTypeRepository;
    private ICargoRepository cargoRepository;
    private IClientRepository clientRepository;

    public CargoService() {
        this.harborRepository = InMemoryHarborRepository.instanceOf();
        this.cargoTypeRepository = InMemoryCargoTypeRepository.instanceOf();
        this.cargoRepository = InMemoryCargoRepository.instanceOf();
        this.clientRepository = InMemoryClientRepository.instanceOf();
    }

    public ICargoReadable createCargo(CreateCargoDTO createCargoDTO) {
        ICargoTypeReadable cargoType = this.cargoTypeRepository.findByNumber(createCargoDTO.getCargoTypeNumber());

        if (cargoType == null) {
            throw new CargoTypeNotFound(String.valueOf(createCargoDTO.getCargoTypeNumber()));
        }

        IHarborReadable originHarbor = this.harborRepository.findById(createCargoDTO.getOriginHarborId());

        if (originHarbor == null) {
            throw new OriginHarborNotFound(String.valueOf(createCargoDTO.getOriginHarborId()));
        }

        IHarborReadable destinationHarbor = this.harborRepository.findById(createCargoDTO.getDestinationHarborId());

        if (destinationHarbor == null) {
            throw new DestinationHarborNotFound(String.valueOf(createCargoDTO.getDestinationHarborId()));
        }

        if (originHarbor.getId() == destinationHarbor.getId()) {
            throw new OriginAndDestinationHarborAreTheSame();
        }

        IClientReadable client = this.clientRepository.findByCode(createCargoDTO.getClientId());

        if (client == null) {
            throw new ClientCodeNotFound(createCargoDTO.getClientId());
        }

        return this.cargoRepository.create(createCargoDTO.getId(), createCargoDTO.getWeight(),
                createCargoDTO.getDeclaredValue(),
                createCargoDTO.getMaxTime(),
                createCargoDTO.getPriority().equals("quick") ? CargoPriority.QUICK : CargoPriority.CHEAP,
                cargoType, originHarbor, destinationHarbor, client);
    }

    public ICargoReadable updateCargoStatus(UpdateCargoStatusDTO updateCargoStatusDTO) {
        ICargoReadable cargo = this.cargoRepository.findById(updateCargoStatusDTO.getId());
        if (cargo == null) {
            throw new CargoNotFound(String.valueOf(updateCargoStatusDTO.getId()));
        }

        if (cargo.getStatus() == CargoStatus.COMPLETED) {
            throw new StatusChangeNotAllowed();
        }

        if (cargo.getStatus() == updateCargoStatusDTO.getCargoStatus()) {
            return cargo;
        }

        return this.cargoRepository.updateStatus(updateCargoStatusDTO.getId(), updateCargoStatusDTO.getCargoStatus());
    }

    public List<ICargoReadable> findAllCargos() {
        List<ICargoReadable> cargos = this.cargoRepository.findAll();
        if (cargos.isEmpty()) {
            throw new NoCargoRegistered();
        }

        return cargos;
    }

    public ICargoReadable findCargoById(FindCargoByIdDTO findCargoByIdDTO) {
        ICargoReadable cargo = this.cargoRepository.findById(findCargoByIdDTO.getId());
        if (cargo == null) {
            throw new CargoNotFound(String.valueOf(findCargoByIdDTO.getId()));
        }

        return cargo;
    }
}

package modules.cargo;

import modules.cargo.dtos.CreateCargoDTO;
import modules.cargo.entities.Cargo;
import modules.cargo.repositories.ICargoRepository;
import modules.cargo.repositories.in_memory.InMemoryCargoRepository;
import modules.cargoType.entities.CargoType;
import modules.cargoType.repositories.ICargoTypeRepository;
import modules.cargoType.repositories.in_memory.InMemoryCargoTypeRepository;
import shared.errors.CargoTypeNotFound;

public class CargoService {
    private ICargoTypeRepository cargoTypeRepository;
    private ICargoRepository cargoRepository;

    public CargoService() {
        this.cargoTypeRepository = InMemoryCargoTypeRepository.instanceOf();
        this.cargoRepository = InMemoryCargoRepository.instanceOf();
    }

    public Cargo createCargo(CreateCargoDTO createCargoDTO) {
        CargoType cargoType = this.cargoTypeRepository.findById(createCargoDTO.getCargoTypeId());

        if (cargoType == null) {
            throw new CargoTypeNotFound(createCargoDTO.getCargoTypeId());
        }

        return this.cargoRepository.create(createCargoDTO.getId(), createCargoDTO.getWeight(),
                createCargoDTO.getDeclaredValue(),
                createCargoDTO.getMaxTime(), cargoType);
    }
}

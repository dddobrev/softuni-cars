package bg.softuni.cars.repositories;

import bg.softuni.cars.models.entities.BrandEntity;
import bg.softuni.cars.models.entities.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Integer> {

}

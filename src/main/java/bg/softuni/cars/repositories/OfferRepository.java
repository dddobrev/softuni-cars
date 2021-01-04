package bg.softuni.cars.repositories;

import bg.softuni.cars.models.entities.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Integer> {

}
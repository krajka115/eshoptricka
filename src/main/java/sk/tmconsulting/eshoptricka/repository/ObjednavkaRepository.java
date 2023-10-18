package sk.tmconsulting.eshoptricka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.tmconsulting.eshoptricka.model.Objednavka;

public interface ObjednavkaRepository extends JpaRepository <Objednavka, Long> {
}

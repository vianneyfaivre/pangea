package re.vianneyfaiv.pangea.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import re.vianneyfaiv.pangea.domain.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

	public Optional<Country> findOneByIsoName2(String isoName2);
}

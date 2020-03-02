package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Klant;

import java.util.Optional;

public interface KlantService {
    Optional<Klant> findByGebruikersnaamEquals(String gebruikersnaam);
    void klantToevoegen(Klant klant);
}

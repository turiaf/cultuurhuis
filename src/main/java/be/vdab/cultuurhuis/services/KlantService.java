package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Klant;

public interface KlantService {
    Klant findByGebruikersnaamEquals(String gebruikersnaam);
}

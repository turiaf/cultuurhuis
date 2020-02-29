package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Voorstelling;

import java.util.List;
import java.util.Optional;

public interface VoorstellingService {
    List<Voorstelling> findByGenreZonderVerleden(long genreId);
    Optional<Voorstelling> findById(long id);
}

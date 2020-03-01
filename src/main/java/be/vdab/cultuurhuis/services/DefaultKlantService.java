package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.repositories.KlantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultKlantService implements KlantService {
    private final KlantRepository klantRepository;

    public DefaultKlantService(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }

    @Override
    public Klant findByGebruikersnaamEquals(String gebruikersnaam) {
        return klantRepository.findByGebruikersnaamEquals(gebruikersnaam);
    }
}

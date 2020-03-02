package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.exceptions.KlantBestaatAlException;
import be.vdab.cultuurhuis.repositories.KlantRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultKlantService implements KlantService {
    private final KlantRepository klantRepository;

    public DefaultKlantService(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }

    @Override
    public Optional<Klant> findByGebruikersnaamEquals(String gebruikersnaam) {
        return klantRepository.findByGebruikersnaamEquals(gebruikersnaam);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void klantToevoegen(Klant klant) {
        Optional<Klant> optionalKlant = klantRepository.findById(klant.getId());
        optionalKlant.ifPresent(klant1 -> {
            throw new KlantBestaatAlException();
        });
        klantRepository.save(klant);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(klant.getGebruikersnaam())
        );
    }
}

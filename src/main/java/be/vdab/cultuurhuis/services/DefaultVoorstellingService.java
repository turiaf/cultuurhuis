package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultVoorstellingService implements VoorstellingService {
    private final VoorstellingRepository voorstellingRepository;

    public DefaultVoorstellingService(VoorstellingRepository voorstellingRepository) {
        this.voorstellingRepository = voorstellingRepository;
    }

    @Override
    public List<Voorstelling> findByGenreZonderVerleden(long genreId) {
        return voorstellingRepository.findByGenreZonderVerleden(genreId, LocalDateTime.now());
    }

    @Override
    public Optional<Voorstelling> findById(long id) {
        return voorstellingRepository.findById(id);
    }

    @Override
    public List<Voorstelling> findAllInList(List<Long> idList) {
        return voorstellingRepository.findAllInList(idList);
    }

}

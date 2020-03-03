package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.dto.GeluktMislukte;
import be.vdab.cultuurhuis.exceptions.NietGenoegPlaatsException;
import be.vdab.cultuurhuis.exceptions.VoorstellingNietGevondenException;
import be.vdab.cultuurhuis.repositories.ReservatieRepository;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultReservatieService implements ReservatieService {
    private final VoorstellingRepository voorstellingRepository;
    private final ReservatieRepository reservatieRepository;

    public DefaultReservatieService(VoorstellingRepository voorstellingRepository, ReservatieRepository reservatieRepository) {
        this.voorstellingRepository = voorstellingRepository;
        this.reservatieRepository = reservatieRepository;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public GeluktMislukte confirmReservatie(Map<Long, Long> voorstellingen, Klant klant) throws InterruptedException {
        List<Voorstelling> voorstellings = voorstellingRepository.findAllInList(new ArrayList<>(voorstellingen.keySet()));
        GeluktMislukte geluktMislukte = new GeluktMislukte();
        Thread.sleep(10000);
        if(voorstellingen.size() == voorstellings.size()) {
            voorstellings.stream().forEach(voorstelling -> {
                long aantal = voorstellingen.get(voorstelling.getId());
                try {
                    voorstelling.verlagenPlaats(aantal);
                    Reservatie reservatie = new Reservatie(aantal, klant, voorstelling);
                    reservatieRepository.save(reservatie);
                    geluktMislukte.addGelukte(voorstelling, aantal);
                } catch (NietGenoegPlaatsException ex) {
                    geluktMislukte.addMislukte(voorstelling, aantal);
                }
            });
        } else {
            throw new VoorstellingNietGevondenException();
        }
        return geluktMislukte;
    }
}

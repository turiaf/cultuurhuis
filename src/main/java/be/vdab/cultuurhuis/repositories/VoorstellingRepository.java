package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.domain.Voorstelling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VoorstellingRepository extends JpaRepository<Voorstelling, Long> {
    List<Voorstelling> findByGenreZonderVerleden(@Param("genreId") long genreId,
                                                 @Param("today") LocalDateTime today);
    List<Voorstelling> findAllInList(@Param("idList")List<Long> idList);
}

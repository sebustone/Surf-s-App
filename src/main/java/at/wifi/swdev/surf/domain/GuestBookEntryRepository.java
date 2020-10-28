package at.wifi.swdev.surf.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GuestBookEntryRepository extends CrudRepository <GuestBookEntry, Integer> {

    @Override
    List <GuestBookEntry> findAll ();

    GuestBookEntry findGuestBookEntryById (Integer id);

    List<GuestBookEntry> findGuestBookEntriesByUser (String user);

    @Transactional
    void deleteGuestBookEntryById (Integer id);

}

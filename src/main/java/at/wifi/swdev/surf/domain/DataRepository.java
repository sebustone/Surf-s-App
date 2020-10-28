
package at.wifi.swdev.surf.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DataRepository extends JpaRepository<Data, Integer> {

    static final Logger LOG = LoggerFactory.getLogger(DataRepository.class);

}

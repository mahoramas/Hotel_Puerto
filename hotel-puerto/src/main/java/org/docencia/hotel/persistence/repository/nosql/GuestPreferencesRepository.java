package org.docencia.hotel.persistence.repository.nosql;

import org.docencia.hotel.persistence.nosql.document.GuestPreferencesDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
/**
 * @author mahoramas
 * @version 1.0.0
 */
@Repository
public interface GuestPreferencesRepository extends MongoRepository<GuestPreferencesDocument, Long> {
}

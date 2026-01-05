package org.docencia.hotel.persistence.nosql.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author mahoramas
 * @version 1.0.0
 */
@Document(collection = "guest_preferences")
public class GuestPreferencesDocument {

    @Id
    private Long id;

    @Field("preferredLanguage")
    private String preferredLanguage;

    @Field("newsLetterOptIn")
    private boolean newsLetterOptIn;

    @Field("tags")
    private List<String> tags;

    @Field("favoriteRoomType")
    private String favoriteRoomType;

    @Field("notes")
    private String notes;

    /** 
     * Constructor vacio de la clase
    */
    public GuestPreferencesDocument() {
    }

    /**
     * Constructor con el identificador de la clase
     * @param id id del huesped
     */
    public GuestPreferencesDocument(Long id) {
        this.id = id;
    }

    /**
     * Constructor con todos los parametros de la clase
     * @param id id del huesped
     * @param preferredLanguage idioma preferido del huesped
     * @param newsLetterOptIn preferencia en recivir periodicos
     * @param tags etiquetas del huesped
     * @param favoriteRoomType tipo de habitacion favorita
     * @param notes notas del guest
     */
    public GuestPreferencesDocument(Long id, String preferredLanguage, boolean newsLetterOptIn, List<String> tags,
            String favoriteRoomType, String notes) {
        this.id = id;
        this.preferredLanguage = preferredLanguage;
        this.newsLetterOptIn = newsLetterOptIn;
        this.tags = tags;
        this.favoriteRoomType = favoriteRoomType;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public boolean isNewsLetterOptIn() {
        return newsLetterOptIn;
    }

    public void setNewsLetterOptIn(boolean newsLetterOptIn) {
        this.newsLetterOptIn = newsLetterOptIn;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getFavoriteRoomType() {
        return favoriteRoomType;
    }

    public void setFavoriteRoomType(String favoriteRoomType) {
        this.favoriteRoomType = favoriteRoomType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GuestPreferencesDocument other = (GuestPreferencesDocument) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}

/*
 *
 */
package org.uic.barcode.ticket.api.spec;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * The Interface IPlaces.
 * <p>
 * IPlaces provides data on reserved places
 */
public interface IPlaces {

    /**
     * Gets the coach.
     *
     * @return the coach
     */
    public String getCoach();

    /**
     * Sets the coach.
     *
     * @param coach the new coach
     */
    public void setCoach(String coach);

    /**
     * Gets the human readable place string.
     * E.g.: "15-18, 21, 22"
     *
     * @return the place string
     */
    public String getPlaceString();

    /**
     * Sets the the human readable place string.
     * E.g.: "15-18, 21, 22"
     * <p>
     * This elements should be avoided if not explicitly required for a special product.
     *
     * @param placeString the new place string
     */
    public void setPlaceString(String placeString);

    /**
     * Gets the human readable place description.
     * E.g. "2 Window, open space"
     * <p>
     * This elements should be avoided if not explicitly required for a special product.
     *
     * @return the human readable place description
     */
    public String getPlaceDescription();

    /**
     * Sets the human readable place description.
     * E.g. "2 Window, open space"
     *
     * @param placeDescription the new place description
     */
    public void setPlaceDescription(String placeDescription);

    /**
     * Gets the places.
     *
     * @return the places
     */
    public default Collection<String> getPlaces() {
        return getPlacesInt().stream().map(CharSequence::toString).collect(Collectors.toList());
    }

    public Collection<CharSequence> getPlacesInt();

    /**
     * Adds the place.
     *
     * @param place the place
     */
    public void addPlace(String place);

    /**
     * Adds the place, without conversion to an integer type in encoding.
     *
     * @param place the place
     */
    public void addPlaceMustString(String place);


}

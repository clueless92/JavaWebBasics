package bg.tilchev.utils;

import org.modelmapper.PropertyMap;

/**
 * Created on 2017-03-02.
 */
public interface ModelParser {

    <S,D> D convert(S source, Class<D> destinationClass);

    <S,D> D convert(S source, Class<D> destinationClass, PropertyMap<S, D> propertyMap);
}

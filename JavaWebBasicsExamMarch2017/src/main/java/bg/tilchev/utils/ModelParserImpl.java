package bg.tilchev.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import javax.ejb.Stateless;

/**
 * Created on 2017-03-02.
 */
@Stateless
public class ModelParserImpl implements ModelParser {

    private ModelMapper modelMapper;

    public ModelParserImpl() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass) {
        D convertedObject = null;
        convertedObject = this.modelMapper.map(source, destinationClass);
        return convertedObject;
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass, PropertyMap<S, D> propertyMap) {
        D convertedObject = null;
        this.modelMapper.addMappings(propertyMap);
        convertedObject = this.modelMapper.map(source, destinationClass);
        return convertedObject;
    }
}

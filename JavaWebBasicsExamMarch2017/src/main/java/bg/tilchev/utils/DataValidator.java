package bg.tilchev.utils;

import java.util.Set;

/**
 * Created on 2017-03-06.
 */
public interface DataValidator {

    <T> Set<String> validateData(T bindingModel);
}

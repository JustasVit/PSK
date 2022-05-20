package lt.vu.decorators;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;

@Decorator
public class RandomStringGeneratorDecorator implements  IGenerator{
    @Inject @Delegate @Any
    IGenerator iGenerator;

    @Override
    public String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }
}

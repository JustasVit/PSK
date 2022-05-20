package lt.vu.decorators;

import lombok.NoArgsConstructor;

import javax.enterprise.context.RequestScoped;
import java.nio.charset.Charset;
import java.util.Random;

@NoArgsConstructor
@RequestScoped
public class RandomStringGenerator implements IGenerator {
    public String generateRandomString(){
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}

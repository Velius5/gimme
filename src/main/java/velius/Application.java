package velius;

import java.io.IOException;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.piotr.AbbyOCR;
import pl.piotr.TessOCR;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application extends SpringBootServletInitializer {

    public static void main(final String[] args) throws TesseractException, IOException {
        SpringApplication.run(Application.class, args);
        TessOCR.init();
        AbbyOCR.init();
        System.setProperty("jna.library.path", "src/main/resources");
        
        /* TEST POŁĄCZENIA Z API
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
        .url("http://localhost:8080/api/users/getall")
        .build();

        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        */
    }

    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}

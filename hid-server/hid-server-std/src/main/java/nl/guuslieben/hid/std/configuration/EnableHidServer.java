package nl.guuslieben.hid.std.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(HidDeviceServerConfiguration.class)
@ComponentScan("nl.guuslieben.hid.std")
@EnableScheduling
public @interface EnableHidServer {
}

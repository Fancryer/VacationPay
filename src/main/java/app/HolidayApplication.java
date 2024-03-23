package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages={"app"})
@EnableAutoConfiguration
@Configuration
public class HolidayApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(HolidayApplication.class,args);
	}
}
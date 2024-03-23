package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages={"app"})
@EnableAutoConfiguration
@Configuration
public class VacationPayApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(VacationPayApplication.class,args);
	}
}
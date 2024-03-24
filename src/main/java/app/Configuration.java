package app;

import app.model.IDayOffProvider;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import java.util.function.BiFunction;

import static java.time.Month.*;

@org.springframework.context.annotation.Configuration
public class Configuration
{
	/**
	 Bean for the {@link IDayOffProvider} interface.
	 Provides a default implementation of the {@link IDayOffProvider} functional interface.
	 The returned {@link IDayOffProvider} provides a set of non-working holidays based on the labor code of the Russian Federation, Article 112.
	 <p><p><p>The holidays include:
	 <p>{@link Month#JANUARY January} - New Year holidays (1-8) and Christmas (7)
	 <p>{@link Month#FEBRUARY February} - Defender of the Fatherland Day (23)
	 <p>{@link Month#MARCH March} - International Women's Day (8)
	 <p>{@link Month#MAY May} - Spring and Labor Day (1 and 9) and Victory Day (9)
	 <p>{@link Month#JUNE June} - Russia Day (12)
	 <p>{@link Month#NOVEMBER November} - National Unity Day (4)
	 @return an {@link IDayOffProvider} implementation providing the default holiday dates
	 */
	@Bean
	public IDayOffProvider getDefaultHolidayProvider()
	{
		return year->
		{
			BiFunction<Month,Integer,LocalDate> getDay=(month,day)->LocalDate.of(year,month,day);
			/*
			 * Labor Code of the Russian Federation Article 112. Non-working holidays:
			 * 01: 1-8 – New Year holidays; Christmas (7)
			 * 02: 23 – Defender of the Fatherland Day
			 * 03: 8 – International Women's Day
			 * 05: 1, 9 – Spring and Labor Day; Victory Day
			 * 06: 12 – Russia Day
			 * 11: 4 – National Unity Day
			 */
			return Set.of(
					getDay.apply(JANUARY,1),
					getDay.apply(JANUARY,2),
					getDay.apply(JANUARY,3),
					getDay.apply(JANUARY,4),
					getDay.apply(JANUARY,5),
					getDay.apply(JANUARY,6),
					getDay.apply(JANUARY,7),
					getDay.apply(JANUARY,8),
					getDay.apply(FEBRUARY,23),
					getDay.apply(MARCH,8),
					getDay.apply(MAY,1),
					getDay.apply(MAY,9),
					getDay.apply(JUNE,12),
					getDay.apply(NOVEMBER,4)
			);
		};
	}
}
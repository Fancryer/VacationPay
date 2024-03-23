package app;

import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import java.util.function.BiFunction;

import static java.time.Month.*;
import static java.time.Month.NOVEMBER;

@org.springframework.context.annotation.Configuration
public class Configuration
{
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
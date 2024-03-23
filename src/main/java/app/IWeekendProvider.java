package app;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public interface IWeekendProvider
{

	/**
	 Checks if the specified date is a weekend (Saturday or Sunday).
	 @param date the date to be checked
	 @return true if the date is a weekend, false otherwise
	 */
	default boolean isWeekend(LocalDate date)
	{
		return getWeekends().contains(date.getDayOfWeek());
	}

	default Set<DayOfWeek> getWeekends()
	{
		return Set.of(SATURDAY,SUNDAY);
	}
}

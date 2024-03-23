package app.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

/**
 * Provides functionality to check if the specified date is a weekend.
 */
public interface IWeekendProvider
{
	/**
	 * Checks if the specified date is a weekend (Saturday or Sunday).
	 * @param date the date to be checked
	 * @return true if the date is a weekend, false otherwise
	 */
	default boolean isWeekend(LocalDate date)
	{
		return getWeekends().contains(date.getDayOfWeek());
	}

	/**
	 * Gets the set of weekends (Saturday and Sunday).
	 * @return a set containing DayOfWeek values for Saturday and Sunday
	 */
	default Set<DayOfWeek> getWeekends()
	{
		return Set.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
	}
}

package app;

import java.time.LocalDate;
import java.util.Set;

/**
 Provides a set of holidays for the specific year and checks if a date is a weekend or a holiday.
 */
public interface IHolidayProvider extends IWeekendProvider
{

	/**
	 Returns the set of holidays for the specified year.
	 @param year the year
	 @return the set of holidays
	 */
	Set<LocalDate> getHolidays(int year);

	/**
	 Checks if the specified date is a holiday.
	 Holidays are determined by the daysOff function, which takes a date and returns a list of dates that are designated as holidays in
	 this year.
	 @param date the date to be checked
	 @return true if the date is a holiday, false otherwise
	 */
	default boolean isHoliday(LocalDate date)
	{
		return getHolidays(date.getYear()).stream().anyMatch(date::equals);
	}
}

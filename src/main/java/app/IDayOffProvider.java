package app;

import java.time.LocalDate;

@FunctionalInterface
public interface IDayOffProvider extends IWeekendProvider, IHolidayProvider
{
	/**
	 Checks if the specified date is a holiday.
	 A holiday is considered a weekend (Saturday or Sunday) or a designated holiday.
	 @param date the date to be checked
	 @return true if the date is a holiday, false otherwise
	 */
	default boolean isDayOff(LocalDate date)
	{
		return isWeekend(date)||isHoliday(date);
	}
}

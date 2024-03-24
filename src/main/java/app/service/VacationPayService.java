package app.service;

import app.model.IDayOffProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;

/**
 Service responsible for calculating vacation pay based on the average salary, the number of vacation days, and the start date. */
@Service("vacationPayService")
public class VacationPayService
{
	private IDayOffProvider dayOffProvider;

	@Autowired
	public VacationPayService(IDayOffProvider dayOffProvider)
	{
		setDayOffProvider(dayOffProvider);
	}

	/**
	 Sets the holiday provider for calculating vacation days.
	 @param provider the holiday provider implementation
	 */
	public void setDayOffProvider(IDayOffProvider provider)
	{
		this.dayOffProvider=provider;
	}

	/**
	 Calculates the vacation pay based on the average salary, the number of vacation days, and the start date.
	 <p>
	 According to Article 322 of the Labor Code of the Russian Federation, the total duration of the provided vacation should not exceed
	 six months, including the time off without pay necessary for travel to and from the location of use.
	 <p>
	 Note: The maximum number of vacation days is 186, but we use 186 because the maximum number of days in a month is 31.
	 @param averageSalary the average monthly salary of the employee during a year (12 months)
	 @param vacationDays the number of vacation days (between 1 and 186)
	 @param startDate the start date of the vacation (optional, defaults to the current date if it is equal to null)
	 @return the calculated vacation pay
	 */
	public BigDecimal calculateVacationPay(
			BigDecimal averageSalary,
			int vacationDays,
			LocalDate startDate
	)
	{
		if(vacationDays<0) return BigDecimal.ZERO;
		// If startDate is not provided, use the current date
		if(startDate==null) startDate=LocalDate.now();
		if(vacationDays>186) vacationDays=186;
		// Calculate the number of work days within the vacation period
		int workDays=calculateWorkDays(startDate,startDate.plusDays(vacationDays));
		// Calculate the length of the month based on the start date
		BigDecimal monthLength=BigDecimal.valueOf(
				YearMonth.of(startDate.getYear(),startDate.getMonth())
				         .lengthOfMonth()
		);
		// Calculate the salary per day
		BigDecimal daySalary=averageSalary.divide(monthLength,RoundingMode.HALF_UP);
		// Calculate the vacation pay by multiplying the salary per day by the number of work days
		System.out.println(workDays);
		return daySalary.multiply(BigDecimal.valueOf(workDays));
	}

	/**
	 Calculates the number of work days between the start and end dates, excluding the holidays.
	 @param startDate the start date of the period
	 @param endDate the end date of the period
	 @return the number of work days
	 */
	public int calculateWorkDays(LocalDate startDate,LocalDate endDate)
	{
		return (int)startDate.datesUntil(endDate)
		                     .filter(date->!dayOffProvider.isDayOff(date))
		                     .count();
	}
}
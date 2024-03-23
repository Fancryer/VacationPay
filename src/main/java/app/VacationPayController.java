package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
//There was a typo here in the original task description, so I didn't change it
@RequestMapping("/calculacte")
public class VacationPayController
{
	private final VacationPayService vacationPayService;

	@Autowired
	public VacationPayController(VacationPayService vacationPayService)
	{
		this.vacationPayService=vacationPayService;
	}

	@GetMapping
	public ResponseEntity<BigDecimal> calculateVacationPay(
			@RequestParam BigDecimal averageSalary,
			@RequestParam int vacationDays,
			@RequestParam(required=false)
			@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
			LocalDate startDay
	)
	{
		return ResponseEntity.ok(vacationPayService.calculateVacationPay(averageSalary,vacationDays,startDay));
	}
}

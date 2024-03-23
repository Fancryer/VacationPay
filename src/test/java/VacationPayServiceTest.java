import app.VacationPayApplication;
import app.service.VacationPayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment=SpringBootTest.WebEnvironment.MOCK,
		classes=VacationPayApplication.class
)
@AutoConfigureMockRestServiceServer
public class VacationPayServiceTest
{

	@Autowired
	private VacationPayService vacationPayService;

	@Test
	public void testCalculateVacationPay()
	{
		//test pay for two weeks
		testVacationPayCalculation(
				14,
				LocalDate.of(2023,9,1),
				new BigDecimal("60000"),
				new BigDecimal("20000")
		);
		//test pay for zero vacation days
		testVacationPayCalculation(
				0,
				LocalDate.of(2023,9,1),
				new BigDecimal("60000"),
				BigDecimal.ZERO
		);
		//test pay for negative vacation days
		testVacationPayCalculation(
				-5,
				LocalDate.of(2023,12,31),
				new BigDecimal("60000"),
				BigDecimal.ZERO
		);
		//test pay for one week containing only holidays
		testVacationPayCalculation(
				7,
				LocalDate.of(2024,1,1),
				new BigDecimal("60000"),
				BigDecimal.ZERO
		);
	}

	private void testVacationPayCalculation(
			int vacationDays,
			LocalDate startDate,
			BigDecimal averageSalary,
			BigDecimal expectedVacationPay
	)
	{
		BigDecimal actualVacationPay=vacationPayService.calculateVacationPay(averageSalary,vacationDays,startDate);
		assertThat(actualVacationPay).isEqualTo(expectedVacationPay);
	}
}
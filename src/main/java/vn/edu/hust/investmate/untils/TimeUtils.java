package vn.edu.hust.investmate.untils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class TimeUtils {
	// Chuyển đổi epoch seconds thành thời điểm bắt đầu của ngày tương ứng
	// Chuyển đổi epoch seconds thành thời điểm bắt đầu của ngày tương ứng
	public static Day getDayFromEpochSeconds(long epochSeconds) {
		Instant instant = Instant.ofEpochSecond(epochSeconds);
		LocalDate localDate = instant.atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toLocalDate();
		Instant startOfDayInstant = localDate.atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant();
		Day day = new Day();
		day.setStartOfDay(startOfDayInstant.getEpochSecond());
		day.setEndOfDay(localDate.atTime(23, 59, 59)
				.atZone(ZoneId.of("Asia/Ho_Chi_Minh"))
				.toInstant().getEpochSecond());
		return day;
	}

	// Lấy danh sách các đối tượng Day từ đầu tuần đến thời điểm truyền vào
	public static Day getDayOfStartOfWeekUntil(long epochSeconds) {
		Instant instant = Instant.ofEpochSecond(epochSeconds);
		LocalDate currentDate = instant.atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toLocalDate();
		LocalDate startOfWeek = currentDate.with(DayOfWeek.MONDAY);
		var day = getDayFromEpochSeconds(startOfWeek.atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant().getEpochSecond());
		return day;
	}

	public static Day getDayOfStartOfMonthUntil(long epochSeconds) {
		Instant instant = Instant.ofEpochSecond(epochSeconds);
		LocalDate currentMonth = instant.atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toLocalDate().withDayOfMonth(1);
		var day =  getDayFromEpochSeconds(currentMonth.atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant().getEpochSecond());
		return moveToNextMondayIfWeekend(day);
	}

	public static Day getDayBeforeTime(int daysBefore, long epochSeconds) {
		Instant instant = Instant.ofEpochSecond(epochSeconds);
		LocalDate localDate = instant.atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toLocalDate();
		LocalDate dayBefore = localDate.minusDays(daysBefore);

		Day day = new Day();
		day.setStartOfDay(dayBefore.atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant().getEpochSecond());
		day.setEndOfDay(dayBefore.atTime(23, 59, 59)
				.atZone(ZoneId.of("Asia/Ho_Chi_Minh"))
				.toInstant().getEpochSecond());
		return moveToPreviousFridayIfWeekend(day);
	}

	public static Day moveToNextMondayIfWeekend(Day day) {
		Instant instant = Instant.ofEpochSecond(day.getStartOfDay());
		LocalDate localDate = instant.atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toLocalDate();

		if (localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
			localDate = localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
			return getDayFromEpochSeconds(localDate.atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant().getEpochSecond());
		}
		return day;
	}

	public static Day moveToPreviousFridayIfWeekend(Day day) {
		Instant instant = Instant.ofEpochSecond(day.getStartOfDay());
		LocalDate localDate = instant.atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toLocalDate();
		if (localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
			localDate = localDate.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
			return getDayFromEpochSeconds(localDate.atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant().getEpochSecond());
		}
		return day;
	}

	public static int getCurrentYear() {
		return LocalDate.now().getYear();
	}

	// Phương thức để lấy quý hiện tại
	public static int getCurrentQuarter() {
		int currentMonth = LocalDate.now().getMonthValue();
		return (currentMonth - 1) / 3 + 1;
	}

	public static SpecificTime getPreviousQuarterEpochTime() {
		LocalDate now = LocalDate.now();

		LocalDate startOfQuarter;
		LocalDate endOfQuarter;

		int currentQuarter = (now.getMonthValue() - 1) / 3 + 1;
		switch (currentQuarter) {
			case 1: // Q1, quý trước là Q4 của năm trước
				startOfQuarter = LocalDate.of(now.getYear() - 1, 10, 1);
				endOfQuarter = LocalDate.of(now.getYear() - 1, 12, 31);
				break;
			case 2: // Q2, quý trước là Q1
				startOfQuarter = LocalDate.of(now.getYear(), 1, 1);
				endOfQuarter = LocalDate.of(now.getYear(), 3, 31);
				break;
			case 3: // Q3, quý trước là Q2
				startOfQuarter = LocalDate.of(now.getYear(), 4, 1);
				endOfQuarter = LocalDate.of(now.getYear(), 6, 30);
				break;
			case 4: // Q4, quý trước là Q3
				startOfQuarter = LocalDate.of(now.getYear(), 7, 1);
				endOfQuarter = LocalDate.of(now.getYear(), 9, 30);
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + currentQuarter);
		}

		ZonedDateTime startDateTime = startOfQuarter.atStartOfDay(ZoneId.systemDefault());
		ZonedDateTime endDateTime = endOfQuarter.atTime(23, 59, 59).atZone(ZoneId.systemDefault());

		SpecificTime specificTime = new SpecificTime();
		specificTime.setStartTime(startDateTime.toEpochSecond());
		specificTime.setEndTime(endDateTime.toEpochSecond());

		return specificTime;
	}


	@Data
	@NoArgsConstructor
	public static class Day {
		private Long startOfDay;
		private Long endOfDay;
	}


	@Data
	@NoArgsConstructor
	public static class SpecificTime {
		private Long startTime;
		private Long endTime;
	}
}

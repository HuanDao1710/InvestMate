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


	@Data
	@NoArgsConstructor
	public static class Day {
		private Long startOfDay;
		private Long endOfDay;
	}
}

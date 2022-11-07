package utils;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
	
	public static LocalDateTime stringToDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
//				"yyyy-MM-ddHH:mm:ss a");
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		return dateTime;
	}

	public static String slugify(final String s) {
		final String intermediateResult = Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")
				.replaceAll("[^-_a-zA-Z0-9]", "-").replaceAll("\\s+", "-").replaceAll("[-]+", "-").replaceAll("^-", "")
				.replaceAll("-$", "").toLowerCase();
		return intermediateResult.substring(0, Math.min(500, intermediateResult.length()));
	}
}

package fullstack.api.vendas.curso.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.util.StringUtils;

public class DateUtils {

	private static final String PADRAO_FORMATACAO_DATA = "dd/MM/yyyy";
	public static final Date DATA_INICIO_PADRAO;
	public static final Date DATA_FIM_PADRAO;

	static {
		DATA_INICIO_PADRAO = DateUtils.fromStringDate("01/01/1970");
		DATA_FIM_PADRAO = DateUtils
				.fromStringDate(LocalDate.now().format(DateTimeFormatter.ofPattern(PADRAO_FORMATACAO_DATA)));
	}

	public static Date fromStringDate(String dateString) {
		if (StringUtils.hasText(dateString)) {
			SimpleDateFormat formato = new SimpleDateFormat(PADRAO_FORMATACAO_DATA);
			try {
				return formato.parse(dateString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}

package br.com.alura.forum.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataUtil {

	public static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	public static final String dataMaxima() {
		return LocalDate.MAX.format(FORMATADOR);
	}

	public static final String dataMinima() {
		return LocalDate.MIN.format(FORMATADOR);
	}

	
}

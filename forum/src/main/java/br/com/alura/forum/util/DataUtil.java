package br.com.alura.forum.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataUtil {

	public static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	public static final String dataMaxima() {
		return LocalDate.now().format(FORMATADOR);
	}

	public static final String dataMinima() {
		return LocalDate.of(1000, 01, 01).format(FORMATADOR);
	}

	
}

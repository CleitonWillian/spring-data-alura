package br.com.alura.forum.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataUtil {
	
	 private DataUtil() {
		    throw new IllegalStateException("Classe de utilidade");
		  }

	public static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public static final LocalDate validaERetornaDataMaximaFormatada(String data) {
		return validaData(data) ? LocalDate.parse(data, FORMATADOR) : LocalDate.parse(getDataMaxima(), FORMATADOR);
	}

	
	public static final LocalDate validaERetornaDataMinimaFormatada(String data) {
		return validaData(data) ? LocalDate.parse(data, FORMATADOR) : LocalDate.parse(getDataMinima(), FORMATADOR);
	}
	
	private static boolean validaData(String data) {
		return data != null;
	}

	private static String getDataMaxima() {
		return LocalDate.now().format(FORMATADOR);
	}

	public static final String getDataMinima() {
		return LocalDate.of(1000, 01, 01).format(FORMATADOR);
	}

}

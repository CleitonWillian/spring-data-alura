package br.com.alura.forum.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.alura.forum.entity.TopicoSituacao;

@Converter
public class TopicSituacaoConverter implements AttributeConverter<TopicoSituacao, String> {

	@Override
	public String convertToDatabaseColumn(TopicoSituacao topicoSituacao) {
		return topicoSituacao.getSituacao();
	}

	@Override
	public TopicoSituacao convertToEntityAttribute(String situacao) {
		return TopicoSituacao.fromBancoDeDados(situacao);

	}

}

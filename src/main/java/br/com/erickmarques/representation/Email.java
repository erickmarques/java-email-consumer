package br.com.erickmarques.representation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
	private String to;
	private String from;
	private String subject;
	private String body;
}

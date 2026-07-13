package it.uniroma3.siw.dto;

import java.time.LocalDateTime;

public class ChatPreviewDTO {

	private Long annuncioId;
	private String annuncioTitolo;

	private Long altroUtenteId;
	private String altroUtenteNome;

	private String ultimoMessaggio;
	private LocalDateTime dataUltimoMessaggio;

	public ChatPreviewDTO(Long annuncioId, String annuncioTitolo, Long altroUtenteId, String altroUtenteNome,
			String ultimoMessaggio, LocalDateTime dataUltimoMessaggio) {
		this.annuncioId = annuncioId;
		this.annuncioTitolo = annuncioTitolo;
		this.altroUtenteId = altroUtenteId;
		this.altroUtenteNome = altroUtenteNome;
		this.ultimoMessaggio = ultimoMessaggio;
		this.dataUltimoMessaggio = dataUltimoMessaggio;
	}

	public Long getAnnuncioId() {
		return annuncioId;
	}

	public String getAnnuncioTitolo() {
		return annuncioTitolo;
	}

	public Long getAltroUtenteId() {
		return altroUtenteId;
	}

	public String getAltroUtenteNome() {
		return altroUtenteNome;
	}

	public String getUltimoMessaggio() {
		return ultimoMessaggio;
	}

	public LocalDateTime getDataUltimoMessaggio() {
		return dataUltimoMessaggio;
	}
}
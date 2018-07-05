package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {

	private List<Sessao> sessoesDaSala;
	
	public GerenciadorDeSessao(List<Sessao> sessoesDaSala){
		this.sessoesDaSala = sessoesDaSala;
	}
	
	private boolean isConflitante(Sessao sessaoExistente, Sessao sessaoNova){
		LocalDate hoje = LocalDate.now();
		LocalDateTime horarioSessaoExistente = sessaoExistente.getHorario().atDate(hoje);
		LocalDateTime horarioSessaoNova = sessaoNova.getHorario().atDate(hoje);
		
		boolean terminaAntes = sessaoNova.getHorarioTermino().isBefore(sessaoExistente.getHorario());
		boolean comecaDepois = sessaoExistente.getHorarioTermino().isBefore(sessaoNova.getHorario());
		
		if (terminaAntes || comecaDepois) {
			return false;
		}
		return true;
		
	}
	
	public boolean cabe(Sessao sessaoNova){
		return sessoesDaSala.stream().noneMatch(sessaoExistente -> isConflitante(sessaoExistente, sessaoNova));
	}
	
}

package br.com.caelum.ingresso.validacao;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessaoTest {
	private Filme filme;
	private Sala  sala;
	private Sessao sessaoDasDez;
	private Sessao sessaoDasTreze;
	private Sessao sessaoDasDezoito;
	
	@Before
	public void preparaSessoes(){
		this.filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI");
		this.sala = new Sala("Sala 3D");
		this.sessaoDasDez = new Sessao(LocalTime.parse("10:00"), filme, sala);
		this.sessaoDasTreze = new Sessao(LocalTime.parse("13:00"), filme, sala);
		this.sessaoDasDezoito = new Sessao(LocalTime.parse("18:00"), filme, sala);
				
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessaoNoMesmoHorario(){
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciador.cabe(sessaoDasDez));
	}
}

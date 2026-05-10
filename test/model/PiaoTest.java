package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Piao;
import model.Suspeito;

public class PiaoTest {

	private Piao piao;
	private Suspeito suspeitoTeste;

	@Before
	public void setUp() {
		suspeitoTeste = new Suspeito(); 
		piao = new Piao(suspeitoTeste, 1); 
	}

	@Test
	public void testConstrutorEGetters() {
		assertEquals("O personagem retornado deve ser exatamente o passado no construtor.", suspeitoTeste, piao.getPersonagem());
		// Verifica se a casa inicial foi definida corretamente
		assertEquals("O ID da casa inicial deve ser 1.", 1, piao.getIdCasaAtual());
	}

	@Test
	public void testSetIdCasaAtual() {
		piao.setIdCasaAtual(15);
		// Verifica se o valor foi atualizado corretamente
		assertEquals("O ID da casa atual deveria ter sido atualizado para 15.", 15, piao.getIdCasaAtual());
	}
}
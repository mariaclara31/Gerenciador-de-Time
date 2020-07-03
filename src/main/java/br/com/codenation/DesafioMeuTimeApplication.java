package br.com.codenation;

import br.com.codenation.model.Time;
import br.com.codenation.model.Jogador;
import br.com.codenation.exceptions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private static List<Time> times = new ArrayList<>();
	private static List<Jogador> jogadores = new ArrayList<>();

	private Boolean existeTime (Long id) {
		return times.stream().filter(t -> t.getId() == id).findAny().isPresent();
	}

	private Boolean existeJogador (Long id) {
		return jogadores.stream().filter(j -> j.getId() == id).findAny().isPresent();
	}

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        if (existeTime(id)) throw new IdentificadorUtilizadoException();

        times.add(new Time (id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
	    if(!existeTime(idTime)) throw new TimeNaoEncontradoException();

		if(existeJogador(id)) throw new IdentificadorUtilizadoException();

	    Jogador novoJogador = new Jogador(id,idTime, nome, dataNascimento, nivelHabilidade, salario);
	    jogadores.add(novoJogador);
	    /*timeOpt.get().addJogador(novoJogador);*/
	}

	private Jogador obterJogador(Long idJogador) {
		return jogadores.stream().filter(j -> j.getId() == idJogador).findFirst().orElse(null);
	}
	private Time obterTime(Long idTime) {
		return times.stream().filter(j -> j.getId() == idTime).findFirst().orElse(null);
	}

	public void definirCapitao(Long idJogador) {
		if(!existeJogador(idJogador)) throw new JogadorNaoEncontradoException();

		Jogador jogador = obterJogador(idJogador);
		Time time = obterTime(jogador.getIdTime());
		time.setCapitao(jogador);
	}

	public Long buscarCapitaoDoTime(Long idTime) {
		if(!existeTime(idTime)) throw new TimeNaoEncontradoException();
		Time time = obterTime(idTime);

		if (time.getCapitao() == null) throw new CapitaoNaoInformadoException();

		return time.getCapitao().getId();
	}

	public String buscarNomeJogador(Long idJogador) {
            if (!existeJogador(idJogador)) throw new JogadorNaoEncontradoException();

			return obterJogador(idJogador).getNome();
	}

	public String buscarNomeTime(Long idTime) {
		if (!existeTime(idTime)) throw new TimeNaoEncontradoException();

		return obterTime(idTime).getNome();
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		if(!existeTime(idTime)) throw new TimeNaoEncontradoException();

		return jogadores.stream()
				.filter(j -> j.getIdTime() ==  idTime)
				.collect(Collectors.toList())
				.stream()
				.map(Jogador::getId)
				.collect(Collectors.toList());
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
		if(!existeTime(idTime)) throw new TimeNaoEncontradoException();

		return jogadores.stream()
				.filter(j -> j.getIdTime() ==  idTime)
				.sorted(comparing(Jogador::getNivelHabilidade).reversed())
				.map(Jogador::getId)
				.findFirst().get();
    }

	public Long buscarJogadorMaisVelho(Long idTime) {
		if(!existeTime(idTime)) throw new TimeNaoEncontradoException();

		return jogadores.stream()
				.filter(j -> j.getIdTime() ==  idTime)
				.sorted(comparing(Jogador::getDataNascimento))
				.map(Jogador::getId)
				.findFirst().get();
	}

	public List<Long> buscarTimes() {
		return times.stream().map(Time::getId).collect(Collectors.toList());
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		if(!existeTime(idTime)) throw new TimeNaoEncontradoException();

		return jogadores.stream()
				.filter(j -> j.getIdTime() ==  idTime)
				.sorted(comparing(Jogador::getSalario).reversed())
				.map(Jogador::getId)
				.findFirst().get();
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		if (!existeJogador(idJogador)) throw new JogadorNaoEncontradoException();

		return obterJogador(idJogador).getSalario();
	}

	public List<Long> buscarTopJogadores(Integer top) {

		return jogadores.stream()
                .sorted(comparing(Jogador::getNivelHabilidade).reversed())
				.limit(top)
				.collect(Collectors.toList())
				.stream()
				.map(Jogador::getId)
				.collect(Collectors.toList());
	}


}

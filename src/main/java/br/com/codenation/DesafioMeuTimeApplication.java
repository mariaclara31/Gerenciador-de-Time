package br.com.codenation;

import  br.com.codenation.desafio.app.MeuTimeInterface;
import  br.com.codenation.desafio.model.Jogador;
import  br.com.codenation.desafio.model.Time;
import  br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import  br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import  br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import  br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private List<Time> times = new ArrayList<>();
	private List<Jogador> jogadores = new ArrayList<>();

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        if (times.stream().filter(t -> Objects.equals(t.getId(), id)).findAny().isPresent()) {
            throw new IdentificadorUtilizadoException();
        }
        times.add(new Time (id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
	    if(jogadores.stream().filter(j -> Objects.equals(j.getId(), id)).findAny().isPresent()) {
	        throw new IdentificadorUtilizadoException();
        }
	    Optional<Time> timeOpt = times.stream().filter(t -> Objects.equals(t.getId(), idTime)).findFirst();
	    if(!timeOpt.isPresent()) {
	        throw new TimeNaoEncontradoException();
        }
	    Jogador novoJogador = new jogador(id,idTime, nome, dataNascimento, nivelHabilidade, salario);
	    jogadores.add(novoJogador);
	    timeOpt.get().addJogador(novoJogador);
	}

	public void definirCapitao(Long idJogador) {
		Optional<Jogador> jogadorOpt = jogadores.stream().filter(j -> Objects.equals(j.getId(), idJogador)).findAny();
		if(!jogadorOpt.isPresent()) {
			throw new JogadorNaoEncontradoException();
		}
		Jogador jogador = jogadorOpt.get();
		Time time = times.stream().filter(t -> Objects.equals(t.getId(). jogador.getIdTime())).findAny().get();
		time.setCapitao(jogador);
	}

	public Long buscarCapitaoDoTime(Long idTime) {
		Optional<Time> timeOpt = times.stream().filter(t -> Objects.equals(t.getId(), idTime)).findAny();
		if (!timeOpt.isPresent()) {
			throw new TimeNaoEncontradoException();
		}
		Time time = timeOpt.get();
		if (time.getCapitao() == null) {
			throw new CapitaoNaoInformadoException();
		}
		return time.getCapitao().getId();
	}

	public String buscarNomeJogador(Long idJogador) {
            Jogador jogador = this.getJogadorById(idJogador);
		if (jogador != null) {
			return jogador.getNome();
		}
		throw new JogadorNaoEncontradoException();
	}

	public String buscarNomeTime(Long idTime) {
		Optional<Time> timeOpt = times.stream().filter(t -> Objects.equals(t.getId(), idTime)).findAny();
		if (!timeOpt.isPresent()) {
			throw new TimeNaoEncontradoException();
		}
		return timeOpt.get().getNome();
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		Optional<Time> timeOpt = times.stream().filter(t -> Objects.equals(t.getId(), idTime)).findAny();
		if (!timeOpt.isPresent()) {
			throw new TimeNaoEncontradoException();
		}
		return timeOpt.get().getJogadorList().stream().sorted(Comparator.comparing(Jogador::getId))
				.map(Jogador::getId).collect(Collectors.toList());
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
        Optional<Time> timeOpt = times.stream().filter(t -> Objects.equals(t.getId(), idTime)).findAny();
        if (!timeOpt.isPresent()) {
            throw new TimeNaoEncontradoException();
        }
        return timeOpt.get().getJogadores().stream().sorted(Comparator.comparing(Jogador::getId))
                .max(Comparator.comparing(Jogador::getNivelHabilidade)).get().getId();
    }

	public Long buscarJogadorMaisVelho(Long idTime) {
		Optional<Time> timeOpt = times.stream().filter(t -> Objects.equals(t.getId(), idTime)).findAny();
		if (!timeOpt.isPresent()) {
			throw new TimeNaoEncontradoException();
		}
		return timeOpt.get().getJogadores().stream().min(Comparator.comparing(Jogador::getDataNascimento).thenComparing(Jogador::getId)).get().getId();
	}

	public List<Long> buscarTimes() {
		List<Long> times = new ArrayList<>();
		if (!this.times.isEmpty()) {
			idTimes = this.times.stream().sorted(Comparator.comparing(Time::getId))
                    .map(Time::getId).collect(Collectors.toList());
		}
		return idTimes;
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		Optional<Time> timeOpt = times.stream().filter(t -> Objects.equals(t.getId(), idTime)).findFirst();
		if (!timeOpt.isPresent) {
			throw new TimeNaoEncontradoException();
		}
		return timeOpt.get().getJogadores().stream().filter(t -> Objects.equals(t.getId(), idJogador)).getId();
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		Optional<Jogador> jogadorOpt = jogadores.stream().filter(t -> Objects.equals(t.getId(), idJogador)).findAny();
		if (!jogadorOpt.isPresent()) {
			throw new JogadorNaoEncontradoException();
		}
		return jogadorOpt.get().getSalario();
	}

	public List<Long> buscarTopJogadores(Integer top) {
		if (this.jogadores.isEmpty()) {
		    return new ArrayList<>();
			}
		List<Long> topJogadores = this.jogadores.stream()
                .sorted(Comparator.comparing(Jogador::getNivelHabilidade).revesed()
                .thenComparing(Jogador::getId).map(Jogador::getId).collect(Collectors.toList()));
		return topJogadores.subList(0, top);
	}

	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Optional<Time> timeDaCasaOpt = this.times.stream().filter(t -> Objects.equals(t.getId(), timeDaCasa)).findAny();
        Optional<Time> timeDeForaOpt = this.times.stream().filter(t -> Objects.equals(t.getId(), timeDeFora)).findAny();
		if(!timeDaCasaOpt.isPresent() || !timeDeForaOpt.isPresent()) {
			throw new TimeNaoEncontradoException();
		}
		Time tTimeDaCasa = timeDaCasaOpt.get();
		Time tTimeDeFora = timeDeForaOpt.get();
		if (tTimeCasa.getCorUniformePrincipal().equals(tTimeFora.getCorUniformePrincipal())) {
			return tTimeFora.getCorUniformeSecundario();
		} else {
			return tTimeFora.getCorUniformePrincipal();
		}
	}
}

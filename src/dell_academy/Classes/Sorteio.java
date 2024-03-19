/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dell_academy.Classes;

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Sorteio {

  private static String VALOR_SORTEIO = "350.000.000";

  private List<Integer> numerosSorteados;
  private List<Aposta> apostasArmazenadas;
  private Map<String, Aposta> mapaNomeApostasVencedoras;
  private boolean vencedor = false;
  private int quantidadeDeVencedores = 0;
  private int novoValorSorteio;

  public int getNovoValorSorteio() {
    return novoValorSorteio;
  }

  public Sorteio(Operador operador) {
    this.numerosSorteados = new ArrayList<>();
    this.apostasArmazenadas = operador.getApostasRealizadas();
    this.mapaNomeApostasVencedoras = new HashMap<>();
  }

  public String getNumerosSorteados() {
    StringBuilder builder = new StringBuilder();
    builder.append("");
    for (int numero : numerosSorteados) {
      builder.append("  ").append(numero);
    }
    return builder.toString();
  }

  public Map<Integer, Integer> calcularFrequencia() {
    // Mapa para armazenar a frequência de cada número apostado
    Map<Integer, Integer> frequenciaNumeros = new HashMap<>();

    // Iterar sobre todas as apostas armazenadas
    for (Aposta aposta : apostasArmazenadas) {
        int[] numerosApostados = aposta.getNumerosApostados();
        for (int numero : numerosApostados) {
            // Atualizar a frequência do número apostado
            frequenciaNumeros.put(numero, frequenciaNumeros.getOrDefault(numero, 0) + 1);
        }
    }

    return frequenciaNumeros;
}

  public void realizarSorteio() {
    Random random = new Random();
    numerosSorteados.clear();

    for (int i = 0; i < 5; i++) {
      int numeroGerado;
      do {
        numeroGerado = random.nextInt(50) + 1;
      } while (numerosSorteados.contains(numeroGerado)); // Verifica se o número já foi sorteado
      numerosSorteados.add(numeroGerado);
    }
  }

  public String apurarNumeros() {
    int numeroRodadas = 1;
    int maxRodadas = 25; // Ajuste o número máximo de rodadas conforme necessário

    StringBuilder resultados = new StringBuilder();

    Random random = new Random();

    do {
        resultados.append("RODADA ").append(numeroRodadas).append(":\n");

        // Sorteia e adiciona um novo número aos números sorteados
        int novoNumero;
        do {
            novoNumero = random.nextInt(50) + 1;
        } while (numerosSorteados.contains(novoNumero));
        numerosSorteados.add(novoNumero);

        // Adiciona os números sorteados ao resultado
        resultados.append("");
        for (int i = 0; i < numerosSorteados.size(); i++) {
            resultados.append(numerosSorteados.get(i)).append("     ");
            if ((i + 1) % 5 == 0) {
                resultados.append("\n");
            }
        }
        resultados.append("\n\n");

        // Verifica todas as apostas
        for (Aposta aposta : apostasArmazenadas) {
            int[] numerosApostados = aposta.getNumerosApostados();
            if (verificarAposta(numerosApostados, numerosSorteados)) {
                // Se houver uma aposta vencedora, adiciona ao mapa de vencedores
                mapaNomeApostasVencedoras.put(aposta.getPessoaApostador().getNome(), aposta);
                quantidadeDeVencedores++;
            }
        }

        // Se houver pelo menos um vencedor, define vencedor como true para encerrar o loop
        if (!mapaNomeApostasVencedoras.isEmpty()) {
            vencedor = true;
        }

        // Se ainda não houve vencedor e não ultrapassou o número máximo de rodadas, continua sorteando
        if (!vencedor && numeroRodadas < maxRodadas) {
            aumentarNumerosSorteados(numerosSorteados);
        }

        numeroRodadas++;
    } while (numeroRodadas < maxRodadas); // Corrigido para < maxRodadas

    // Se não houver vencedor após o número máximo de rodadas, aumenta o prêmio
    if (!vencedor) {
        aumentarNumerosSorteados(numerosSorteados);
        float novoValorSorteio = Float.parseFloat(VALOR_SORTEIO.replace(".", ""));
        novoValorSorteio *= 1.03;
        resultados.append("Novo valor do sorteio: ").append(novoValorSorteio).append("\n");
    }

    return resultados.toString();
}

  public boolean verificarAposta(int[] numerosApostados, List<Integer> numerosSorteados) {
    for (int numero : numerosApostados) {
      if (!numerosSorteados.contains(numero)) {
        return false;
      }
    }
    return true;
  }

  public int getQtdVencedores() {
    return quantidadeDeVencedores;
  }

  public void aumentarNumerosSorteados(List<Integer> numerosSorteados) {
    Random random = new Random();
    int novoNumero;

    do {
      novoNumero = random.nextInt(50) + 1;
    } while (numerosSorteados.contains(novoNumero));

    numerosSorteados.add(novoNumero);
  }

 public String entregarPremiacao() {
    StringBuilder resultadoPremiacao = new StringBuilder();

    if (!mapaNomeApostasVencedoras.isEmpty()) {
        resultadoPremiacao.append("Vencedor(es): \n");
        
        // Criar um TreeMap para armazenar as entradas do mapa ordenadas por chave (nome do apostador)
        Map<String, Aposta> mapaOrdenado = new TreeMap<>(mapaNomeApostasVencedoras);
        
        // Iterar sobre as entradas do mapa ordenado
        for (Map.Entry<String, Aposta> entry : mapaOrdenado.entrySet()) {
            Aposta apostaVencedora = entry.getValue();
            resultadoPremiacao.append("Nome do Apostador: ").append(apostaVencedora.getPessoaApostador().getNome())
                    .append("\n");
            resultadoPremiacao.append("CPF do Apostador: ").append(apostaVencedora.getPessoaApostador().getCpf())
                    .append("\n");
            resultadoPremiacao.append("Números Apostados: ");
            for (int numero : apostaVencedora.getNumerosApostados()) {
                resultadoPremiacao.append(numero).append(" ");
            }
            resultadoPremiacao.append("\n---------------------------------------------\n");
        }
    } else {
        resultadoPremiacao.append("Não houve vencedores nesta rodada.\n");
    }

    return resultadoPremiacao.toString();
}

public Map<String, Aposta> getMapaNomesOrdenados() {
  // Criar um TreeMap para armazenar as entradas do mapa ordenadas por chave (nome do apostador)
  Map<String, Aposta> mapaOrdenado = new TreeMap<>(mapaNomeApostasVencedoras);
  
  return mapaOrdenado;
}
}

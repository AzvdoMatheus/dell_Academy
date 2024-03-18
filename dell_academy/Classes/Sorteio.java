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

public class Sorteio {

  private static String VALOR_SORTEIO = "350.850.100";

  private List<Integer> numerosSorteados;
  private List<Aposta> apostasArmazenadas;
  private Map<String, Aposta> mapaNomeApostasVencedoras;
  private boolean vencedor = false;

  Sorteio(Operador operador) {
    this.numerosSorteados = new ArrayList<>();
    this.apostasArmazenadas = operador.getApostasRealizadas();
    this.mapaNomeApostasVencedoras = new HashMap<>();
  }

  // ARRUMAR LOGICA
  public void realizarSorteio() {
    Random random = new Random();

    for (Integer numeroGerado : numerosSorteados) {
      numeroGerado = random.nextInt() % 50 + 1;
      if (!numerosSorteados.contains(numeroGerado))
        numerosSorteados.add(numeroGerado);
    }
    apurarNumeros();
  }

  public void apurarNumeros() {

    int numeroRodadas = 0;

    do {
      for (Aposta aposta : apostasArmazenadas) {
        int[] numerosApostados = aposta.getNumerosApostados();
        if (verificarAposta(numerosApostados, numerosSorteados)) {
          vencedor = true;
          mapaNomeApostasVencedoras.put(aposta.getPessoaApostador().getNome(), aposta);
          break;
        }
      }
      numeroRodadas++;
    } while (!vencedor && numeroRodadas < 26);

    if (!vencedor) {
      System.out.println("Nenhum vencedor nesta rodada. Aumentando prêmio em 3%.");
      System.out.println("Sorteando mais um valor para adicionar aos numeros...");
      aumentarNumerosSorteados(numerosSorteados);
      float novoValorSorteio = Float.parseFloat(VALOR_SORTEIO.replace(".", ""));
      novoValorSorteio *= 1.03;
      System.out.println("Novo valor do sorteio: " + novoValorSorteio);
    }
  }

  public boolean verificarAposta(int[] numerosApostados, List<Integer> numerosSorteados) {
    for (int numero : numerosApostados) {
        if (!numerosSorteados.contains(numero)) {
            return false;
        }
    }
    return true;
  }

  public void aumentarNumerosSorteados(List<Integer> numerosSorteados) {
    Random random = new Random();
    int novoNumero;

    do {
      novoNumero = random.nextInt(50) + 1;
    } while (numerosSorteados.contains(novoNumero));

    numerosSorteados.add(novoNumero);
  }

  public void entregarPremiacao() {
    if (!mapaNomeApostasVencedoras.isEmpty()) {
        System.out.println("Vencedor(es): ");
        for (Map.Entry<String, Aposta> entry : mapaNomeApostasVencedoras.entrySet()) {
            Aposta apostaVencedora = entry.getValue();
            System.out.println("Nome do Apostador: " + apostaVencedora.getPessoaApostador().getNome());
            System.out.println("CPF do Apostador: " + apostaVencedora.getPessoaApostador().getCpf());
            System.out.println("Números Apostados: ");
            for (int numero : apostaVencedora.getNumerosApostados()) {
                System.out.print(numero + " ");
            }
            System.out.println();
            System.out.println("---------------------------------------------");
        }
    } else {
        System.out.println("Não houve vencedores nesta rodada.");
    }
}

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dell_academy.Classes;

/**
 *
 * @author User
 */
public class Aposta {

  private static int registro = 1000;
  private final int registroFinal;
  private int[] numerosApostados;
  Pessoa pessoaApostador;

  public Aposta(Pessoa apostador, int[] numerosDaAposta) {
    this.registroFinal = registro;
    registro++;
    numerosApostados = new int[5];
    this.pessoaApostador = apostador;
    this.numerosApostados = numerosDaAposta;
  }

  public int getregistro() {
    return registro;
  }

  public String getNumerosApostados(Pessoa p) {
    StringBuilder vetor = new StringBuilder();

    // Itera sobre os números apostados na aposta atual
    for (int i = 0; i < numerosApostados.length; i++) {
        vetor.append(numerosApostados[i]);

        // Adiciona vírgula e espaço se não for o último número da aposta
        if (i < numerosApostados.length - 1) {
            vetor.append(", ");
        }
    }

    return vetor.toString();
  } 

  public Pessoa getPessoaApostador() {
    return pessoaApostador;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Registro Aposta: ").append(registroFinal).append(", ");
    sb.append("Números Apostados: ");
    for (int numero : numerosApostados) {
      sb.append(numero).append(" ");
    }
    sb.append(", ");
    sb.append("Apostador: ").append(pessoaApostador);
    return sb.toString();
  }
}


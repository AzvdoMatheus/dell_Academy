/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dell_academy.Classes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Pessoa {

  private String nome;
  private String cpf;
  private List<Aposta> apostasFeitas;

  public Pessoa(String nome, String cpf) {
    this.nome = nome;
    this.cpf = cpf;
    apostasFeitas = new ArrayList<>();
  }

  public String getNome() {
    return nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void adicionarAposta(Aposta aposta) {
    apostasFeitas.add(aposta);
}


public List<Aposta> getApostasFeitas(Pessoa pessoa) {
  List<Aposta> apostas = new ArrayList<>();
  for (Aposta aposta : apostasFeitas) {
      if (aposta.getPessoaApostador().equals(pessoa)) {
          apostas.add(aposta);
      }
  }
  return apostas;
}

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Nome: ").append(nome).append(", ");
    sb.append("CPF: ").append(cpf);
    return sb.toString();
  }

}

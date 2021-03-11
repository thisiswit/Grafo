// Igor Pinheiro e João Wictor

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

class Arquivo {
  public static void main(String[] args) throws IOException {
    FileWriter arq = new FileWriter("arquivo.txt");
    PrintWriter gravarArq = new PrintWriter(arq);

    String direcionado = "0";
    String ponderado = "1";
    int vertices = 10;
    int arestas = 10;
    int peso = 10; //limitar o peso

    gravarArq.printf(direcionado + "\n" + ponderado +"\n");
    
    for(int i = 1; i <= vertices; i++)
      gravarArq.printf(i + "\n");
    gravarArq.printf("-" + "\n");

    Random gerador = new Random();

    if(ponderado.equals("1")){
      for(int i = 0; i < arestas; i++) {
        gravarArq.printf(Integer.toString(1 + gerador.nextInt(vertices + 1)) + "," + 
        Integer.toString(1 + gerador.nextInt(vertices + 1)) + "," + Integer.toString(gerador.nextInt(peso + 1)) + "\n");
      }
    }
    
    else{
      for(int i = 0; i < arestas; i++) {
        gravarArq.printf(Integer.toString(1 + gerador.nextInt(vertices + 1)) + ","+ 
        Integer.toString(1 + gerador.nextInt(vertices + 1)) + "\n");
      }
    }
    arq.close();

    System.out.println("Arquivo gerado!");

  }
}
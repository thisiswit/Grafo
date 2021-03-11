// Este é um exemplo simples de implementação de grafo representado por lista
// de adjacências

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Grafo {
    public class Vertice {
        String nome;
        List<Aresta> adj;

        Vertice(String nome) {
            this.nome = nome;
            this.adj = new ArrayList<Aresta>();
        }

        void addAdj(Aresta e) {
            adj.add(e);
        }
    }

    public class Aresta {
        Vertice origem;
        Vertice destino;
        double valor;
        // Não Ponderado
        Aresta(Vertice origem, Vertice destino) {
            this.origem = origem;
            this.destino = destino;
        }
        //Ponderado
        Aresta(Vertice origem, Vertice destino, double valor) {
            this.origem = origem;
            this.destino = destino;
            this.valor = valor;
        }
    }

    boolean pond;
    List<Vertice> vertices;
    List<Aresta> arestas;

    public Grafo() {
        vertices = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();
    }

    Vertice addVertice(String nome) {
        Vertice v = new Vertice(nome);
        vertices.add(v);
        return v;
    }

    Vertice readVertice(String nome) {
        for(Vertice p: vertices){
            if(p.nome.equals(nome))
                return p;
        }
        return null;
    }
    
    Aresta addAresta(Vertice origem, Vertice destino) {
        if(origem == null || destino == null)
          return null;
        Aresta e = new Aresta(origem, destino);

        for(Aresta a: arestas){
          if(a.origem == e.origem && a.destino == e.destino)
            return null;
        }
        
        origem.addAdj(e);
        arestas.add(e);
        return e;
    }

    Aresta addAresta(Vertice origem, Vertice destino, double valor) {
      if(origem == null || destino == null)
        return null;
      Aresta e = new Aresta(origem, destino);

      for(Aresta a: arestas){
        if(a.origem == e.origem && a.destino == e.destino)
          return null;
      }

      e.valor = valor;  
      origem.addAdj(e);
      arestas.add(e);
      return e;
    }

    public String toString() {
        String r = "";
        for (Vertice u : vertices) {
            r += u.nome + " -> ";
            int i = 0;
            for (Aresta e : u.adj) {
                Vertice v = e.destino;
                     
                if(pond == true){
                  if(i == u.adj.size() - 1)
                    r += (v.nome + " (" + e.valor + ")");
                  else
                    r += (v.nome + " (" + e.valor + "), ");
                }  
                else{
                  if(i == u.adj.size() - 1)
                    r += v.nome;
                  else 
                    r += v.nome + ",";
                }
                i++;                 
            }
            r += "\n";
        }
        return r;
    }
    public static void main(String[] args) throws IOException {
        FileReader arq = new FileReader("arquivo.txt");
        BufferedReader lerArq = new BufferedReader(arq);

        String direcionado = lerArq.readLine();
        String ponderado = lerArq.readLine();
        

        System.out.println(direcionado);
        System.out.println(ponderado);

        Grafo g = new Grafo();

        String ler = "";
        while(!ler.equals("-")) {
            ler = lerArq.readLine();

            if(ler.equals("-")) //Famosa Gambiarra
                break;
            g.addVertice(ler);
        }

        String arestas = "";
        if(direcionado.equals("1"))
          System.out.print("Grafo Direcionado e ");
        else
          System.out.print("Grafo não Direcionado e ");
        if(ponderado.equals("1"))
          System.out.println("Ponderado:");
        else
          System.out.println("não Ponderado:");
        
        while(!arestas.equals(null)){
          arestas = lerArq.readLine();
          if(arestas == null){ //Famosa Gambiarra
            break;
          }
          String no[] = arestas.split(",");
          String origem = no[0];
          String destino = no[1];
          
        
          Vertice v_origem = g.readVertice(origem);
          Vertice v_destino = g.readVertice(destino);
          
           
          if(ponderado.equals("1")){
            double valor = Double.parseDouble(no[2]);
            g.pond = true;
            if(g.addAresta(v_origem, v_destino, valor) == null)
              System.out.print("Não é possível gerar aresta ou ela já existe! (" + origem + " -> " + destino + ")\n");
            if(direcionado.equals("0"))
              g.addAresta(v_destino, v_origem, valor);
          }
          else{
            if(g.addAresta(v_origem, v_destino) == null)
              System.out.print("Não é possível gerar aresta ou ela já existe! (" + origem + " -> " + destino + ")\n");
            if(direcionado.equals("0"))
              g.addAresta(v_destino, v_origem);
          }

            
        }

        arq.close();
        System.out.println(g);
    }
}


package maze;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.*;

public class Maze {
    private int x;
    private int y;
    private Cell[][] maze;
    HashMap<Cell, Cell> parent;
    ArrayList <Estado>list= new ArrayList<>();
    
    int eixo_X=0;
    int eixo_Y=0;
    Stack pilha = new Stack();
    
   
    public Maze(){
    
    }
    
    public Maze(int x, int y) {
        this.x = x;
        this.y = y;
        
        parent = new HashMap<>();
        maze = new Cell[x][y];
        for (int i=0;i<x;i++) {
            maze[i] = new Cell[y];
            for (int j=0;j<y;j++) {
                maze[i][j] = new Cell(i,j);
            }
        }
        init();
        generate();
    }

    private void init() {

    }


    private Set<Cell> visited;
    // generate the maze
    private void generate() {
        System.out.println("Generating maze...");
        SecureRandom randomizer = new SecureRandom();
        // start position = (1,1)
        visited = new HashSet<>();
        maze[0][1].clear();
        Cell position = maze[1][1];
        parent.put(position, maze[0][1]);

        // TODO busca em profundidade aleatória
        generate(position);
        maze[x-1][y-2].clear();
        parent.put(maze[x-1][y-2], maze[x-2][y-2]);
    }

    private void generate(Cell start) {
        if (visited.contains(start)) return;
        List<Cell> possible = allMoves(start);
        visited.add(start);
        for (Cell c : possible) {
            if (!visited.contains(c)) {
                parent.put(c, start);
                destroyWall(c, start);
                generate(c);
            }
        }
    }

    private void printMaze() {
        System.out.println();
        for (int i=0; i<x;i++) {
            for (int j=0;j<y;j++) {
                System.out.print((char) maze[i][j].value);
            }
            System.out.println();
        }
    }

    private void destroyWall(Cell position, Cell previous) {
        if (previous==position) return; // do nothing
        else {
            int posx = (position.x + previous.x)/2;
            int posy = (position.y + previous.y)/2;
            Cell target = maze[posx][posy];
            target.clear();
            parent.put(position, target);
            parent.put(target, previous);
        }
    }

    private List<Cell> allMoves(Cell position) {
        List<Cell> moves = new ArrayList<>();
        int posx = position.x;
        int posy = position.y;

        // north
        Cell north = posx-2 >= 0? maze[posx-2][posy] : null;
        Cell south = posx+2 < this.x? maze[posx+2][posy] : null;
        Cell west = posy-2 >= 0? maze[posx][posy-2] : null;
        Cell east = posy+2 < this.y? maze[posx][posy+2] : null;

        if (north!=null) moves.add(north);
        if (south!=null) moves.add(south);
        if (west!=null) moves.add(west);
        if (east!=null) moves.add(east);

        Collections.shuffle(moves);
        return moves;
    }
    private void solution() {
        Cell m = maze[x-1][y-2];
        Stack stack = new Stack();

        while (m!=maze[0][1]) {
            stack.push(m);
            m = parent.get(m);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int x;
        int y;
        while (true) {

            x = scan.nextInt();
            y = scan.nextInt();
            Maze m = new Maze(x, y);
            System.out.println("Maze:");
            m.printMaze();
            System.out.println("Solution:");
            m.solution();
            
            
            Estado inicial= new Estado();
            System.out.println("\n\n\n");
            System.out.println("coloque o estado(saida) final de y(linha vertical)");
            
            y = scan.nextInt();
            System.out.println("\n\n");
            System.out.println("coloque o estado(saida) final de x (linha horizontal)");
            
            x = scan.nextInt();
            
            Estado end = new Estado(y,x);
            
            m.pegarinicial(inicial);
            
            m.buscar(inicial, end);


        }
    }
    
    
    
    
  
//meus codigos e funções da solução
    
    //função principal recursiva buscar
    
    public void buscar(Estado inici,Estado estadofinal){

        
        if((char)maze[inici.getY()][inici.getX()].value == '#' || verificarVisitado(inici)==true){
                return;
        }
       
        pegarEstado(inici);
        
        if(inici.getX() == estadofinal.x && inici.getY() == estadofinal.y ){
                System.out.println(" achei a saida");
                return;
        }
        
            int x=inici.getX();
            int y= inici.getY();
            
        buscar(inici.moverBaixo(inici), estadofinal);
        
            inici.setY(y);
            inici.setX(x);
        
        buscar(inici.moverDireita(inici), estadofinal);
        
            inici.setY(y);
            inici.setX(x);
       
        
        buscar(inici.moverEsquerda(inici), estadofinal);
          
            inici.setY(y);
            inici.setX(x);
         buscar(inici.moverCima(inici), estadofinal);
          

    }
    //sempre pegar todos os estado e adiciona no arraylist 
    public void pegarEstado(Estado estado){
        
        this.list.add(new Estado(estado.getY(),estado.getX()));
        
    }
    
    //função responsavel para pegar a entrada do labirinto!
    
public void pegarinicial(Estado inicial){
    boolean flag=true;
        System.out.println("gerando a solução do labirinto");

       for (int i=0; i<x;i++) {
             for (int j=0;j<y;j++) {
                 char entrada = (char)maze[i][j].value;
                     if(entrada == '0' && flag==true){
                       
                        inicial.setY(i);
                        inicial.setX(j);
                        
                        flag=false;

                         break;
                     }

             }
             

         }

    }
//retorna verdadeiro se o resultado existir  
public boolean verificarVisitado(Estado estadoAtual){
    
    
    if(list.isEmpty()){
    
        //System.out.println("arreylist vazio");
       
   }
    else{
   
        for(int i=0; i < list.size();i++){
         
            if(estadoAtual.getX()==list.get(i).getX() && estadoAtual.getY() == list.get(i).getY()){
              
                return true;
            }

        }
    }
   
    return false;
    
    }


}

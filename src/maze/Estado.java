
package maze;

import java.util.ArrayList;

public class Estado {
    
    int x;
    int y;
    
    ArrayList<Estado> visitados = new ArrayList<>();
    
    Estado(){}
    
    Estado(int y,int x){
    
        this.y=y;
        this.x=x;    
    }

   //funções de movimento dos eixos y e x;
     public Estado moverCima(Estado estado){
        
         
        if(estado.getY()>0){ 
        estado.setY(this.getY()-1);
        
        }
        return estado;
        
    };
    public Estado moverBaixo(Estado estado){
       
        estado.setY(this.getY()+1);
     
        return estado;
    };

    public Estado moverEsquerda(Estado estado){
     
        if(estado.getX()>0){
        estado.setX(this.getX()-1);
       }
        
        return estado;
    
    };

    public Estado moverDireita(Estado estado){
      
        estado.setX(this.getX()+1);  
        
        return estado;
    };
    
    
    //getters e setters
    
     public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

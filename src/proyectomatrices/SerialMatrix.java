/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gerar
 */
public final class SerialMatrix {
    
    int[][] Matrix1;
    int[][] Matrix2;
    int[][] auxMatrix;
    long time1;
    long time2;
    long time1M;
    long time2M;
    int rows;
    int cols;
    
    public SerialMatrix() {
        
        
        
    }
    
    public SerialMatrix(int rows, int cols) {
        
        this.rows = rows;
        this.cols = cols;
        initializeMatrix();
        
    }
    
    public int[][] CalculateMatrix() {
        
        time1 = System.nanoTime(); 
        time1M = System.currentTimeMillis();
        
        for(int i = 0; i < rows; i++) {
            
            for(int j = 0; j < cols; j++) {
                
//                auxMatrix[i][j] = Matrix1[i][0] * Matrix2[0][j] + Matrix1[i][1] * Matrix2[1][j] + Matrix1[i][2] * Matrix2[2][j] + Matrix1[i][3] * Matrix2[3][j]; 
                  auxMatrix[i][j] = multiplyMatrix(i, j);
                
            }
            
        }
        
        time2 = System.nanoTime();
        time2M = System.currentTimeMillis();
        
        time1 = time2 - time1;
        time1M = time2M - time1M;

//        System.out.println("El tiempo de ejecución fue: " + time1);
        System.out.println("El tiempo de ejecución del secuencial fue: " + time1M + "ms");
        
        return auxMatrix;
        
    }
    
    public void initializeMatrix(){
               
        Matrix1 = new int[rows][cols];
        Matrix2 = new int[rows][cols];
        auxMatrix = new int[rows][cols];
//        int offset = 0;
        
        for(int i = 0; i < rows; i++) {
            
            for(int j = 0; j < cols; j++) {
                
                Matrix1[i][j] = (j + 1);
                Matrix2[i][j] = (j + 1);
//                System.out.println("Valor de matriz1 en posición: " + i + " " + j + " : " + Matrix1[i][j]);
//                System.out.println("Valor de matriz2 en posición: " + i + " " + j + " : " + Matrix2[i][j]);
                
//                if(j + 1 == cols) offset = offset + (j + 1);
                
            }
            
        }
        
    }
    
    public int multiplyMatrix(int i, int j) {
        
        int res = 0;
        int aux = 0;
        
        while(aux < rows){
            
            res = res + (Matrix1[i][aux] * Matrix2[aux][j]);
            aux++;
            
        }
        
        return res;
        
    }
   
    
}

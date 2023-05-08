/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

/**
 *
 * @author gerar
 */
public class Initialize {
      
    public int[][] initalizeArray(int[][] Matrix, int rows, int cols) {

        //Se crea un arreglo con números random del 1 al 1000
        for(int i = 0; i < rows; i++) {

            for(int j = 0; j < cols; j++) {

                Matrix[i][j] = (int)(Math.random() * 1000);             
            
            }

        }
        
        return Matrix;
        
    }
    
    public void initializeProcess() {
        
        
        
    }
    
}

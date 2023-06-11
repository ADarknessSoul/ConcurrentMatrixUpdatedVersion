/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author angel
 */
public class MiClaseRemota extends UnicastRemoteObject implements MiInterfazRemota {
    
    public String MatrixEnd;
    public long Rmiti;
    public int[][] matrixA;
    public int[][] matrixB;
    public int[][] result;
    private List<MiInterfazRemota> clients;
    public List <Long> tiempos;
    int[][] Matriz;
    Thread[] threads;
    int clientRemainingRows;
    int auxRemainingRows;
    boolean isOdd = false;
    

    public MiClaseRemota() throws RemoteException {
        clients = new ArrayList<>();
        tiempos = new ArrayList<>();
    }

    public void registerClient(MiInterfazRemota client) throws RemoteException {
        clients.add(client);
       
        System.out.println(clients.size());
        System.out.println(client);
    }

    public void MatrixFor(int rowsA, int colsA, int rowsB, int colsB, int[][] MatrizA, int[][] MatrizB, int batchSize) throws RemoteException {
        
        //Matriz = new int[rowsA][colsB];
        int numClients = clients.size(); 
//        int numClients = 1;
        int[][] newMatrix;
        if(rowsA <= batchSize) threads = new Thread[rowsA];
        else threads = new Thread[batchSize];
        
        if(rowsA < numClients) {

            newMatrix = new int[rowsA][colsA];
            
            for(int i = 0; i < rowsA; i++) {
                
                for(int j = 0; j < colsA; j++) {
                    
                    newMatrix[i][j] = MatrizA[i][j];
                    
                    
                }
                
                MiInterfazRemota client = clients.get(i);
                client.initializeThreads(newMatrix.length, colsA, rowsB, colsB, batchSize, newMatrix, MatrizB, i);
                
            }
            
        } else {
            
            clientRemainingRows = rowsA % numClients;
            auxRemainingRows = clientRemainingRows;
            if(clientRemainingRows > 0) isOdd = true;
            
            
            for(int i = 0; i < numClients; i++) {

                MiInterfazRemota client = clients.get(i);
                newMatrix = separateArray(MatrizA, numClients, i, rowsA, colsA);
                client.initializeThreads(rowsA, colsA, rowsB, colsB, batchSize, newMatrix, MatrizB, i);

            }
            
        }

    }
    
    public void initializeThreads(int rowsA, int colsA, int rowsB, int colsB, int batchSize, int[][] Matrix1, int[][] Matrix2, int actualClient) {
            int numBatches = rowsA / batchSize;
            int remainingRows = rowsA % batchSize;
            int startIndex = 0, endIndex = 0;
              
              if(rowsA <= batchSize) {
                  
                int[][] batch = new int[1][colsA];
    
                for(int i = 0; i < rowsA; i++) {
                    
                    for(int j = 0; j < colsA; j++) {
                        
                        batch[0][j] = Matrix1[i][j]; 
                        
                    }
                    
                    startIndex = i;
                    endIndex = startIndex;
                    
                    MatrixRMI runnable = new MatrixRMI(rowsA, colsA, rowsB, colsB, batch, Matrix2, startIndex, endIndex, actualClient);
                    Thread thread = new Thread(runnable);
                    threads[i] = thread;
                    thread.start();
                    
                }
    
              } 
              else {
    
                 if(remainingRows == 0) {

                    int[][] batch = new int[numBatches][colsA];
                    int newRows = -1;
                    boolean flag = true; 
                     
                    for(int i = 0; i < batchSize; i++) {
                         
                        for(int j = 0; j < numBatches; j++) {                    
                                                         
                            newRows++;
                            if(flag) {
                                startIndex = newRows;
                                flag = false;
                            }
                            endIndex = newRows;
                                                
                            for(int k = 0; k < colsA; k++) {
                                
                                batch[j][k] = Matrix1[newRows][k];
                                
                            }

                        }
                        
                    flag = true;
                    MatrixRMI runnable = new MatrixRMI(rowsA, colsA, rowsB, colsB, batch, Matrix2, startIndex, endIndex, actualClient);
                    Thread thread = new Thread(runnable);
                    threads[i] = thread;
                    thread.start();
                         
                    }
                                      
                }
                else {
                                         
                    int newRows = -1;
                    int j = 0;
                    boolean bandera = true;
                    boolean flag = true;
                    int numOfArrays = remainingRows;
                    int batch[][] = new int[numBatches][colsA];
                    
                    for(int i = 0; i < batchSize; i++) {    

                        bandera = true;
                        
                        for(; j < numBatches; j++) { 
                                                         
                            newRows++;
                            if(flag) {
                                startIndex = newRows;
                                flag = false;
                            }
                            endIndex = newRows;
                                                
                            for(int k = 0; k < colsA; k++) {
                                
                                if(numOfArrays != 0 && bandera != false) {
                                    
                                    batch = new int[numBatches + 1][colsA];
                                    numOfArrays--;
                                    bandera = false;
                                    
                                } 
                                else if(bandera != false) {
                                    
                                    batch = new int[numBatches][colsA];
                                    bandera = false;
                                    
                                }
                                batch[j][k] = Matrix1[newRows][k];
                                
                            }
                            
                            
                        }
                        
                    if(remainingRows != 0) {
                        
                        newRows++;
                        endIndex = newRows;
                        
                        for(int k = 0; k < colsA; k++) {
                              
                              batch[j][k] = Matrix1[newRows][k];
                              
                        }  
                        
                        remainingRows--;
                        
                    }
                    
                    flag = true;    
                    MatrixRMI runnable = new MatrixRMI(rowsA, colsA, rowsB, colsB, batch, Matrix2, startIndex, endIndex, actualClient);
                    Thread thread = new Thread(runnable);
                    threads[i] = thread;
                    thread.start();
                    j = 0;
                    bandera = true;
                        
                    }
                   
                }
    
              }
                            
        } //InitializeThreads
    
    public int[][] separateArray(int[][] MatrizA, int numClients, int position, int rowsA, int colsA) {
        
        int blockSize = (rowsA / numClients);
        int[][] auxMatrix = new int[blockSize][colsA];
        
        int startIndex = (blockSize * position);
        int endIndex = startIndex + (blockSize - 1);
        
        if(clientRemainingRows == 0 && !isOdd) {

            //System.out.println("Indice inicio: " + startIndex + ", Indice final: " + endIndex);
            
            for(int i = 0; startIndex <= endIndex; i++) {

                for(int j = 0; j < colsA; j++) {
                    
                     auxMatrix[i][j] = MatrizA[startIndex][j];
                     //System.out.println("Valor de la nueva matriz para el bloque " + position + ": " + auxMatrix[i][j]);
                     
                    
                }
                
                startIndex++;

            }
            
        } else {

            //Se agrega una fila extra a cada blockSize por cada fila faltante en cada iteración

            
            if(clientRemainingRows > 0) {
                
                startIndex = (blockSize + 1) * position;
                endIndex = startIndex + (blockSize);
                auxMatrix = new int[blockSize + 1][colsA];
                clientRemainingRows--;
                
            } else {
                
                startIndex += auxRemainingRows;
                endIndex += auxRemainingRows;
                
            }


            
            for(int i = 0; startIndex <= endIndex; i++) {

                for(int j = 0; j < colsA; j++) {
                    
                     auxMatrix[i][j] = MatrizA[startIndex][j];
                     //System.out.println("Valor de la nueva matriz para el bloque " + position + ": " + auxMatrix[i][j]);
                     
                    
                }
                
                startIndex++;

            }
            
        }

        return auxMatrix;
        
    }
    

    @Override
    public Long TIemEnd() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void multiSeccion(int[][] blockA, int[][] blockB, int startRow, int startCol, int endRow, int endCol) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     
}

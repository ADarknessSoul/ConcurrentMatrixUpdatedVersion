/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author angel
 */
public interface MiInterfazRemota extends Remote{
      public int[][]  MatrixFor (int rowsA, int colsA, int rowsB, int colsB, int[][] MatrizA, int[][] MatrizB, int batchSize)throws RemoteException;
      public void registerClient( MiInterfazRemota client) throws RemoteException;
      public void initializeThreads(int rowsA, int colsA, int rowsB, int colsB, int batchSize, int[][] Matrix1, int[][] Matrix2, int actualClient, int clientStartIndex) throws RemoteException;
      public int getTiempoFinal() throws RemoteException;
      public int getClientes() throws RemoteException;
}

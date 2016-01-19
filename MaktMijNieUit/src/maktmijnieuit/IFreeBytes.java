package maktmijnieuit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;

/**
 *
 * @author Alex Ras
 */
public interface IFreeBytes extends Library{
    public IFreeBytes INSTANCE =(IFreeBytes) Native.loadLibrary("Kernel32", IFreeBytes.class);
    
    public boolean GetDiskFreeSpaceA(String path, IntByReference resultSectorsPerCluster, IntByReference resultBytesPerSector,
            IntByReference resultNumberFreeClusters, IntByReference resultTotalNumberClusters);
    
}

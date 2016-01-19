package maktmijnieuit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sun.jna.ptr.IntByReference;

/**
 *
 * @author Alex Ras
 */
public class MaktMijNieUit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ICurrentTime libTime = ICurrentTime.INSTANCE;
        CurrentTime outputTime = new CurrentTime();
        libTime.GetSystemTime(outputTime);
        
        System.out.println(outputTime.toString());
        
        CurrentTime firstSystemTime = new CurrentTime();
        CurrentTime secondSystemTime = new CurrentTime();
        libTime.GetSystemTime(firstSystemTime);
        for (int i = 0; i < 1000000000; i++);
        libTime.GetSystemTime(secondSystemTime);
        long passedTime = secondSystemTime.getTimeInMillis() - firstSystemTime.getTimeInMillis();
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000000000; i++);
        long estimatedTime = System.nanoTime() - startTime;

        System.out.println("GetSystemTime: " + passedTime + "ms");
        System.out.println("System.nanoTime: " + estimatedTime / 1000000 + "ms");

        IFreeBytes libSpace = IFreeBytes.INSTANCE;
        IntByReference sectorsPerCluster = new IntByReference();
        IntByReference bytesPerSector = new IntByReference();
        IntByReference freeClusters = new IntByReference();
        IntByReference totalClusters = new IntByReference();
        libSpace.GetDiskFreeSpaceA("C:\\", sectorsPerCluster, bytesPerSector, freeClusters, totalClusters);
        long lSectorsPerCluster = sectorsPerCluster.getValue();
        long freeB = freeClusters.getValue() * lSectorsPerCluster * bytesPerSector.getValue();
        long freeKB = freeB / 1024;
        long freeGB = freeB / 1024 / 1024 / 1024;

        System.out.println("C:\\: " + freeKB + "KB");
        System.out.println("C:\\: " + freeGB + "GB");
    }

}

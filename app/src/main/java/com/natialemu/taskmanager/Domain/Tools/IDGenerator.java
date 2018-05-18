package com.natialemu.taskmanager.Domain.Tools;

public class IDGenerator {
    public static int ID = 0;

    public static int getNewID(){
        return (++ID);
    }
}

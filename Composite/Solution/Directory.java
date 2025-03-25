package Composite.Solution;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystem{
    String directoryName;
    List<FileSystem> objs=new ArrayList<>();

    Directory(String directoryName){
        this.directoryName=directoryName;
    }

    public void addFileSystem(FileSystem f)
    {
        objs.add(f);
    }
    @Override
    public void ls() {
        System.out.println("Directory: "+ directoryName);
        for(FileSystem f:objs)
        {
            f.ls();
        }
    }
}

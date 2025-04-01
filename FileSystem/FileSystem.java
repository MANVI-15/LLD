package Questions.FileSystem;

import java.util.ArrayList;

interface Prototype{
    Prototype getClone();
}
interface Component extends Prototype{
    String getName();
    int getSize();
    String getLocation();
    void getContents();
    void setParent(Component c);

}
class File implements Component {
    String name;
    Component parent;

    File(String name,Component parent){
        this.name=name;
        this.parent=parent;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getSize() {
        return 20;
    }

    public String getLocation(){
        if(parent!=null) {
            return parent.getLocation() + "." + name;
        }
        else {
            return name;
        }
    }

    @Override
    public Prototype getClone() {
        File f=new File(this.name,null);
        return f;
    }

    public void getContents(){
        System.out.println(this.name);
    }

    public void setParent(Component c){
        this.parent=c;
    }

}
class Directory implements Component{

    ArrayList<Component>componentList;
    String name;
    Component parent;

    Directory(String name,Component parent){
        this.componentList=new ArrayList<>();
        this.name=name;
        this.parent=parent;
    }

    public String getName(){
        return this.name;
    }

    public void addComponent(Component c){
        componentList.add(c);
        return;
    }

    public void removeComponent(Component c){
        componentList.remove(c);
    }

    public int getSize(){
        int ans=0;
        for(int i=0;i<componentList.size();i++){
            ans+=componentList.get(i).getSize();;
        }

        return ans;
    }

    public String getLocation(){
        if(parent!=null){
            return parent.getLocation()+"."+name;
        }
        else {
            return name;
        }
    }

    @Override
    public Prototype getClone() {
       Directory d= new Directory(this.name,null);
        for (Component component : componentList) {
           Component clone=(Component)component.getClone();
           clone.setParent(this);
           d.addComponent(clone);
        }
        return d;
    }

    public void getContents(){
        System.out.println(name);
        for(Component c:componentList){
            c.getContents();
        }
    }

    public void setParent(Component c){
        this.parent=c;
    }
}
public class FileSystem {
    public static void main(String[] args){
        Directory bollywood=new Directory("bollywood",null);

        Directory genre = new Directory("genre",bollywood);
        Directory comedy=new Directory("comedy",genre);
        Directory romance=new Directory("romance",genre);

        File f1=new File("Welcome",comedy);
        File f2=new File("Hera pheri",comedy);


        File f3=new File("2 States",romance);
        File f4=new File("Dhadkan",romance);

        comedy.addComponent(f1);
        comedy.addComponent(f2);

        romance.addComponent(f3);
        romance.addComponent(f4);

        genre.addComponent(comedy);
        genre.addComponent(romance);

        bollywood.addComponent(genre);

       System.out.println(bollywood.getSize());
       System.out.println(f4.getLocation());
       System.out.println(f3.getLocation());
       bollywood.getContents();

       System.out.println("...............................");

       Directory clone1=(Directory) bollywood.getClone();
       clone1.getContents();

        System.out.println("...............................");

       Directory clone2 =(Directory) genre.getClone();
       clone2.getContents();

       System.out.println("...............................");
       comedy.removeComponent(f1);
       bollywood.getContents();

    }
}

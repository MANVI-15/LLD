package Questions.LoggingSystem;

enum LoggerType {INFO,DEBUG,ERROR,NONE}

abstract class Handler{
   Handler nextHandler;
   Handler(Handler nextHandler){
       this.nextHandler=nextHandler;
   }
   abstract void log(LoggerType type,String mssg);
}

class DebugHandler extends Handler{

   DebugHandler(Handler nextHandler){
        super(nextHandler);
    }

    void log(LoggerType type,String mssg){
        if(type==LoggerType.DEBUG){
            System.out.println("This log is of debug type");
            System.out.println(mssg);
            return;
        }
        if(nextHandler!=null) {
            nextHandler.log(type,mssg);
        }
        else {
            System.out.println("This log type is not supported");
        }
    }
}

class ErrorHandler extends Handler{

    ErrorHandler(Handler nextHandler){
        super(nextHandler);
    }

    void log(LoggerType type,String mssg){
        if(type==LoggerType.ERROR){
            System.out.println("This log is of error type");
            System.out.println(mssg);
            return;
        }
        if(nextHandler!=null) {
            nextHandler.log(type,mssg);
        }
        else {
            System.out.println("This log type is not supported");
        }
    }
}

class InfoHandler extends Handler{

    InfoHandler(Handler nextHandler){
        super(nextHandler);
    }

    void log(LoggerType type,String mssg){
        if(type==LoggerType.INFO){
            System.out.println("This log is of info type");
            System.out.println(mssg);
            return;
        }
        if(nextHandler!=null) {
            nextHandler.log(type,mssg);
        }
        else {
            System.out.println("This log type is not supported");
        }
    }
}

public class LoggingSystem {

    public static void main(String[] args) {
        Handler loggerHandler = new DebugHandler(new InfoHandler(new ErrorHandler(null)));

        loggerHandler.log(LoggerType.DEBUG,"need to debug this");
        loggerHandler.log(LoggerType.INFO,"just for info");
        loggerHandler.log(LoggerType.NONE,"please log this");
    }

}

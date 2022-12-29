package com.ruppyrup.episode13.spring.annotations;


import com.ruppyrup.episode13.spring.annotations.service.IMessageRenderer;
import com.ruppyrup.episode13.spring.rrframework.application.RRDIApplication;


public class Main extends RRDIApplication {


    public static void main(String[] args){
        Main main = new Main();
        main.start(main.getClass());
    }

    @Override
    public void run() {
        IMessageRenderer renderer = (IMessageRenderer) getBean("StandardOutMessageRenderer");
        renderer.render();
    }

    @Override
    public void cleanup() {
        System.out.println("Finished");
    }
}

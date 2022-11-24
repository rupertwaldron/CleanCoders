package com.ruppyrup.episode13.spring.annotations;


import com.ruppyrup.solid.DIP.spring.annotations.service.IMessageRenderer;
import com.ruppyrup.solid.DIP.spring.rrframework.application.RRDIApplication;

/**
 * 
 * @author apuravchauhan
 *
 */
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

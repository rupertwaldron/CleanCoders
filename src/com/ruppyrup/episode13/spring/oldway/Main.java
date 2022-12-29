package com.ruppyrup.episode13.spring.oldway;


import com.ruppyrup.episode13.spring.oldway.service.HelloWorldMessageProvider;
import com.ruppyrup.episode13.spring.oldway.service.IMessageProvider;
import com.ruppyrup.episode13.spring.oldway.service.IMessageRenderer;
import com.ruppyrup.episode13.spring.oldway.service.StandardOutMessageRenderer;

/**
 * 
 * @author apuravchauhan
 *
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
        
        IMessageProvider provider = new HelloWorldMessageProvider();
        IMessageRenderer messageRenderer = new StandardOutMessageRenderer();
        messageRenderer.setMessageProvider(provider);
        messageRenderer.render();
    }

}

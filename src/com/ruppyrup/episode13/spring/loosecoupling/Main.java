package com.ruppyrup.episode13.spring.loosecoupling;


import com.ruppyrup.solid.DIP.spring.loosecoupling.service.IMessageRenderer;

/**
 * 
 * @author apuravchauhan
 *
 */
public class Main {
    public static void main(String[] args) throws Exception {
        IMessageRenderer renderer = (IMessageRenderer) ApuravFramework.beanRegistry.get("renderer");
        renderer.render();
    }
}

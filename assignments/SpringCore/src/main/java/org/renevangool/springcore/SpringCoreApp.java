package org.renevangool.springcore;

import org.renevangool.springcore.controller.StringController;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Scanner;

public class SpringCoreApp {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ConfigurableEnvironment env = ctx.getEnvironment();
        env.setActiveProfiles("prod");
        ctx.load("*-profile.xml");
        ctx.refresh();

        StringController controller = ctx.getBean(StringController.class);

        boolean run = true;

        while(run) {
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter String to modify:");

            String str = myObj.nextLine();  // Read user input

            if(str.equals("")){run = false;}

            System.out.println("Original string: " + str);

            String transformedString = controller.getTransformedString(str);
            System.out.println("Transformed string: " + transformedString);

            int wordCount = controller.getCountedWords(str);
            System.out.println("Word count: " + wordCount);
        }
    }
}
